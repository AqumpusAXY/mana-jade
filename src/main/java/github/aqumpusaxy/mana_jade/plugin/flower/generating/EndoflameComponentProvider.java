package github.aqumpusaxy.mana_jade.plugin.flower.generating;

import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.BotaniaFloraCalc;
import github.aqumpusaxy.mana_jade.util.DecimalFormatUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum EndoflameComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();

        if (data.contains("EndoflameManaPerSecond")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.endoflame_mana_per_second",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("EndoflameManaPerSecond"))
                    )
            );
        }

        if (data.contains("EndoflameBurnTime")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.endoflame_burn_time",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("EndoflameBurnTime"))
                    )
            );
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        //每秒魔力产出
        data.putDouble(
                "EndoflameManaPerSecond",
                BotaniaFloraCalc.EndoflameCalc.getEndoflameManaPerSecond(accessor)
        );

        //燃烧时间
        data.putDouble(
                "EndoflameBurnTime",
                BotaniaFloraCalc.EndoflameCalc.getEndoflameBurnTime(accessor)
        );
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.ENDOFLAME_INFO;
    }
}
