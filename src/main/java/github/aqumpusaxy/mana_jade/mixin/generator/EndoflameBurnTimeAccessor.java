package github.aqumpusaxy.mana_jade.mixin.generator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import vazkii.botania.common.block.flower.generating.EndoflameBlockEntity;

@Mixin(value = EndoflameBlockEntity.class, remap = false)
public interface EndoflameBurnTimeAccessor {
    @Accessor
    int getBurnTime();
}
