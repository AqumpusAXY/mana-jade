package github.aqumpusaxy.mana_jade.util.calc.flora.generating;

import github.aqumpusaxy.mana_jade.mixin.generator.EndoflameBurnTimeAccessor;
import snownee.jade.api.BlockAccessor;
import vazkii.botania.common.block.flower.generating.EndoflameBlockEntity;

import static github.aqumpusaxy.mana_jade.util.calc.flora.CommonFloraCalc.ticksToSeconds;
import static github.aqumpusaxy.mana_jade.util.calc.flora.CommonFloraCalc.isBoosted;

public class EndoflameCalc {
    private static final int MANA_PER_TICK = 3;
    private static final int GENERATION_DELAY = 2;

    public static double getEndoflameManaPerSecond(BlockAccessor accessor) {
        EndoflameBlockEntity blockEntity = (EndoflameBlockEntity) accessor.getBlockEntity();

        return MANA_PER_TICK * 20D / GENERATION_DELAY * (isBoosted(blockEntity) ? 2 : 1);
    }

    public static double getEndoflameBurnTime(BlockAccessor accessor) {
        EndoflameBlockEntity blockEntity = (EndoflameBlockEntity) accessor.getBlockEntity();

        return ticksToSeconds(((EndoflameBurnTimeAccessor) blockEntity).getBurnTime(), blockEntity);
    }
}