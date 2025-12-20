package github.aqumpusaxy.mana_jade.mixin.generator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import vazkii.botania.common.block.flower.generating.HydroangeasBlockEntity;

@Mixin(value = HydroangeasBlockEntity.class, remap = false)
public interface HydroangeasPassiveDecayTicksAccessor {
    @Accessor
    int getPassiveDecayTicks();
}
