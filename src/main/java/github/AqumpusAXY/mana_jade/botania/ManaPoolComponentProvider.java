package github.AqumpusAXY.mana_jade.botania;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;

public enum ManaPoolComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getServerData().contains("MaxMana") && accessor.getServerData().contains("CurrentMana")) {
            if (accessor.getBlock() != BotaniaBlocks.creativePool) {
                tooltip.add(Component.translatable("tooltip.mana_jade.botania_mana_receiver_storage",
                                accessor.getServerData().getInt("CurrentMana"), accessor.getServerData().getInt("MaxMana"))
                        .withStyle(Style.EMPTY.withColor(0x05D5FF)));
            } else {
                tooltip.add(Component.translatable("tooltip.mana_jade.botania_creative_mana_pool_storage")
                        .withStyle(Style.EMPTY.withColor(0x05D5FF)));
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
        return BotaniaPlugin.BOTANIA_MANA_POOL_STORAGE;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.TAIL - 2;
    }
}
