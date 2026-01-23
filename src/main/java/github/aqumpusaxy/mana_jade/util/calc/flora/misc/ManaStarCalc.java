package github.aqumpusaxy.mana_jade.util.calc.flora.misc;

import github.aqumpusaxy.mana_jade.invoker.ManaStarDeltaManaInvoker;
import snownee.jade.api.BlockAccessor;
import vazkii.botania.common.block.flower.ManastarBlockEntity;

import static github.aqumpusaxy.mana_jade.util.calc.flora.CommonFloraCalc.isBoosted;

public class ManaStarCalc {
    private static final double UPDATE_DELAY_SECONDS = 3D;

    public static double getDeltaMana(BlockAccessor accessor) {
        ManastarBlockEntity manastar = (ManastarBlockEntity) accessor.getBlockEntity();

        return ((ManaStarDeltaManaInvoker) manastar).mana_jade$getDeltaMana() / (isBoosted(manastar)
                ? UPDATE_DELAY_SECONDS / 2
                : UPDATE_DELAY_SECONDS);
    }
}