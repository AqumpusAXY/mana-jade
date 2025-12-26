package github.aqumpusaxy.mana_jade.util;

import github.aqumpusaxy.mana_jade.invoker.ManaStarDeltaManaInvoker;
import github.aqumpusaxy.mana_jade.invoker.PureDaisyTicksRequiredInvoker;
import github.aqumpusaxy.mana_jade.mixin.PureDaisyTicksRemainingAccessor;
import github.aqumpusaxy.mana_jade.mixin.generator.FluidGeneratorFieldAccessor;
import snownee.jade.api.BlockAccessor;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;
import vazkii.botania.common.block.flower.ManastarBlockEntity;
import vazkii.botania.common.block.flower.PureDaisyBlockEntity;
import vazkii.botania.common.block.flower.generating.FluidGeneratorBlockEntity;

public class BotaniaFloraCalc {
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
            double[] resultArr = new double[8];
            for (int i = 0; i < originArr.length; i++) {
                resultArr[i] = isBoosted(blockEntity)
                        ? ticksToSeconds(originArr[i]) * 8 / 2
                        : ticksToSeconds(originArr[i]) * 8;
            }

            return resultArr;
        }
    }

    public static class ManaStarCalc {
        public static double getDeltaMana(BlockAccessor accessor) {
            return ((ManaStarDeltaManaInvoker) accessor.getBlockEntity()).mana_jade$getDeltaMana() / 3D;
        }
    }

    public static double getFluidGeneratorManaPerSecond(FluidGeneratorBlockEntity fluidGenerator) {
        int manaPerTick = ((FluidGeneratorFieldAccessor) fluidGenerator).getManaPerTick();
        int generationDelay = fluidGenerator.getGenerationDelay();

        return manaPerTick * 20D / generationDelay;
    }

    public static double getEndoflameManaPerSecond() {
        return 3 * 20D / 2;
    }

    public static int getRosaArcanaXpPerSecond() {
        return 20;
    }

    public static int getMunchdewManaPerLeaf() {
        return 160;
    }

    public static int getMunchdewLeavesPerSecond() {
        return 4;
    }

    public static double ticksToSeconds(int ticks) {
        return ticks / 20D;
    }

    public static boolean isBoosted(SpecialFlowerBlockEntity flower) {
        return flower.isOnSpecialSoil() && flower.isOvergrowthAffected();
    }
}
