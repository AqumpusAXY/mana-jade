package github.aqumpusaxy.mana_jade.plugin.block_entity_info;

import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.ElementProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.common.block.block_entity.RunicAltarBlockEntity;

public enum RunicAltarComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
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
        RunicAltarBlockEntity blockEntity = (RunicAltarBlockEntity) accessor.getBlockEntity();
        data.putInt("MaxMana", blockEntity.getTargetMana());
        data.putInt("CurrentMana", blockEntity.getCurrentMana());
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.RUNIC_ALTAR_RECIPE_PROGRESS;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.TAIL - 2;
    }
}
