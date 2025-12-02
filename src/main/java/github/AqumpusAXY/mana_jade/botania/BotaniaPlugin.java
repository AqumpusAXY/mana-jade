package github.AqumpusAXY.mana_jade.botania;

import github.AqumpusAXY.mana_jade.ManaJade;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.FlowerBlock;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import vazkii.botania.api.block_entity.BindableSpecialFlowerBlockEntity;
import vazkii.botania.common.block.FloatingSpecialFlowerBlock;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;
import vazkii.botania.common.block.block_entity.mana.ManaSpreaderBlockEntity;
import vazkii.botania.common.block.mana.ManaPoolBlock;
import vazkii.botania.common.block.mana.ManaSpreaderBlock;

@WailaPlugin
public class BotaniaPlugin implements IWailaPlugin {
    public static final ResourceLocation BOTANIA_MANA_POOL_STORAGE =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "botania_mana_pool_storage");
    public static final ResourceLocation BOTANIA_MANA_SPREADER_STORAGE =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "botania_mana_spreader_storage");
    public static final ResourceLocation BOTANIA_MANA_SPREADER_BURST_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "botania_mana_spreader_burst_info");
    public static final ResourceLocation BOTANIA_SPECIAL_FLOWER_STORAGE =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "botania_special_flower_storage");


    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(ManaPoolComponentProvider.INSTANCE, ManaPoolBlockEntity.class);
        registration.registerBlockDataProvider(ManaSpreaderComponentProvider.INSTANCE, ManaSpreaderBlockEntity.class);
        registration.registerBlockDataProvider(ManaSpreaderBurstComponentProvider.INSTANCE, ManaSpreaderBlockEntity.class);
        registration.registerBlockDataProvider(SpecialFlowerStorageComponentProvider.INSTANCE, BindableSpecialFlowerBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(ManaPoolComponentProvider.INSTANCE, ManaPoolBlock.class);
        registration.registerBlockComponent(ManaSpreaderComponentProvider.INSTANCE, ManaSpreaderBlock.class);
        registration.registerBlockComponent(ManaSpreaderBurstComponentProvider.INSTANCE, ManaSpreaderBlock.class);
        registration.registerBlockComponent(SpecialFlowerStorageComponentProvider.INSTANCE, FlowerBlock.class);
        registration.registerBlockComponent(SpecialFlowerStorageComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);
    }
}
