package github.aqumpusaxy.mana_jade.plugin.block_entity_info;

import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.ManaPoolCatalystManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum ManaPoolCatalystComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        var level = Minecraft.getInstance().level;
        if (level == null) return;

        var block = level.getBlockState(accessor.getPosition().below()).getBlock();
        if (ManaPoolCatalystManager.catalysts.contains(block)) {
            tooltip.add(Component.translatable("tooltip.mana_jade.mana_pool_catalyst", block.getName()));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.MANA_POOL_CATALYST;
    }
}
