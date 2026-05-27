package com.magafin.common.nmdreg;

import com.magafin.common.item.BronzeMaterial;
import com.magafin.common.item.CleaverItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.function.Supplier;

import static com.magafin.nomansdelight.MODID;
import static vectorwing.farmersdelight.common.registry.ModItems.*;

public class ItemReg {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> LIVING_SOUP_BOWL = ITEMS.register("living_soup_bowl", registryName ->
            new ConsumableItem(bowlFoodItem(FoodValues.LIVING_SOUP_BOWL).stacksTo(16), true));
    public static final DeferredItem<Item> LIVING_SOUP_ITEM = ITEMS.register("living_soup",()->
            new BlockItem(BlockReg.LIVING_SOUP.get(), basicItem().stacksTo(1)));
    public static final Supplier<Item> CLEAVER = ITEMS.register("cleaver",
            () -> new CleaverItem(BronzeMaterial.BRONZE, knifeItem(BronzeMaterial.BRONZE).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> VENISON_ROULADE_PLATE = ITEMS.register("venison_roulade", registryName ->
            new ConsumableItem(bowlFoodItem(FoodValues.VENISON_ROULADE).stacksTo(16), true));
    public static final DeferredItem<Item> VENISON_ROULADE_BLOCK = ITEMS.register("venison_roulade_block",()->
            new BlockItem(BlockReg.VENISON_ROULADE_BLOCK.get(), basicItem().stacksTo(1)));
    public static final DeferredItem<Item> FROG_SANDWICH = ITEMS.register("frog_sandwich", registryName ->
            new ConsumableItem(foodItem(FoodValues.FROG_SANDWICH), false));
    public static final DeferredItem<Item> FRUIT_TART = ITEMS.register("fruit_tart", registryName ->
            new ConsumableItem(foodItem(FoodValues.FRUIT_TART), false));
    public static final DeferredItem<Item> NUT_BUN = ITEMS.register("nut_bun", registryName ->
            new ConsumableItem(foodItem(FoodValues.NUT_BUN).stacksTo(32), false));
    public static final DeferredItem<Item> SMORE = ITEMS.register("smore", registryName ->
            new ConsumableItem(foodItem(FoodValues.SMORE).stacksTo(64), true));
    public static final DeferredItem<Item> VENISON_CHOP = ITEMS.register("venison_chop", registryName ->
            new ConsumableItem(foodItem(FoodValues.VENISON_CHOP), false));
    public static final DeferredItem<Item> COOKED_VENISON_CHOP = ITEMS.register("cooked_venison_chop", registryName ->
            new ConsumableItem(foodItem(FoodValues.COOKED_VENISON_CHOP), false));
    public static final DeferredItem<Item> HORSE_CUTS = ITEMS.register("horse_cuts", registryName ->
            new ConsumableItem(foodItem(FoodValues.HORSE_CUTS), false));
    public static final DeferredItem<Item> COOKED_HORSE_CUTS = ITEMS.register("cooked_horse_cuts", registryName ->
            new ConsumableItem(foodItem(FoodValues.COOKED_HORSE_CUTS), false));
    public static final DeferredItem<Item> SPORE_SALAD = ITEMS.register("spore_salad", registryName ->
            new ConsumableItem(bowlFoodItem(FoodValues.SPORE_SALAD).stacksTo(16), true));
    public static final DeferredItem<Item> COOKED_WEEDS = ITEMS.register("cooked_weeds", registryName ->
            new ConsumableItem(bowlFoodItem(FoodValues.COOKED_WEEDS).stacksTo(16), true));
    public static final DeferredItem<Item> LAVENDER_TEA = ITEMS.register("lavender_tea", registryName ->
            new DrinkableItem(drinkItem().food(FoodValues.LAVENDER_TEA), true, false));
    public static final DeferredItem<Item> PESTO_PIZZA_SLICE = ITEMS.register("pesto_pizza_slice", registryName ->
            new ConsumableItem(foodItem(FoodValues.PESTO_PIZZA_SLICE), false));
    public static final DeferredItem<Item> PESTO_PIZZA_ITEM = ITEMS.register("pesto_pizza",()->
            new BlockItem(BlockReg.PESTO_PIZZA.get(), basicItem().stacksTo(1)));
    public static final DeferredItem<Item> VENISON_TARTARE = ITEMS.register("venison_tartare", registryName ->
            new ConsumableItem(bowlFoodItem(FoodValues.VENISON_TARTARE).stacksTo(16), true));
    public static final DeferredItem<Item> BILLHOOK_BASS_WITH_EGGS = ITEMS.register("billhook_bass_with_eggs", registryName ->
            new ConsumableItem(bowlFoodItem(FoodValues.BILLHOOK_BASS_WITH_EGGS).stacksTo(16), true));
    public static final DeferredItem<Item> BILLHOOK_BASS_SLICE = ITEMS.register("billhook_bass_slice", registryName ->
            new ConsumableItem(foodItem(FoodValues.BILLHOOK_BASS_SLICE), false));
    public static final DeferredItem<Item> COOKED_BILLHOOK_BASS_SLICE = ITEMS.register("cooked_billhook_bass_slice", registryName ->
            new ConsumableItem(foodItem(FoodValues.COOKED_BILLHOOK_BASS_SLICE), false));
    public static final DeferredItem<Item> SHROOMBURGER = ITEMS.register("shroomburger", registryName ->
            new ConsumableItem(foodItem(FoodValues.SHROOMBURGER), true));
    public static final DeferredItem<Item> HORSE_WRAP = ITEMS.register("horse_wrap", registryName ->
            new ConsumableItem(foodItem(FoodValues.HORSE_WRAP), false));
    public static final DeferredItem<Item> BILLHOOK_BASS_ROLL = ITEMS.register("billhook_bass_roll", registryName ->
            new ConsumableItem(foodItem(FoodValues.BILLHOOK_BASS_ROLL), false));
   // public static final Supplier<Item> MEAT_PIE_SLICE = ITEMS.register("meat_pie_slice",
       // () -> new Item(foodItem(FoodValues.MEAT_PIE)));
   // public static final Supplier<Item> MEAT_PIE = ITEMS.register("meat_pie",
           // () -> new BlockItem(BlockReg.MEAT_PIE.get(), basicItem()));
   // public static final DeferredItem<Item> SHAWARMA = ITEMS.register("shawarma", registryName ->
           // new ConsumableItem(foodItem(FoodValues.SHAWARMA), false));
   // public static final Supplier<Item> FERMENTATION_BARREL_ITEM = ITEMS.register("fermentation_barrel",
           // () -> new BlockItem(BlockReg.FERMENTATION_BARREL.get(), basicItem()));

    public static final DeferredItem<Item> ORANGE = ITEMS.register("orange", registryName ->
            new ConsumableItem(foodItem(FoodValues.SMORE).stacksTo(64), true));
}

