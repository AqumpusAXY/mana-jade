package github.aqumpusaxy.mana_jade.plugin.flora_info.generating;

import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.common.block.flower.generating.RosaArcanaBlockEntity;

public enum RosaArcanaComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {

    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        RosaArcanaBlockEntity blockEntity = (RosaArcanaBlockEntity) accessor.getBlockEntity();


    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.ROSA_ARCANA_INFO;
    }
}
