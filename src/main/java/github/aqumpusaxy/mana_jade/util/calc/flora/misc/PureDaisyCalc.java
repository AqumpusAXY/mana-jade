package github.aqumpusaxy.mana_jade.util.calc.flora.misc;

import github.aqumpusaxy.mana_jade.invoker.PureDaisyTicksRequiredInvoker;
import github.aqumpusaxy.mana_jade.mixin.PureDaisyTicksRemainingAccessor;
import snownee.jade.api.BlockAccessor;
import vazkii.botania.common.block.flower.PureDaisyBlockEntity;

import static github.aqumpusaxy.mana_jade.util.calc.flora.CommonFloraCalc.ticksToSeconds;

public class PureDaisyCalc {
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