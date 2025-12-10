package github.aqumpusaxy.mana_jade.plugin.block_entity_info;

import github.aqumpusaxy.mana_jade.mixin.ManaSpreaderGetBurstInvoker;
import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import github.aqumpusaxy.mana_jade.util.CustomDecimalFormat;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.common.block.block_entity.mana.ManaSpreaderBlockEntity;
import vazkii.botania.common.entity.ManaBurstEntity;

public enum ManaSpreaderBurstComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getServerData().contains("BurstStartingMana")) {
            tooltip.add(Component.translatable("tooltip.mana_jade.mana_spreader_burst_starting_mana",
                    accessor.getServerData().getInt("BurstStartingMana")
            ));

            tooltip.add(Component.translatable("tooltip.mana_jade.mana_spreader_burst_velocity",
                    CustomDecimalFormat.TWO_DECIMAL_FORMAT.format(accessor.getServerData().getDouble("BurstVelocityX")),
                    CustomDecimalFormat.TWO_DECIMAL_FORMAT.format(accessor.getServerData().getDouble("BurstVelocityY")),
                    CustomDecimalFormat.TWO_DECIMAL_FORMAT.format(accessor.getServerData().getDouble("BurstVelocityZ"))
            ));

            tooltip.add(Component.translatable("tooltip.mana_jade.mana_spreader_burst_ticks_before_mana_loss",
                    accessor.getServerData().getInt("BurstTicksBeforeManaLoss")
            ));

            tooltip.add(Component.translatable("tooltip.mana_jade.mana_spreader_burst_mana_loss",
                    accessor.getServerData().getFloat("BurstManaLossPerTick")
            ));
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        ManaSpreaderBlockEntity blockEntity = (ManaSpreaderBlockEntity) accessor.getBlockEntity();
        ManaBurstEntity burst = ((ManaSpreaderGetBurstInvoker) blockEntity).invokeGetBurst(true);

        data.putInt("BurstStartingMana", burst.getStartingMana());

        Vec3 BurstVelocity = burst.getDeltaMovement();
        data.putDouble("BurstVelocityX", BurstVelocity.x);
        data.putDouble("BurstVelocityY", BurstVelocity.y);
        data.putDouble("BurstVelocityZ", BurstVelocity.z);

        data.putFloat("BurstManaLossPerTick", burst.getManaLossPerTick());
        data.putInt("BurstTicksBeforeManaLoss", burst.getMinManaLoss());
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.MANA_SPREADER_BURST_INFO;
    }
}
