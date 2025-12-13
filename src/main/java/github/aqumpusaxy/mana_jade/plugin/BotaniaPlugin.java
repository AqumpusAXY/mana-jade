package github.aqumpusaxy.mana_jade.plugin;

import github.aqumpusaxy.mana_jade.ManaJade;
import github.aqumpusaxy.mana_jade.plugin.block_entity_info.ManaPoolCatalystComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.block_entity_info.ManaSpreaderBurstComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.block_entity_info.RunicAltarComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.block_entity_info.TerraPlateComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.flora_info.ManaStarComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.flora_info.PureDaisyComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.mana_storage.ManaPoolComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.mana_storage.ManaSpreaderComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.mana_storage.SpecialFlowerComponentProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.FlowerBlock;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import vazkii.botania.api.block_entity.BindableSpecialFlowerBlockEntity;
import vazkii.botania.common.block.FloatingSpecialFlowerBlock;
import vazkii.botania.common.block.block_entity.RunicAltarBlockEntity;
import vazkii.botania.common.block.block_entity.TerrestrialAgglomerationPlateBlockEntity;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;
import vazkii.botania.common.block.block_entity.mana.ManaSpreaderBlockEntity;
import vazkii.botania.common.block.flower.ManastarBlockEntity;
import vazkii.botania.common.block.flower.PureDaisyBlockEntity;
import vazkii.botania.common.block.mana.ManaPoolBlock;
import vazkii.botania.common.block.mana.ManaSpreaderBlock;
import vazkii.botania.common.block.mana.RunicAltarBlock;
import vazkii.botania.common.block.mana.TerrestrialAgglomerationPlateBlock;

@WailaPlugin
public class BotaniaPlugin implements IWailaPlugin {
    public static final ResourceLocation MANA_POOL_STORAGE =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "mana_pool_storage");
    public static final ResourceLocation MANA_SPREADER_STORAGE =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "mana_spreader_storage");
    public static final ResourceLocation SPECIAL_FLOWER_STORAGE =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "special_flower_storage");

    public static final ResourceLocation MANA_POOL_CATALYST =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "mana_pool_catalyst");
    public static final ResourceLocation MANA_SPREADER_BURST_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "mana_spreader_burst_info");
    public static final ResourceLocation RUNIC_ALTAR_RECIPE_PROGRESS =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "runic_altar_recipe_progress");
    public static final ResourceLocation TERRA_PLATE_RECIPE_PROGRESS =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "terra_plate_recipe_progress");

    public static final ResourceLocation PURE_DAISY_RECIPE_PROGRESS =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "pure_daisy_recipe_progress");
    public static final ResourceLocation MANA_STAR_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "mana_star_info");


    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(ManaPoolComponentProvider.INSTANCE, ManaPoolBlockEntity.class);
        registration.registerBlockDataProvider(ManaSpreaderComponentProvider.INSTANCE, ManaSpreaderBlockEntity.class);
        registration.registerBlockDataProvider(SpecialFlowerComponentProvider.INSTANCE, BindableSpecialFlowerBlockEntity.class);

        registration.registerBlockDataProvider(ManaSpreaderBurstComponentProvider.INSTANCE, ManaSpreaderBlockEntity.class);
        registration.registerBlockDataProvider(RunicAltarComponentProvider.INSTANCE, RunicAltarBlockEntity.class);
        registration.registerBlockDataProvider(TerraPlateComponentProvider.INSTANCE, TerrestrialAgglomerationPlateBlockEntity.class);

        registration.registerBlockDataProvider(PureDaisyComponentProvider.INSTANCE, PureDaisyBlockEntity.class);
        registration.registerBlockDataProvider(ManaStarComponentProvider.INSTANCE, ManastarBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(ManaPoolComponentProvider.INSTANCE, ManaPoolBlock.class);
        registration.registerBlockComponent(ManaSpreaderComponentProvider.INSTANCE, ManaSpreaderBlock.class);
        registration.registerBlockComponent(SpecialFlowerComponentProvider.INSTANCE, FlowerBlock.class);
        registration.registerBlockComponent(SpecialFlowerComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);

        registration.registerBlockComponent(ManaPoolCatalystComponentProvider.INSTANCE, ManaPoolBlock.class);
        registration.registerBlockComponent(ManaSpreaderBurstComponentProvider.INSTANCE, ManaSpreaderBlock.class);
        registration.registerBlockComponent(RunicAltarComponentProvider.INSTANCE, RunicAltarBlock.class);
        registration.registerBlockComponent(TerraPlateComponentProvider.INSTANCE, TerrestrialAgglomerationPlateBlock.class);

        registration.registerBlockComponent(PureDaisyComponentProvider.INSTANCE, FlowerBlock.class);
        registration.registerBlockComponent(ManaStarComponentProvider.INSTANCE, FlowerBlock.class);
        registration.registerBlockComponent(PureDaisyComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);
        registration.registerBlockComponent(ManaStarComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);
    }
}
