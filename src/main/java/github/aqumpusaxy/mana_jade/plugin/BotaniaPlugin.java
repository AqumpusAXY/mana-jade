package github.aqumpusaxy.mana_jade.plugin;

import github.aqumpusaxy.mana_jade.ManaJade;
import github.aqumpusaxy.mana_jade.plugin.flower.ManaStarComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.flower.PureDaisyComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.flower.generating.*;
import github.aqumpusaxy.mana_jade.plugin.machine.ManaPoolCatalystComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.machine.ManaSpreaderBurstComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.machine.RunicAltarComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.machine.TerraPlateComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.mana.ManaPoolComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.mana.ManaSpreaderComponentProvider;
import github.aqumpusaxy.mana_jade.plugin.mana.SpecialFlowerComponentProvider;
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
import vazkii.botania.common.block.flower.generating.*;
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
    public static final ResourceLocation SPECIAL_FLORA_STORAGE =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "special_flora_storage");

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

    public static final ResourceLocation HYDROANGEAS_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "hydroangeas_info");
    public static final ResourceLocation ENDOFLAME_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "endoflame_info");
    public static final ResourceLocation THERMALILY_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "thermalily_info");
    public static final ResourceLocation ROSA_ARCANA_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "rosa_arcana_info");
    public static final ResourceLocation MUNCHDEW_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "munchdew_info");
    public static final ResourceLocation ENTROPINNYUM_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "entropinnyum_info");

    public static final ResourceLocation[] CLIENT_FEATURES = {
            MANA_POOL_CATALYST
    };

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
        registration.registerBlockDataProvider(HydroangeasComponentProvider.INSTANCE, HydroangeasBlockEntity.class);
        registration.registerBlockDataProvider(EndoflameComponentProvider.INSTANCE, EndoflameBlockEntity.class);
        registration.registerBlockDataProvider(ThermalilyComponentProvider.INSTANCE, ThermalilyBlockEntity.class);
        registration.registerBlockDataProvider(RosaArcanaComponentProvider.INSTANCE, RosaArcanaBlockEntity.class);
        registration.registerBlockDataProvider(MunchdewComponentProvider.INSTANCE, MunchdewBlockEntity.class);
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
        registration.registerBlockComponent(HydroangeasComponentProvider.INSTANCE, FlowerBlock.class);
        registration.registerBlockComponent(EndoflameComponentProvider.INSTANCE, FlowerBlock.class);
        registration.registerBlockComponent(ThermalilyComponentProvider.INSTANCE, FlowerBlock.class);
        registration.registerBlockComponent(RosaArcanaComponentProvider.INSTANCE, FlowerBlock.class);
        registration.registerBlockComponent(MunchdewComponentProvider.INSTANCE, FlowerBlock.class);
        registration.registerBlockComponent(EntropinnyumComponentProvider.INSTANCE, FlowerBlock.class);

        registration.registerBlockComponent(PureDaisyComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);
        registration.registerBlockComponent(ManaStarComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);
        registration.registerBlockComponent(HydroangeasComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);
        registration.registerBlockComponent(EndoflameComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);
        registration.registerBlockComponent(ThermalilyComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);
        registration.registerBlockComponent(RosaArcanaComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);
        registration.registerBlockComponent(MunchdewComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);
        registration.registerBlockComponent(EntropinnyumComponentProvider.INSTANCE, FloatingSpecialFlowerBlock.class);

        markClientFeatures(registration);
    }

    private static void markClientFeatures(IWailaClientRegistration registration) {
        for (ResourceLocation feature : CLIENT_FEATURES) {
            registration.markAsClientFeature(feature);
        }
    }
}
