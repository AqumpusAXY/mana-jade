package github.aqumpusaxy.mana_jade.plugin.flora.generating;

import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.plugin.Identifiers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.common.block.flower.generating.EntropinnyumBlockEntity;

public enum EntropinnyumComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (!config.get(Identifiers.GENERATING_FLORA_INFO)) return;

        CompoundTag data = accessor.getServerData();
        BlockEntity blockEntity = accessor.getBlockEntity();

        if (blockEntity instanceof EntropinnyumBlockEntity && data.contains("CurrentMana")) {
            boolean canAbsorbExplosion = data.getInt("CurrentMana") == 0;

            String key = canAbsorbExplosion
                    ? "tooltip.mana_jade.true"
                    : "tooltip.mana_jade.false";

            int color = canAbsorbExplosion
                    ? 0x00FF00
                    : 0xFF0000;

            tooltip.add(Component.translatable(
                    "tooltip.mana_jade.entropinnyum_can_absorb_explosion",
                    Component.translatable(key).withStyle(style -> style.withColor(color)))
            );
        }
    }

    @Override
    public ResourceLocation getUid() {
        return Identifiers.ENTROPINNYUM_INFO;
    }
}
