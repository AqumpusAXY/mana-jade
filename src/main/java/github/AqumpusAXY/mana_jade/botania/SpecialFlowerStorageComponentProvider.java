package github.AqumpusAXY.mana_jade.botania;

import github.AqumpusAXY.mana_jade.util.ElementProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.api.block_entity.BindableSpecialFlowerBlockEntity;

public enum SpecialFlowerStorageComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getServerData().contains("MaxMana") && accessor.getServerData().contains("CurrentMana")) {
            int mana = accessor.getServerData().getInt("CurrentMana");
            int maxMana = accessor.getServerData().getInt("MaxMana");
            tooltip.add(ElementProvider.ManaProgressElement(mana, maxMana));
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
