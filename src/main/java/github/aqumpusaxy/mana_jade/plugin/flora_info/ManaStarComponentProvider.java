package github.aqumpusaxy.mana_jade.plugin.flora_info;

import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.BotaniaFloraCalc;
import github.aqumpusaxy.mana_jade.util.ColorUtil;
import github.aqumpusaxy.mana_jade.util.DecimalFormatUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum ManaStarComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();
        if (!data.contains("deltaMana")) return;

        double deltaMana = data.getDouble("deltaMana");

        int hexColor = deltaMana > 0
                ? ColorUtil.rgbToHex(0.05F, 0.05F, 1F)
                : ColorUtil.rgbToHex(1F, 0.05F, 0.05F);

        tooltip.add(
                Component.translatable(
                                "tooltip.mana_jade.mana_star_info",
                                DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(deltaMana)
                        )
                        .withStyle(Style.EMPTY.withColor(hexColor))
        );
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        double deltaMana = BotaniaFloraCalc.ManaStarCalc.getDeltaMana(accessor);

        if (deltaMana == 0) return;

        data.putDouble("deltaMana", deltaMana);
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.MANA_STAR_INFO;
    }
}
