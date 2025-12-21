package github.aqumpusaxy.mana_jade.mixin.generator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import vazkii.botania.common.block.flower.generating.RosaArcanaBlockEntity;

@Mixin(value = RosaArcanaBlockEntity.class, remap = false)
public interface RosaArcanaManaPerXpAccessor {
    @Accessor("MANA_PER_XP")
    int getManaPerXp();
}
