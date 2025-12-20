package github.aqumpusaxy.mana_jade.plugin.flora_info;

import github.aqumpusaxy.mana_jade.invoker.ManaStarDeltaManaInvoker;
import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.ColorUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.common.block.flower.ManastarBlockEntity;

public enum ManaStarComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag serverData = accessor.getServerData();
        if (!serverData.contains("deltaMana")) return;

        int deltaMana = serverData.getInt("deltaMana");
        //TODO: 删除这一行
        if (deltaMana == 0) return;

        int hexColor = deltaMana > 0
                ? ColorUtil.rgbToHex(0.05F, 0.05F, 1F)
                : ColorUtil.rgbToHex(1F, 0.05F, 0.05F);

        tooltip.add(
                Component.translatable("tooltip.mana_jade.mana_star_info", deltaMana)
                        .withStyle(Style.EMPTY.withColor(hexColor))
        );
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        ManastarBlockEntity blockEntity = (ManastarBlockEntity) accessor.getBlockEntity();
        //TODO: 如果deltaMana为0则返回
        data.putInt("deltaMana", ((ManaStarDeltaManaInvoker) blockEntity).mana_jade$getDeltaMana() / 3);
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.MANA_STAR_INFO;
    }
}
