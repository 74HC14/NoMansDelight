package com.magafin.common.nmdreg;

import com.magafin.common.block.*;
import com.magafin.nomansdelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.function.Supplier;

import static com.magafin.common.nmdreg.ItemReg.LIVING_SOUP_BOWL;
import static com.magafin.common.nmdreg.ItemReg.VENISON_ROULADE_PLATE;
import static com.magafin.common.nmdreg.ItemReg.PESTO_PIZZA_SLICE;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class BlockReg {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, nomansdelight.MODID);

    public static final Supplier<Block> LIVING_SOUP = BLOCKS.register("living_soup",
            () -> new LivingSoupBlock(ofFullCopy(Blocks.FLOWER_POT).sound(SoundType.DECORATED_POT), LIVING_SOUP_BOWL, true));
    public static final Supplier<Block> VENISON_ROULADE_BLOCK = BLOCKS.register("venison_roulade_block",
            () -> new VenisonRouladeBlock(ofFullCopy(Blocks.CAKE), VENISON_ROULADE_PLATE, true));
    public static final Supplier<Block> PESTO_PIZZA = BLOCKS.register("pesto_pizza",
            () -> new PestoPizzaBlock(ofFullCopy(Blocks.CAKE).noOcclusion(), PESTO_PIZZA_SLICE));
   // public static final Supplier<Block> MEAT_PIE = BLOCKS.register("meat_pie",
           // () -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ItemReg.MEAT_PIE_SLICE));
    public static final Supplier<Block> FERMENTATION_BARREL = BLOCKS.register("fermentation_barrel",
            () -> new FermentationBarrelBlock(ofFullCopy(Blocks.COMPOSTER).noOcclusion()));
}

