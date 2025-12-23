package github.aqumpusaxy.mana_jade.plugin.flora_info.generating;

import github.aqumpusaxy.mana_jade.mixin.generator.FluidGeneratorFieldAccessor;
import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.DecimalFormatUtil;
import github.aqumpusaxy.mana_jade.util.BotaniaFloraCalc;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.common.block.flower.generating.ThermalilyBlockEntity;

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
        ThermalilyBlockEntity blockEntity = (ThermalilyBlockEntity) accessor.getBlockEntity();

        //每秒魔力产出
        data.putDouble("ThermalilyManaPerSecond", BotaniaFloraCalc.getFluidGeneratorManaPerSecond(blockEntity));

        //燃烧时间和冷却时间
        int burnTime = ((FluidGeneratorFieldAccessor) blockEntity).getBurnTime();
        if (burnTime > 0) {
            data.putDouble("ThermalilyBurnTime", burnTime / 20D);
        } else {
            data.putDouble("ThermalilyCooldown", ((FluidGeneratorFieldAccessor) blockEntity).getCooldown() /20D);
            data.putDouble("ThermalilyCooldownTime", blockEntity.getCooldownTime(false) / 20D);
        }
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.THERMALILY_INFO;
    }
}
