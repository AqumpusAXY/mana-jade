package github.aqumpusaxy.mana_jade.mixin.generator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import vazkii.botania.common.block.flower.generating.FluidGeneratorBlockEntity;

@Mixin(value = FluidGeneratorBlockEntity.class, remap = false)
public interface FluidGeneratorFieldAccessor {
    @Accessor
    int getManaPerTick();

    @Accessor
    int getBurnTime();

    @Accessor
    int getCooldown();
}
