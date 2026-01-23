package github.aqumpusaxy.mana_jade.util.calc.flora.misc;

import snownee.jade.api.BlockAccessor;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;

public class CommonFloraCalc {
    
    public static double ticksToSeconds(int ticks, SpecialFlowerBlockEntity flower) {
        return ticks / 20D / (isBoosted(flower) ? 2 : 1);
    }

    public static boolean isBoosted(SpecialFlowerBlockEntity flower) {
        return flower.isOnSpecialSoil() && flower.isOvergrowthAffected();
    }
}