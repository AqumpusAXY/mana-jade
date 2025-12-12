package github.aqumpusaxy.mana_jade.mixin;

import github.aqumpusaxy.mana_jade.invoker.TerraPlateGetRecipeManaInvoker;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import vazkii.botania.api.recipe.TerrestrialAgglomerationRecipe;
import vazkii.botania.common.block.block_entity.TerrestrialAgglomerationPlateBlockEntity;

import java.util.List;

@Mixin(value = TerrestrialAgglomerationPlateBlockEntity.class, remap = false)
public abstract class TerrestrialAgglomerationPlateBlockEntityMixin implements TerraPlateGetRecipeManaInvoker {
    @Shadow
    @Nullable
    protected abstract TerrestrialAgglomerationRecipe getCurrentRecipe(SimpleContainer items);
    @Shadow
    protected abstract SimpleContainer getInventory(List<ItemEntity> itemEntities);
    @Shadow
    protected abstract List<ItemEntity> getItemEntities();

    @Unique
    @Override
    public int mana_jade$getRecipeMana() {
        var recipe = this.getCurrentRecipe(this.getInventory(this.getItemEntities()));
        return recipe == null ? 0: recipe.getMana();
    }
}
