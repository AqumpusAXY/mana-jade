package github.aqumpusaxy.mana_jade.plugin.block_entity_info;

import github.aqumpusaxy.mana_jade.invoker.TerraPlateGetRecipeManaInvoker;
import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.ElementProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.common.block.block_entity.TerrestrialAgglomerationPlateBlockEntity;

public enum TerraPlateComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getServerData().contains("MaxMana") && accessor.getServerData().getInt("MaxMana") > 0) {
            int mana = accessor.getServerData().getInt("CurrentMana");
            int maxMana = accessor.getServerData().getInt("MaxMana");
            tooltip.add(ElementProvider.manaProgressElement(mana, maxMana));
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        TerrestrialAgglomerationPlateBlockEntity blockEntity = (TerrestrialAgglomerationPlateBlockEntity) accessor.getBlockEntity();
        data.putInt("MaxMana", ((TerraPlateGetRecipeManaInvoker) blockEntity).mana_jade$getRecipeMana());
        data.putInt("CurrentMana", blockEntity.getCurrentMana());
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.TERRA_PLATE_RECIPE_PROGRESS;
    }
}
