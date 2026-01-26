package github.aqumpusaxy.mana_jade.plugin.mana;

import github.aqumpusaxy.mana_jade.plugin.Identifiers;
import github.aqumpusaxy.mana_jade.util.ElementProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.common.block.block_entity.mana.ManaSpreaderBlockEntity;

public enum ManaSpreaderComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (!config.get(Identifiers.MANA_STORAGE)) return;

        if (accessor.getServerData().contains("MaxMana") && accessor.getServerData().contains("CurrentMana")) {
            int mana = accessor.getServerData().getInt("CurrentMana");
            int maxMana = accessor.getServerData().getInt("MaxMana");
            tooltip.add(ElementProvider.manaProgressElement(mana, maxMana));
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        ManaSpreaderBlockEntity blockEntity = (ManaSpreaderBlockEntity) accessor.getBlockEntity();
        data.putInt("MaxMana", blockEntity.getMaxMana());
        data.putInt("CurrentMana", blockEntity.getCurrentMana());
    }

    @Override
    public ResourceLocation getUid() {
        return Identifiers.MANA_SPREADER_STORAGE;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.TAIL - 2;
    }
}
