package com.magafin.common.block;

import com.magafin.common.block.entity.FermentationBarrelBlockEntity;
import com.magafin.common.nmdreg.BlockEntityReg;
import com.magafin.common.nmdreg.RecipeReg;
import com.magafin.common.recipe.FermentationRecipe;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FermentationBarrelBlock extends BaseEntityBlock {

    public static final MapCodec<FermentationBarrelBlock> CODEC = simpleCodec(FermentationBarrelBlock::new);

    // Хитбокс с пустой серединой (как у компостницы)
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),   // Дно
            Block.box(0, 2, 0, 2, 16, 16),  // Стенка West
            Block.box(14, 2, 0, 16, 16, 16), // Стенка East
            Block.box(2, 2, 0, 14, 16, 2),  // Стенка North
            Block.box(2, 2, 14, 14, 16, 16) // Стенка South
    );

    public FermentationBarrelBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return 1.0F; // Убирает лишние тени внутри блока
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof FermentationBarrelBlockEntity barrel)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        ItemStack heldItem = player.getItemInHand(hand);

        // --- 1. СБОР ГОТОВОГО ПРОДУКТА ---
        if (barrel.isFinished) {
            ItemStack resultStack = barrel.outputStack;

            // Автоматически определяем нужную тару через ванильный CraftingRemainder
            ItemStack requiredContainer = resultStack.getItem().hasCraftingRemainingItem()
                    ? new ItemStack(resultStack.getItem().getCraftingRemainingItem())
                    : ItemStack.EMPTY;

            boolean needsContainer = !requiredContainer.isEmpty();

            // Если нужна тара, а игрок держит что-то другое
            if (needsContainer && !ItemStack.isSameItemSameComponents(heldItem, requiredContainer)) {
                if (!level.isClientSide) {
                    // Выводим красивое сообщение над хотбаром
                    player.displayClientMessage(
                            net.minecraft.network.chat.Component.literal("Для сбора нужен предмет: ").append(requiredContainer.getHoverName()),
                            true
                    );
                }
                return ItemInteractionResult.FAIL; // Рука не дергается
            }

            if (level.isClientSide) return ItemInteractionResult.SUCCESS;

            if (needsContainer) {
                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                heldItem.shrink(1);
            } else {
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.6F, 0.8F);
            }

            player.getInventory().placeItemBackInInventory(barrel.outputStack.copy());
            barrel.inputStack.shrink(1);

            if (barrel.inputStack.isEmpty()) {
                barrel.isFinished = false;
                barrel.outputStack = ItemStack.EMPTY;
                barrel.currentTexture = "";
            }
            barrel.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
            return ItemInteractionResult.SUCCESS;
        }

        // --- 2. БОЧКА В ПРОЦЕССЕ (ИЗВЛЕЧЕНИЕ СЫРЬЯ ИЛИ ДОКЛАДЫВАНИЕ) ---
        if (!barrel.isFinished && !barrel.inputStack.isEmpty()) {
            ItemStack input = barrel.inputStack;

            // Определяем тару по сырью (например, пустая миска, чтобы забрать рагу)
            ItemStack requiredContainer = input.getItem().hasCraftingRemainingItem()
                    ? new ItemStack(input.getItem().getCraftingRemainingItem())
                    : ItemStack.EMPTY;

            boolean needsContainer = !requiredContainer.isEmpty();

            // Проверяем, правильным ли предметом игрок пытается извлечь сырье
            boolean isTryingToExtract = (needsContainer && ItemStack.isSameItemSameComponents(heldItem, requiredContainer))
                    || (!needsContainer && heldItem.isEmpty());

            if (isTryingToExtract) {
                if (level.isClientSide) return ItemInteractionResult.SUCCESS;

                if (needsContainer) {
                    heldItem.shrink(1);
                    level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                } else {
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.6F, 1.2F);
                }

                player.getInventory().placeItemBackInInventory(barrel.inputStack.copyWithCount(1));
                barrel.inputStack.shrink(1);

                if (barrel.inputStack.isEmpty()) {
                    barrel.progress = 0;
                    barrel.currentTexture = "";
                }
                barrel.setChanged();
                level.sendBlockUpdated(pos, state, state, 3);
                return ItemInteractionResult.SUCCESS;
            }

            // Если предметы совпадают — игрок докладывает сырье
            if (ItemStack.isSameItemSameComponents(heldItem, input) && barrel.inputStack.getCount() < 4) {
                if (level.isClientSide) return ItemInteractionResult.SUCCESS;

                if (needsContainer) {
                    level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.2F);
                    player.getInventory().placeItemBackInInventory(requiredContainer.copy());
                } else {
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.4F, 1.5F);
                }

                barrel.inputStack.grow(1);
                heldItem.shrink(1);
                barrel.setChanged();
                level.sendBlockUpdated(pos, state, state, 3);
                return ItemInteractionResult.SUCCESS;
            }

            // Если кликнул пустой рукой (а нужна тара) или совсем не тем предметом
            if (heldItem.isEmpty() && needsContainer) {
                if (!level.isClientSide) {
                    player.displayClientMessage(
                            net.minecraft.network.chat.Component.literal("Для извлечения нужен предмет: ").append(requiredContainer.getHoverName()),
                            true
                    );
                }
            }
            return ItemInteractionResult.FAIL;
        }

        // --- 3. ПУСТАЯ БОЧКА (ЗАГРУЗКА ПЕРВОГО ИНГРЕДИЕНТА) ---
        if (barrel.inputStack.isEmpty()) {
            Optional<RecipeHolder<FermentationRecipe>> recipeOpt = level.getRecipeManager()
                    .getRecipeFor(RecipeReg.FERMENTATION_TYPE.get(), new SingleRecipeInput(heldItem), level);

            if (recipeOpt.isEmpty()) return ItemInteractionResult.FAIL;

            if (level.isClientSide) return ItemInteractionResult.SUCCESS;

            FermentationRecipe rec = recipeOpt.get().value();

            ItemStack requiredContainer = heldItem.getItem().hasCraftingRemainingItem()
                    ? new ItemStack(heldItem.getItem().getCraftingRemainingItem())
                    : ItemStack.EMPTY;
            boolean hasRemainder = !requiredContainer.isEmpty();

            if (hasRemainder) {
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.2F);
                player.getInventory().placeItemBackInInventory(requiredContainer.copy());
            } else {
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.4F, 1.5F);
            }

            barrel.inputStack = heldItem.copyWithCount(1);
            barrel.maxProgress = rec.processTime();
            barrel.currentTexture = rec. readyTexture().toString();

            heldItem.shrink(1);
            barrel.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
            return ItemInteractionResult.SUCCESS;
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FermentationBarrelBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, BlockEntityReg.FERMENTATION_BARREL_BE.get(), FermentationBarrelBlockEntity::tick);
    }
}