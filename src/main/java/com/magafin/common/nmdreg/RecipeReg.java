package com.magafin.common.nmdreg;

import com.magafin.common.recipe.FermentationRecipe;
import com.magafin.nomansdelight;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RecipeReg {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, nomansdelight.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, nomansdelight.MODID);

    public static final Supplier<RecipeType<FermentationRecipe>> FERMENTATION_TYPE = RECIPE_TYPES.register("fermentation", () -> new RecipeType<>() {
        @Override
        public String toString() { return "fermentation"; }
    });

    public static final Supplier<RecipeSerializer<FermentationRecipe>> FERMENTATION_SERIALIZER = RECIPE_SERIALIZERS.register("fermentation", () ->
            new RecipeSerializer<FermentationRecipe>() {
                @Override
                public MapCodec<FermentationRecipe> codec() { return FermentationRecipe.CODEC; }
                @Override
                public StreamCodec<RegistryFriendlyByteBuf, FermentationRecipe> streamCodec() { return FermentationRecipe.STREAM_CODEC; }
            }
    );
}
