package com.magafin.common.recipe;

import com.magafin.common.nmdreg.RecipeReg;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record FermentationRecipe(
        Ingredient input,
        ItemStack output,
        ResourceLocation rawTexture,
        ResourceLocation readyTexture,
        int processTime,
        int minLight,
        int maxLight
) implements Recipe<SingleRecipeInput> {

    public static final MapCodec<FermentationRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(FermentationRecipe::input),
            ItemStack.CODEC.fieldOf("output").forGetter(FermentationRecipe::output),
            ResourceLocation.CODEC.fieldOf("raw_texture").forGetter(FermentationRecipe::rawTexture),
            ResourceLocation.CODEC.fieldOf("ready_texture").forGetter(FermentationRecipe::readyTexture),
            Codec.INT.optionalFieldOf("time", 200).forGetter(FermentationRecipe::processTime),
            Codec.INT.optionalFieldOf("min_light", 0).forGetter(FermentationRecipe::minLight),
            Codec.INT.optionalFieldOf("max_light", 15).forGetter(FermentationRecipe::maxLight)
    ).apply(inst, FermentationRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FermentationRecipe> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public FermentationRecipe decode(RegistryFriendlyByteBuf buffer) {
            return new FermentationRecipe(
                    Ingredient.CONTENTS_STREAM_CODEC.decode(buffer),
                    ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer),
                    ResourceLocation.STREAM_CODEC.decode(buffer),
                    ResourceLocation.STREAM_CODEC.decode(buffer),
                    ByteBufCodecs.INT.decode(buffer),
                    ByteBufCodecs.INT.decode(buffer),
                    ByteBufCodecs.INT.decode(buffer)
            );
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buffer, FermentationRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.input());
            ItemStack.STREAM_CODEC.encode(buffer, recipe.output());
            ResourceLocation.STREAM_CODEC.encode(buffer, recipe.rawTexture());
            ResourceLocation.STREAM_CODEC.encode(buffer, recipe.readyTexture());
            ByteBufCodecs.INT.encode(buffer, recipe.processTime());
            ByteBufCodecs.INT.encode(buffer, recipe.minLight());
            ByteBufCodecs.INT.encode(buffer, recipe.maxLight());
        }
    };

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.input.test(input.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider provider) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeReg.FERMENTATION_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeReg.FERMENTATION_TYPE.get();
    }
}