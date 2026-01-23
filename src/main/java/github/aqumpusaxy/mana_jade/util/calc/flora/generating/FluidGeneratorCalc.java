package github.aqumpusaxy.mana_jade.util.calc.flora.generating;

import github.aqumpusaxy.mana_jade.mixin.generator.FluidGeneratorFieldAccessor;
import github.aqumpusaxy.mana_jade.mixin.generator.HydroangeasPassiveDecayTicksAccessor;
import snownee.jade.api.BlockAccessor;
import vazkii.botania.common.block.flower.generating.FluidGeneratorBlockEntity;
import vazkii.botania.common.block.flower.generating.HydroangeasBlockEntity;

import static github.aqumpusaxy.mana_jade.util.calc.flora.misc.CommonFloraCalc.ticksToSeconds;
import static github.aqumpusaxy.mana_jade.util.calc.flora.misc.CommonFloraCalc.isBoosted;

public class FluidGeneratorCalc {
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