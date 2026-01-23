package github.aqumpusaxy.mana_jade.util;

import github.aqumpusaxy.mana_jade.invoker.ManaStarDeltaManaInvoker;
import github.aqumpusaxy.mana_jade.invoker.PureDaisyTicksRequiredInvoker;
import github.aqumpusaxy.mana_jade.mixin.PureDaisyTicksRemainingAccessor;
import github.aqumpusaxy.mana_jade.mixin.generator.*;
import snownee.jade.api.BlockAccessor;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;
import vazkii.botania.common.block.flower.ManastarBlockEntity;
import vazkii.botania.common.block.flower.PureDaisyBlockEntity;
import vazkii.botania.common.block.flower.generating.*;

public class BotaniaFloraCalc {
    //TODO:拆分成多个独立的类
    public static class PureDaisyCalc {
        private static final String[] DIR_KEYS = {
                "tooltip.mana_jade.direction.northwest",
                "tooltip.mana_jade.direction.west",
                "tooltip.mana_jade.direction.southwest",
                "tooltip.mana_jade.direction.south",
                "tooltip.mana_jade.direction.southeast",
                "tooltip.mana_jade.direction.east",
                "tooltip.mana_jade.direction.northeast",
                "tooltip.mana_jade.direction.north"
        };
        private static final int DIR_COUNT = 8;

        public static String getDirKeys(int index) {
            return DIR_KEYS[index];
        }

        public static double[] getSecondsRemaining(BlockAccessor accessor) {
            PureDaisyBlockEntity blockEntity = (PureDaisyBlockEntity) accessor.getBlockEntity();

            int[] ticksRemaining = ((PureDaisyTicksRemainingAccessor) blockEntity).getTicksRemaining();

            return arrTicksToSeconds(blockEntity, ticksRemaining);
        }

        public static double[] getSecondsRequired(BlockAccessor accessor) {
            PureDaisyBlockEntity blockEntity = (PureDaisyBlockEntity) accessor.getBlockEntity();

            int[] ticksRequired = ((PureDaisyTicksRequiredInvoker) blockEntity).mana_jade$getTicksRequired();

            return arrTicksToSeconds(blockEntity, ticksRequired);
        }

        private static double[] arrTicksToSeconds(PureDaisyBlockEntity blockEntity, int[] originArr) {
            double[] resultArr = new double[DIR_COUNT];
            for (int i = 0; i < originArr.length; i++) {
                resultArr[i] = ticksToSeconds(originArr[i], blockEntity) * DIR_COUNT;
            }

            return resultArr;
        }
    }

    public static class ManaStarCalc {
        private static final double UPDATE_DELAY_SECONDS = 3D;

        public static double getDeltaMana(BlockAccessor accessor) {
            ManastarBlockEntity manastar = (ManastarBlockEntity) accessor.getBlockEntity();

            return ((ManaStarDeltaManaInvoker) manastar).mana_jade$getDeltaMana() / (isBoosted(manastar)
                    ? UPDATE_DELAY_SECONDS / 2
                    : UPDATE_DELAY_SECONDS);
        }
    }

    public static class FluidGeneratorCalc {
        public static double getFluidGeneratorManaPerSecond(BlockAccessor accessor) {
            FluidGeneratorBlockEntity blockEntity = (FluidGeneratorBlockEntity) accessor.getBlockEntity();

            int manaPerTick = ((FluidGeneratorFieldAccessor) blockEntity).getManaPerTick();
            int generationDelay = blockEntity.getGenerationDelay();

            return manaPerTick * 20D / generationDelay * (isBoosted(blockEntity) ? 2 : 1);
        }

        public static double getFluidGeneratorBurnTime(BlockAccessor accessor) {
            FluidGeneratorBlockEntity blockEntity = (FluidGeneratorBlockEntity) accessor.getBlockEntity();

            int burnTime = ((FluidGeneratorFieldAccessor) blockEntity).getBurnTime();

            return ticksToSeconds(burnTime, blockEntity);
        }

        public static double getFluidGeneratorCooldown(BlockAccessor accessor) {
            FluidGeneratorBlockEntity blockEntity = (FluidGeneratorBlockEntity) accessor.getBlockEntity();

            return ticksToSeconds(((FluidGeneratorFieldAccessor) blockEntity).getCooldown(), blockEntity);
        }

        public static double getFluidGeneratorCooldownTime(BlockAccessor accessor) {
            FluidGeneratorBlockEntity blockEntity = (FluidGeneratorBlockEntity) accessor.getBlockEntity();

            return ticksToSeconds(blockEntity.getCooldownTime(false), blockEntity);
        }

        public static double getHydroangeasDecayTime(BlockAccessor accessor) {
            HydroangeasBlockEntity blockEntity = (HydroangeasBlockEntity) accessor.getBlockEntity();

            return ticksToSeconds(HydroangeasBlockEntity.DECAY_TIME -
                    ((HydroangeasPassiveDecayTicksAccessor) blockEntity).getPassiveDecayTicks(), blockEntity);
        }
    }

    public static class EndoflameCalc {
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

    public static class RosaArcanaCalc{
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

    public static class MunchdewCalc {
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

    public static double ticksToSeconds(int ticks, SpecialFlowerBlockEntity flower) {
        return ticks / 20D / (isBoosted(flower) ? 2 : 1);
    }

    public static boolean isBoosted(SpecialFlowerBlockEntity flower) {
        return flower.isOnSpecialSoil() && flower.isOvergrowthAffected();
    }
}
