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

public enum HydroangeasComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();

        if (data.contains("HydroangeasManaPerSecond")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.hydroangeas_mana_per_second",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("HydroangeasManaPerSecond"))
                    )
            );
        }

        if (data.contains("HydroangeasDecayTime")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.hydroangeas_decay_time",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("HydroangeasDecayTime"))
                    )
            );
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        //每秒魔力产出
        data.putDouble(
                "HydroangeasManaPerSecond",
                BotaniaFloraCalc.FluidGeneratorCalc.getFluidGeneratorManaPerSecond(accessor)
        );

        //枯萎时间
        data.putDouble(
                "HydroangeasDecayTime",
                BotaniaFloraCalc.FluidGeneratorCalc.getHydroangeasDecayTime(accessor)
        );
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.HYDROANGEAS_INFO;
    }
}
