package github.aqumpusaxy.mana_jade.plugin.flora_info.generating;

import github.aqumpusaxy.mana_jade.mixin.generator.EndoflameBurnTimeAccessor;
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
import vazkii.botania.common.block.flower.generating.EndoflameBlockEntity;

public enum EndoflameComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();

        if (data.contains("EndoflameManaPerSecond")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.endoflame_mana_per_second",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("EndoflameManaPerSecond"))));
        }

        if (data.contains("EndoflameBurnTime")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.endoflame_burn_time",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("EndoflameBurnTime"))));
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        EndoflameBlockEntity blockEntity = (EndoflameBlockEntity) accessor.getBlockEntity();

        //每秒魔力产出
        data.putDouble("EndoflameManaPerSecond", GeneratingFloraCalc.getEndoflameManaPerSecond());

        //燃烧时间
        data.putDouble("EndoflameBurnTime", ((EndoflameBurnTimeAccessor) blockEntity).getBurnTime() / 20D);
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.ENDOFLAME_INFO;
    }
}
