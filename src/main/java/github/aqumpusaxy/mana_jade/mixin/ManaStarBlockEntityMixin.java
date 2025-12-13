package github.aqumpusaxy.mana_jade.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import github.aqumpusaxy.mana_jade.invoker.ManaStarDeltaManaInvoker;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.flower.ManastarBlockEntity;

@Mixin(value = ManastarBlockEntity.class, remap = false)
public abstract class ManaStarBlockEntityMixin implements ManaStarDeltaManaInvoker {
    @Shadow
    private int lastMana;

    @Unique
    private int mana_jade$deltaMana = 0;

    @Override
    @Unique
    public int mana_jade$getDeltaMana() {
        return mana_jade$deltaMana;
    }

    @Inject(method = "tickFlower",
            at = @At(
                    value = "FIELD",
                    target = "Lvazkii/botania/common/block/flower/ManastarBlockEntity;lastMana:I",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void catchDeltaMana(CallbackInfo ci, @Local(name = "mana") int mana) {
        mana_jade$deltaMana = mana - lastMana;
    }
}
