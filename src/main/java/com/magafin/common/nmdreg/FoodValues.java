package com.magafin.common.nmdreg;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class FoodValues {
    public static final int BRIEF_DURATION = 600;    // 30 seconds
    public static final int SHORT_DURATION = 1200;    // 1 minute
    public static final int MEDIUM_DURATION = 3600;    // 3 minutes
    public static final int LONG_DURATION = 6000;// 5 minutes
    public static final int VERY_LONG_DURATION = 8400;// 7 minutes

    public static final FoodProperties LIVING_SOUP_BOWL = (new FoodProperties.Builder())
            .nutrition(12).saturationModifier(0.75f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, MEDIUM_DURATION, 0), 1.0F).build();
    public static final FoodProperties VENISON_ROULADE = (new FoodProperties.Builder())
            .nutrition(14).saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, LONG_DURATION, 0), 1.0F).build();
    public static final FoodProperties FROG_SANDWICH = (new FoodProperties.Builder())
            .nutrition(10).saturationModifier(0.8F).build();
    public static final FoodProperties FRUIT_TART = (new FoodProperties.Builder())
            .nutrition(6).saturationModifier(0.5f).build();
    public static final FoodProperties NUT_BUN = (new FoodProperties.Builder())
            .nutrition(5).saturationModifier(0.5f).build();
    public static final FoodProperties SMORE = (new FoodProperties.Builder())
            .nutrition(9).saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT, MEDIUM_DURATION, 0), 1.0F).build();
    public static final FoodProperties VENISON_CHOP = (new FoodProperties.Builder())
            .nutrition(1).saturationModifier(0.5f).build();
    public static final FoodProperties COOKED_VENISON_CHOP = (new FoodProperties.Builder())
            .nutrition(3).saturationModifier(0.8f).build();
    public static final FoodProperties HORSE_CUTS = (new FoodProperties.Builder())
            .nutrition(2).saturationModifier(0.5f).build();
    public static final FoodProperties COOKED_HORSE_CUTS = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.8f).build();
    public static final FoodProperties SPORE_SALAD = (new FoodProperties.Builder())
            .nutrition(5).saturationModifier(0.4F)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60, 0), 1.0F).build();
    public static final FoodProperties COOKED_WEEDS = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.5F)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 300, 0), 1.0F).build();
    public static final FoodProperties LAVENDER_TEA = (new FoodProperties.Builder()).alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, BRIEF_DURATION, 0), 1.0F).build();
    public static final FoodProperties PESTO_PIZZA_SLICE = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.8f).build();
    public static final FoodProperties VENISON_TARTARE = (new FoodProperties.Builder())
            .nutrition(10).saturationModifier(0.75f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT, MEDIUM_DURATION, 0), 1.0F).build();
    public static final FoodProperties BILLHOOK_BASS_WITH_EGGS = (new FoodProperties.Builder())
            .nutrition(12).saturationModifier(0.75f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, MEDIUM_DURATION, 0), 1.0F).build();
    public static final FoodProperties BILLHOOK_BASS_SLICE = (new FoodProperties.Builder())
            .nutrition(1).saturationModifier(0.1f).build();
    public static final FoodProperties COOKED_BILLHOOK_BASS_SLICE = (new FoodProperties.Builder())
            .nutrition(3).saturationModifier(0.5f).build();
    public static final FoodProperties SHROOMBURGER = (new FoodProperties.Builder())
            .nutrition(10).saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT, BRIEF_DURATION, 0), 1.0F).build();
    public static final FoodProperties HORSE_WRAP = (new FoodProperties.Builder())
            .nutrition(11).saturationModifier(0.8F).build();
    public static final FoodProperties BILLHOOK_BASS_ROLL = (new FoodProperties.Builder())
            .nutrition(7).saturationModifier(0.6F).build();
    public static final FoodProperties MEAT_PIE = (new FoodProperties.Builder())
            .nutrition(6).saturationModifier(0.8F).build();
    public static final FoodProperties SHAWARMA = (new FoodProperties.Builder())
            .nutrition(6).saturationModifier(0.8F).build();

}
