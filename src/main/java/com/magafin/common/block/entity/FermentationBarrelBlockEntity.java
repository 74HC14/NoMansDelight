package com.magafin.common.block.entity;

import com.magafin.common.nmdreg.BlockEntityReg;
import com.magafin.common.recipe.FermentationRecipe;
import com.magafin.common.nmdreg.RecipeReg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class FermentationBarrelBlockEntity extends BlockEntity {
    public ItemStack inputStack = ItemStack.EMPTY;
    public ItemStack outputStack = ItemStack.EMPTY;
    public int progress = 0;
    public int maxProgress = 0;
    public boolean isFinished = false;
    public String currentTexture = "";

    public FermentationBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityReg.FERMENTATION_BARREL_BE.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FermentationBarrelBlockEntity pBlockEntity) {
        if (level.isClientSide || pBlockEntity.isFinished || pBlockEntity.inputStack.isEmpty()) return;

        // Получаем реальный уровень света в блоке (учитывает и небо, и факелы)
        int light = level.getLightEngine().getRawBrightness(pos, 0);

        Optional<RecipeHolder<FermentationRecipe>> recipeOpt = level.getRecipeManager()
                .getRecipeFor(RecipeReg.FERMENTATION_TYPE.get(), new SingleRecipeInput(pBlockEntity.inputStack), level);

        if (recipeOpt.isPresent()) {
            FermentationRecipe recipe = recipeOpt.get().value();

            // Строгая проверка освещения
            if (light >= recipe.minLight() && light <= recipe.maxLight()) {
                pBlockEntity.progress++;
                if (pBlockEntity.progress >= pBlockEntity.maxProgress) {
                    pBlockEntity.isFinished = true;
                    pBlockEntity.outputStack = recipe.output().copy();
                    pBlockEntity.currentTexture = recipe.readyTexture().toString(); // Убедись, что тут правильное поле

                    level.playSound(null, pos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 0.5F, 0.8F);

                    pBlockEntity.setChanged();
                    level.sendBlockUpdated(pos, state, state, 3);
                }
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!inputStack.isEmpty()) tag.put("Input", inputStack.saveOptional(registries));
        if (!outputStack.isEmpty()) tag.put("Output", outputStack.saveOptional(registries));
        tag.putInt("Progress", progress);
        tag.putInt("MaxProgress", maxProgress);
        tag.putBoolean("Finished", isFinished);
        tag.putString("Texture", currentTexture);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inputStack = ItemStack.parseOptional(registries, tag.getCompound("Input"));
        outputStack = ItemStack.parseOptional(registries, tag.getCompound("Output"));
        progress = tag.getInt("Progress");
        maxProgress = tag.getInt("MaxProgress");
        isFinished = tag.getBoolean("Finished");
        currentTexture = tag.getString("Texture");
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    // Добавляем этот метод для корректной синхронизации данных между сервером и клиентом
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) {
            this.loadAdditional(tag, lookupProvider);
        }
    }
}