package github.aqumpusaxy.mana_jade.plugin.flora_info;

import github.aqumpusaxy.mana_jade.accessor.PureDaisyTicksRequiredAccessor;
import github.aqumpusaxy.mana_jade.mixin.PureDaisyTicksRemainingAccessor;
import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;
import vazkii.botania.common.block.flower.PureDaisyBlockEntity;

public enum PureDaisyComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getServerData().contains("TimeRemaining")) {
            int[] timeRequired = accessor.getServerData().getIntArray("TimeRequired");
            int[] timeRemaining = accessor.getServerData().getIntArray("TimeRemaining");
            boolean isBoosted = isBoosted((SpecialFlowerBlockEntity) accessor.getBlockEntity());
            for (int i = 0; i < timeRemaining.length; i++) {
                if (timeRemaining[i] < 0) {
                    tooltip.add(Component.translatable("tooltip.mana_jade.botania_pure_daisy_no_recipe",
                            Component.translatable(getDirectionName(i))));
                } else {
                    tooltip.add(Component.translatable("tooltip.mana_jade.botania_pure_daisy_recipe_progress",
                            Component.translatable(getDirectionName(i)), timeToSeconds(timeRemaining[i], isBoosted),
                            timeToSeconds(timeRequired[i], isBoosted)));
                }
            }
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        PureDaisyBlockEntity blockEntity = (PureDaisyBlockEntity) accessor.getBlockEntity();
        int[] ticksRequired = ((PureDaisyTicksRequiredAccessor) blockEntity).mana_jade$getTicksRequired();
        int[] ticksRemaining = ((PureDaisyTicksRemainingAccessor) blockEntity).getTicksRemaining();
        data.putIntArray("TimeRequired", ticksRequired);
        data.putIntArray("TimeRemaining", ticksRemaining);
    }

    @Override
    public ResourceLocation getUid() {
        return BotaniaPlugin.BOTANIA_PURE_DAISY_RECIPE_PROGRESS;
    }

    private static String getDirectionName(int i) {
        return switch (i) {
            case 0 -> "tooltip.mana_jade.direction.northwest";
            case 1 -> "tooltip.mana_jade.direction.west";
            case 2 -> "tooltip.mana_jade.direction.southwest";
            case 3 -> "tooltip.mana_jade.direction.south";
            case 4 -> "tooltip.mana_jade.direction.southeast";
            case 5 -> "tooltip.mana_jade.direction.east";
            case 6 -> "tooltip.mana_jade.direction.northeast";
            case 7 -> "tooltip.mana_jade.direction.north";
            default -> throw new IllegalArgumentException("Invalid direction index: " + i);
        };
    }

    private static float timeToSeconds(int time, boolean isBoosted) {
        return time * 8 / 20f / (isBoosted ? 2 : 1);
    }

    private static boolean isBoosted(SpecialFlowerBlockEntity blockEntity) {
        return blockEntity.isOnSpecialSoil() && blockEntity.isOvergrowthAffected();
    }
}
