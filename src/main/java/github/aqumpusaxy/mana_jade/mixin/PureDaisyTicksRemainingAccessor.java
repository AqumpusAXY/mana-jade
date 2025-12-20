package github.aqumpusaxy.mana_jade.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import vazkii.botania.common.block.flower.PureDaisyBlockEntity;

@Mixin(value = PureDaisyBlockEntity.class, remap = false)
public interface PureDaisyTicksRemainingAccessor {
    @Accessor
    int[] getTicksRemaining();
}
