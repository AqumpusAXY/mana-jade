package github.aqumpusaxy.mana_jade.util.calc.flora.generating;

import github.aqumpusaxy.mana_jade.mixin.generator.MunchdewCooldownAccessor;
import snownee.jade.api.BlockAccessor;
import vazkii.botania.common.block.flower.generating.MunchdewBlockEntity;

import static github.aqumpusaxy.mana_jade.util.calc.flora.misc.CommonFloraCalc.ticksToSeconds;
import static github.aqumpusaxy.mana_jade.util.calc.flora.misc.CommonFloraCalc.isBoosted;

public class MunchdewCalc {
    private static final int MANA_PER_LEAF = 160;
    private static final int LEAVES_PER_SECOND = 4;
    private static final int COOLDOWN_TIME = 1600;

    public static int getMunchdewManaPerLeaf() {
        return MANA_PER_LEAF;
    }

    public static int getMunchdewLeavesPerSecond(BlockAccessor accessor) {
        MunchdewBlockEntity blockEntity = (MunchdewBlockEntity) accessor.getBlockEntity();

        return LEAVES_PER_SECOND * (isBoosted(blockEntity) ? 2 : 1);
    }

    public static double getMunchdewCooldown(BlockAccessor accessor) {
        MunchdewBlockEntity blockEntity = (MunchdewBlockEntity) accessor.getBlockEntity();

        return ticksToSeconds(((MunchdewCooldownAccessor) blockEntity).getCooldown(), blockEntity);
    }

    public static double getMunchdewCooldownTime(BlockAccessor accessor) {
        MunchdewBlockEntity blockEntity = (MunchdewBlockEntity) accessor.getBlockEntity();

        return ticksToSeconds(COOLDOWN_TIME, blockEntity);
    }
}