package com.magafin.common.nmdreg;

import com.magafin.common.block.entity.FermentationBarrelBlockEntity;
import com.magafin.nomansdelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockEntityReg {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, nomansdelight.MODID);

    public static final Supplier<BlockEntityType<FermentationBarrelBlockEntity>> FERMENTATION_BARREL_BE = BLOCK_ENTITIES.register("fermentation_barrel",
            () -> BlockEntityType.Builder.of(FermentationBarrelBlockEntity::new, BlockReg.FERMENTATION_BARREL.get()).build(null));
}
