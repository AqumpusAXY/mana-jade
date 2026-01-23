package github.aqumpusaxy.mana_jade.util.calc.flora.generating;

import github.aqumpusaxy.mana_jade.mixin.generator.RosaArcanaManaPerXpAccessor;
import snownee.jade.api.BlockAccessor;
import vazkii.botania.common.block.flower.generating.RosaArcanaBlockEntity;

import static github.aqumpusaxy.mana_jade.util.calc.flora.CommonFloraCalc.isBoosted;

public class RosaArcanaCalc{
    private static final int XP_PER_TICK = 1;

    public static int getRosaArcanaManaPerXp(BlockAccessor accessor) {
        RosaArcanaBlockEntity blockEntity = (RosaArcanaBlockEntity) accessor.getBlockEntity();

        return ((RosaArcanaManaPerXpAccessor) blockEntity).getManaPerXp();
    }

    public static int getRosaArcanaXpPerSecond(BlockAccessor accessor) {
        RosaArcanaBlockEntity blockEntity = (RosaArcanaBlockEntity) accessor.getBlockEntity();

        return XP_PER_TICK * 20 * (isBoosted(blockEntity) ? 2 : 1);
    }
}