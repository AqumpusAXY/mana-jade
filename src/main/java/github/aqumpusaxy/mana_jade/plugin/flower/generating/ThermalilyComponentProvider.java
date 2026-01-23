package github.aqumpusaxy.mana_jade.plugin.flower.generating;

import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.calc.flora.generating.FluidGeneratorCalc;
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
                            String.format("%.2f", data.getDouble("ThermalilyManaPerSecond"))
                    )
            );
        }

        if (data.contains("ThermalilyBurnTime")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.thermalily_burn_time",
                            String.format("%.2f", data.getDouble("ThermalilyBurnTime"))
                    )
            );
        } else if (data.contains("ThermalilyCooldown") && data.contains("ThermalilyCooldownTime")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.thermalily_cooldown_time",
                            String.format("%.2f", data.getDouble("ThermalilyCooldown")),
                            String.format("%.2f", data.getDouble("ThermalilyCooldownTime"))
                    )
            );
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        //每秒魔力产出
        data.putDouble("ThermalilyManaPerSecond", FluidGeneratorCalc.getFluidGeneratorManaPerSecond(accessor));

        //燃烧时间和冷却时间
        double burnTime = FluidGeneratorCalc.getFluidGeneratorBurnTime(accessor);
        double cooldownTime = FluidGeneratorCalc.getFluidGeneratorCooldown(accessor);

        if (burnTime > 0) {
            data.putDouble("ThermalilyBurnTime", burnTime);
        } else if (cooldownTime > 0) {
            data.putDouble(
                    "ThermalilyCooldown",
                    FluidGeneratorCalc.getFluidGeneratorCooldown(accessor)
            );

            data.putDouble(
                    "ThermalilyCooldownTime",
                    FluidGeneratorCalc.getFluidGeneratorCooldownTime(accessor)
            );
        }
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.THERMALILY_INFO;
    }
}