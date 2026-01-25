package github.aqumpusaxy.mana_jade.plugin.flora.generating;

import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.NumberFormatter;
import github.aqumpusaxy.mana_jade.util.calc.flora.generating.MunchdewCalc;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum MunchdewComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();

        if (data.contains("MunchdewManaPerLeaf")) {
            tooltip.add(
                    Component.translatable(
                            "tooltip.mana_jade.munchdew_mana_per_leaf",
                            data.getInt("MunchdewManaPerLeaf")
                    )
            );
        }

        if (data.contains("MunchdewLeavesPerSecond")) {
            tooltip.add(
                    Component.translatable(
                            "tooltip.mana_jade.munchdew_leaves_per_second",
                            data.getInt("MunchdewLeavesPerSecond")
                    )
            );
        }

        if (data.contains("MunchdewCooldown")) {
            tooltip.add(
                    Component.translatable("tooltip.mana_jade.munchdew_cooldown",
                            NumberFormatter.formatDouble(data.getDouble("MunchdewCooldown")),
                            NumberFormatter.formatDouble(data.getDouble("MunchdewCooldownPercent"))
                    )
            );
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        //每树叶转换魔力
        data.putInt(
                "MunchdewManaPerLeaf",
                MunchdewCalc.getMunchdewManaPerLeaf()
        );

        //每秒破坏树叶
        data.putInt(
                "MunchdewLeavesPerSecond",
                MunchdewCalc.getMunchdewLeavesPerSecond(accessor)
        );

        //冷却时间
        double cooldown = MunchdewCalc.getMunchdewCooldown(accessor);
        double cooldownTime = MunchdewCalc.getMunchdewCooldownTime(accessor);
        if (cooldown > 0) {
            data.putDouble("MunchdewCooldown", cooldown);
            data.putDouble("MunchdewCooldownPercent", cooldownTime);
        }
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.MUNCHDEW_INFO;
    }
}