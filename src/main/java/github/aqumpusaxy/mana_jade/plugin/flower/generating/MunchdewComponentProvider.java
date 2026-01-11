package github.aqumpusaxy.mana_jade.plugin.flower.generating;

import github.aqumpusaxy.mana_jade.mixin.generator.MunchdewCooldownAccessor;
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
import vazkii.botania.common.block.flower.generating.MunchdewBlockEntity;

public enum MunchdewComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();

        if (data.contains("MunchdewManaPerLeaf")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.munchdew_mana_per_leaf",
                            data.getInt("MunchdewManaPerLeaf")
                    )
            );
        }

        if (data.contains("MunchdewLeavesPerSecond")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.munchdew_leaves_per_second",
                            data.getInt("MunchdewLeavesPerSecond")
                    )
            );
        }

        if (data.contains("MunchdewCooldown")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.munchdew_cooldown",
                            DecimalFormatUtil.TWO_DECIMAL_FORMAT.format(data.getDouble("MunchdewCooldown")),
                            "80.00"
                    )
            );
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        MunchdewBlockEntity blockEntity = (MunchdewBlockEntity) accessor.getBlockEntity();

        //每树叶转换魔力
        data.putInt("MunchdewManaPerLeaf", BotaniaFloraCalc.getMunchdewManaPerLeaf());

        //每秒破坏树叶
        data.putInt("MunchdewLeavesPerSecond", BotaniaFloraCalc.getMunchdewLeavesPerSecond());

        //冷却时间
        int cooldown = ((MunchdewCooldownAccessor) blockEntity).getCooldown();
        if (cooldown > 0) {
            data.putDouble("MunchdewCooldown", cooldown / 20D);
        }
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.MUNCHDEW_INFO;
    }
}
