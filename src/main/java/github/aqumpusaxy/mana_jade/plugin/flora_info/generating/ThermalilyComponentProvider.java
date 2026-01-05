package github.aqumpusaxy.mana_jade.plugin.flora_info.generating;

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

public enum ThermalilyComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();

        if (data.contains("ThermalilyManaPerSecond")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.thermalily_mana_per_second",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("ThermalilyManaPerSecond"))
                    )
            );
        }

        if (data.contains("ThermalilyBurnTime")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.thermalily_burn_time",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("ThermalilyBurnTime"))
                    )
            );
        } else if (data.contains("ThermalilyCooldown") && data.contains("ThermalilyCooldownTime")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.thermalily_cooldown_time",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("ThermalilyCooldown")),
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("ThermalilyCooldownTime"))
                    )
            );
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        //每秒魔力产出
        data.putDouble("ThermalilyManaPerSecond", BotaniaFloraCalc.FluidGeneratorCalc.getFluidGeneratorManaPerSecond(accessor));

        //燃烧时间和冷却时间
        double burnTime = BotaniaFloraCalc.FluidGeneratorCalc.getFluidGeneratorBurnTime(accessor);
        double cooldownTime = BotaniaFloraCalc.FluidGeneratorCalc.getFluidGeneratorCooldown(accessor);

        if (burnTime > 0) {
            data.putDouble("ThermalilyBurnTime", burnTime);
        } else if (cooldownTime > 0) {
            data.putDouble(
                    "ThermalilyCooldown",
                    BotaniaFloraCalc.FluidGeneratorCalc.getFluidGeneratorCooldown(accessor)
            );

            data.putDouble(
                    "ThermalilyCooldownTime",
                    BotaniaFloraCalc.FluidGeneratorCalc.getFluidGeneratorCooldownTime(accessor)
            );
        }
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.THERMALILY_INFO;
    }
}
