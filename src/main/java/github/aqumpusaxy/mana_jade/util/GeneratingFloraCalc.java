package github.aqumpusaxy.mana_jade.util;

import github.aqumpusaxy.mana_jade.mixin.generator.FluidGeneratorManaPerTickAccessor;
import vazkii.botania.common.block.flower.generating.FluidGeneratorBlockEntity;

public class GeneratingFloraCalc {
    public static double getFluidGeneratorManaPerSecond(FluidGeneratorBlockEntity fluidGenerator) {
        int manaPerTick = ((FluidGeneratorManaPerTickAccessor) fluidGenerator).getManaPerTick();
        int generationDelay = fluidGenerator.getGenerationDelay();

        return manaPerTick * 20D / generationDelay;
    }

    public static double getEndoflameManaPerSecond() {
        return 3 * 20D / 2;
    }
}
