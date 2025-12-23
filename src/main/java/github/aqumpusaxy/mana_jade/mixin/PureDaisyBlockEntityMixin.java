package github.aqumpusaxy.mana_jade.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import github.aqumpusaxy.mana_jade.invoker.PureDaisyTicksRequiredInvoker;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.recipe.PureDaisyRecipe;
import vazkii.botania.common.block.flower.PureDaisyBlockEntity;

@Mixin(value = PureDaisyBlockEntity.class, remap = false)
public abstract class PureDaisyBlockEntityMixin implements PureDaisyTicksRequiredInvoker {
    @Shadow
    private int positionAt;

    @Shadow
    @Final
    private int[] ticksRemaining;

    @Unique
    @Final
    private int[] mana_jade$ticksRequired = new int[8];

    @Unique
    @Override
    public int[] mana_jade$getTicksRequired() {
        return mana_jade$ticksRequired;
    }

    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/recipe/PureDaisyRecipe;getTime()I"))
    public void catchTime(CallbackInfo ci, @Local(name = "recipe") PureDaisyRecipe recipe) {
        mana_jade$ticksRequired[positionAt] = recipe.getTime();
    }

    @Inject(method = "tickFlower", at = @At("TAIL"))
    public void timeUpdate(CallbackInfo ci) {
        if (ticksRemaining[positionAt] == -1) {
            mana_jade$ticksRequired[positionAt] = 0;
        }
    }
}
