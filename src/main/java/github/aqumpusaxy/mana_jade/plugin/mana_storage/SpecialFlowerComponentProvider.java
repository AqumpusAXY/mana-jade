package github.aqumpusaxy.mana_jade.plugin.mana_storage;

import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.ElementProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.api.block_entity.BindableSpecialFlowerBlockEntity;

public enum SpecialFlowerComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getServerData().contains("MaxMana") && accessor.getServerData().contains("CurrentMana")) {
            int mana = accessor.getServerData().getInt("CurrentMana");
            int maxMana = accessor.getServerData().getInt("MaxMana");
            tooltip.add(ElementProvider.manaProgressElement(mana, maxMana));
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BindableSpecialFlowerBlockEntity<?> blockEntity = (BindableSpecialFlowerBlockEntity<?>) accessor.getBlockEntity();
        data.putInt("MaxMana", blockEntity.getMaxMana());
        data.putInt("CurrentMana", blockEntity.getMana());
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.BOTANIA_SPECIAL_FLOWER_STORAGE;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.TAIL - 2;
    }
}
