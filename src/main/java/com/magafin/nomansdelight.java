package com.magafin;

import com.farcr.nomansland.common.registry.items.NMLCreativeTabs;
import com.farcr.nomansland.common.registry.items.NMLItems;
import com.magafin.common.nmdreg.BlockEntityReg;
import com.magafin.common.nmdreg.BlockReg;
import com.magafin.common.nmdreg.ItemReg;
import com.magafin.common.nmdreg.RecipeReg;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import static com.magafin.common.nmdreg.BlockReg.BLOCKS;
import static com.magafin.common.nmdreg.ItemReg.ITEMS;

@Mod(nomansdelight.MODID)
public class nomansdelight {
    public static final String MODID = "nomansdelight";
    private static final Logger LOGGER = LogUtils.getLogger();

    public nomansdelight(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ITEMS.register(modEventBus);

        BLOCKS.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        BlockEntityReg.BLOCK_ENTITIES.register(modEventBus);
        RecipeReg.RECIPE_TYPES.register(modEventBus);
        RecipeReg.RECIPE_SERIALIZERS.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == NMLCreativeTabs.NO_MANS_TAB.get()){
            event.insertAfter(NMLItems.WATER_MOSAIC.get().getDefaultInstance(), ItemReg.LIVING_SOUP_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.LIVING_SOUP_ITEM.get().getDefaultInstance(), ItemReg.LIVING_SOUP_BOWL.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.LIVING_SOUP_BOWL.get().getDefaultInstance(), ItemReg.VENISON_ROULADE_BLOCK.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.VENISON_ROULADE_BLOCK.get().getDefaultInstance(), ItemReg.VENISON_ROULADE_PLATE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.VENISON_ROULADE_PLATE.get().getDefaultInstance(), ItemReg.SPORE_SALAD.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.SPORE_SALAD.get().getDefaultInstance(), ItemReg.COOKED_WEEDS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.COOKED_WEEDS.get().getDefaultInstance(), ItemReg.FROG_SANDWICH.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.SWEET_TART.get().getDefaultInstance(), ItemReg.FRUIT_TART.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.FROG_SANDWICH.get().getDefaultInstance(), ItemReg.NUT_BUN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.HARDTACK.get().getDefaultInstance(), ItemReg.SMORE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.RAW_VENISON.get().getDefaultInstance(), ItemReg.VENISON_CHOP.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.COOKED_VENISON.get().getDefaultInstance(), ItemReg.COOKED_VENISON_CHOP.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.RAW_HORSE.get().getDefaultInstance(), ItemReg.HORSE_CUTS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.HORSE_STEAK.get().getDefaultInstance(), ItemReg.COOKED_HORSE_CUTS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.NUT_BUN.get().getDefaultInstance(), ItemReg.LAVENDER_TEA.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.VENISON_ROULADE_PLATE.get().getDefaultInstance(), ItemReg.PESTO_PIZZA_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.PESTO_PIZZA_ITEM.get().getDefaultInstance(), ItemReg.PESTO_PIZZA_SLICE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.BILLHOOK_BASS.get().getDefaultInstance(), ItemReg.BILLHOOK_BASS_SLICE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(NMLItems.COOKED_BILLHOOK_BASS.get().getDefaultInstance(), ItemReg.COOKED_BILLHOOK_BASS_SLICE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.COOKED_WEEDS.get().getDefaultInstance(), ItemReg.BILLHOOK_BASS_WITH_EGGS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.BILLHOOK_BASS_WITH_EGGS.get().getDefaultInstance(), ItemReg.VENISON_TARTARE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.FROG_SANDWICH.get().getDefaultInstance(), ItemReg.SHROOMBURGER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.FROG_SANDWICH.get().getDefaultInstance(), ItemReg.HORSE_WRAP.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ItemReg.FROG_SANDWICH.get().getDefaultInstance(), ItemReg.BILLHOOK_BASS_ROLL.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);


            event.insertAfter(NMLItems.ANCIENT_BRONZE_MASK.get().getDefaultInstance(), ItemReg.CLEAVER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }

}