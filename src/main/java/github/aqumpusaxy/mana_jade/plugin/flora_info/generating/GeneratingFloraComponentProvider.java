//package github.aqumpusaxy.mana_jade.plugin.flora_info.generating;
//
//import github.aqumpusaxy.mana_jade.mixin.generator.EndoflameBurnTimeAccessor;
//import github.aqumpusaxy.mana_jade.mixin.generator.FluidGeneratorFieldAccessor;
//import github.aqumpusaxy.mana_jade.plugin.BotaniaPlugin;
//import github.aqumpusaxy.mana_jade.util.GeneratingFloraCalc;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import snownee.jade.api.BlockAccessor;
//import snownee.jade.api.IBlockComponentProvider;
//import snownee.jade.api.IServerDataProvider;
//import snownee.jade.api.ITooltip;
//import snownee.jade.api.config.IPluginConfig;
//import vazkii.botania.common.block.flower.generating.EndoflameBlockEntity;
//import vazkii.botania.common.block.flower.generating.FluidGeneratorBlockEntity;
//
//public enum GeneratingFloraComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
//    INSTANCE;
//
//    @Override
//    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
//
//    }
//
//    @Override
//    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
//        BlockEntity blockEntity = accessor.getBlockEntity();
//
//        if (blockEntity instanceof FluidGeneratorBlockEntity fluidGenerator) {
//            if (((FluidGeneratorFieldAccessor) fluidGenerator).getBurnTime() <= 0) return;
//
//            data.putDouble("manaPerSecond", GeneratingFloraCalc.getFluidGeneratorManaPerSecond(fluidGenerator));
//        } else if (blockEntity.getClass().getSimpleName().equals("EndoflameBlockEntity")) {
//            EndoflameBlockEntity endoflame = (EndoflameBlockEntity) blockEntity;
//
//            if (((EndoflameBurnTimeAccessor) endoflame).getBurnTime() <= 0) return;
//
//            data.putDouble("manaPerSecond", GeneratingFloraCalc.getEndoflameManaPerSecond());
//        }
//    }
//
//    @Override
//    public ResourceLocation getUid() {
//        return BotaniaPlugin.GENERATING_FLORA_MANA_PER_SECOND;
//    }
//}
