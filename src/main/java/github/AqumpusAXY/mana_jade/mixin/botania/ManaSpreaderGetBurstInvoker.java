package github.AqumpusAXY.mana_jade.mixin.botania;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import vazkii.botania.common.block.block_entity.mana.ManaSpreaderBlockEntity;
import vazkii.botania.common.entity.ManaBurstEntity;

@Mixin(value = ManaSpreaderBlockEntity.class, remap = false)
public interface ManaSpreaderGetBurstInvoker {
    @Invoker("getBurst")
    ManaBurstEntity invokeGetBurst(boolean fake);
}
