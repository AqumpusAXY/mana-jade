package github.aqumpusaxy.mana_jade.plugin.flora.generating;

import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.calc.flora.generating.RosaArcanaCalc;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum RosaArcanaComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;
    //TODO: 该Provider改为纯客户端功能
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();

        if (data.contains("RosaArcanaManaPerXp")) {
            tooltip.add(
                    Component.translatable(
                            "tooltip.mana_jade.rosa_arcana_mana_per_xp",
                            data.getInt("RosaArcanaManaPerXp")
                    )
            );
        }

        if (data.contains("RosaArcanaXpPerSecond")) {
            tooltip.add(
                    Component.translatable(
                            "tooltip.mana_jade.rosa_arcana_xp_per_second",
                            data.getInt("RosaArcanaXpPerSecond")
                    )
            );
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        //每经验转换魔力
        data.putInt(
                "RosaArcanaManaPerXp",
                RosaArcanaCalc.getRosaArcanaManaPerXp(accessor)
        );

        //每秒吸收玩家经验
        data.putInt(
                "RosaArcanaXpPerSecond",
                RosaArcanaCalc.getRosaArcanaXpPerSecond(accessor)
        );
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.ROSA_ARCANA_INFO;
    }
}