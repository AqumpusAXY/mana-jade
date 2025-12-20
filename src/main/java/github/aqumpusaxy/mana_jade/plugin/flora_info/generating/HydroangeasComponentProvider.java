package github.aqumpusaxy.mana_jade.plugin.flora_info.generating;

import github.aqumpusaxy.mana_jade.mixin.generator.HydroangeasPassiveDecayTicksAccessor;
import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.DecimalFormatUtil;
import github.aqumpusaxy.mana_jade.util.GeneratingFloraCalc;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.common.block.flower.generating.HydroangeasBlockEntity;

public enum HydroangeasComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();

        if (data.contains("ManaPerSecond")) {
            tooltip.add(
                    Component.translatable("tootip.mana_jade.hydroangeas_mana_per_second",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("ManaPerSecond")))
            );
        }

        if (data.contains("DecayTime")) {
            tooltip.add(
                    Component.translatable("tootip.mana_jade.hydroangeas_decay_time",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("DecayTime")))
            );
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        HydroangeasBlockEntity blockEntity = (HydroangeasBlockEntity) accessor.getBlockEntity();

        //每秒魔力产出
        data.putDouble("ManaPerSecond", GeneratingFloraCalc.getFluidGeneratorManaPerSecond(blockEntity));

        //枯萎时间
        data.putDouble(
                "DecayTime", (HydroangeasBlockEntity.DECAY_TIME -
                        ((HydroangeasPassiveDecayTicksAccessor) blockEntity).getPassiveDecayTicks()) / 20D
        );
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.HYDROANGEAS_INFO;
    }
}
