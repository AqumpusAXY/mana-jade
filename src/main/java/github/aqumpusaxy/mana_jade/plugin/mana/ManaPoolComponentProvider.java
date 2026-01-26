package github.aqumpusaxy.mana_jade.plugin.mana;

import github.aqumpusaxy.mana_jade.plugin.Identifiers;
import github.aqumpusaxy.mana_jade.util.ElementProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;

public enum ManaPoolComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (!config.get(Identifiers.MANA_STORAGE)) return;

        if (accessor.getServerData().contains("MaxMana") && accessor.getServerData().contains("CurrentMana")) {
            if (accessor.getBlock() != BotaniaBlocks.creativePool) {
                int mana = accessor.getServerData().getInt("CurrentMana");
                int maxMana = accessor.getServerData().getInt("MaxMana");
                tooltip.add(ElementProvider.manaProgressElement(mana, maxMana));
            } else {
                tooltip.add(ElementProvider.infiniteManaProgressElement());
            }
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        ManaPoolBlockEntity blockEntity = (ManaPoolBlockEntity) accessor.getBlockEntity();
        data.putInt("MaxMana", blockEntity.getMaxMana());
        data.putInt("CurrentMana", blockEntity.getCurrentMana());
    }

    @Override
    public ResourceLocation getUid() {
        return Identifiers.MANA_POOL_STORAGE;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.TAIL - 2;
    }
}
