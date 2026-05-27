package com.magafin.client.event;

import com.magafin.client.render.FermentationBarrelRenderer;
import com.magafin.common.nmdreg.BlockEntityReg;
import com.magafin.nomansdelight;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = nomansdelight.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientSetupEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityReg.FERMENTATION_BARREL_BE.get(), FermentationBarrelRenderer::new);
    }
}