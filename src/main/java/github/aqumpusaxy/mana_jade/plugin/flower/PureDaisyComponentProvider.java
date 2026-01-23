package github.aqumpusaxy.mana_jade.plugin.flower;

import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.calc.flora.misc.PureDaisyCalc;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum PureDaisyComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();

        for (int i = 0; i < 8; i++) {
            if (!(data.contains("PureDaisyTimeRemaining" + i) && data.contains("PureDaisyTimeRequired" + i))) continue;

            double timeRemaining = data.getDouble("PureDaisyTimeRemaining" + i);
            double timeRequired = data.getDouble("PureDaisyTimeRequired" + i);

            tooltip.add(
                    Component.translatable(
                            "tooltip.mana_jade.pure_daisy_recipe_progress",
                            Component.translatable(PureDaisyCalc.getDirKeys(i)),
                            String.format("%.2f", timeRemaining),
                            String.format("%.2f", timeRequired)
                    )
            );
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        for (int i = 0; i < 8; i++) {
            double timeRemaining = PureDaisyCalc.getSecondsRemaining(accessor)[i];
            double timeRequired = PureDaisyCalc.getSecondsRequired(accessor)[i];

            if (timeRemaining <= 0) continue;

            data.putDouble(
                    "PureDaisyTimeRemaining" + i,
                    timeRemaining
            );

            data.putDouble(
                    "PureDaisyTimeRequired" + i,
                    timeRequired
            );
        }
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.PURE_DAISY_RECIPE_PROGRESS;
    }
}