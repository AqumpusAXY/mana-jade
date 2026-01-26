package github.aqumpusaxy.mana_jade.plugin;

import github.aqumpusaxy.mana_jade.ManaJade;
import net.minecraft.resources.ResourceLocation;

public class Identifiers {
    public static final ResourceLocation MANA_STORAGE =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "mana_storage");

    public static final ResourceLocation MISC_FLORA_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "misc_flora_info");
    public static final ResourceLocation GENERATING_FLORA_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "generating_flora_info");

    //魔力存储
    public static final ResourceLocation MANA_POOL_STORAGE = MangaStorageIdentifier("mana_pool");
    public static final ResourceLocation MANA_SPREADER_STORAGE = MangaStorageIdentifier("mana_spreader");
    public static final ResourceLocation SPECIAL_FLORA_STORAGE = MangaStorageIdentifier("special_flora");

    //杂项花
    public static final ResourceLocation PURE_DAISY_INFO = MiscFloraIdentifier("pure_daisy_info");
    public static final ResourceLocation MANA_STAR_INFO = MiscFloraIdentifier("mana_star_info");

    //产能花
    public static final ResourceLocation HYDROANGEAS_INFO = GeneratingFloraIdentifier("hydroangeas_info");
    public static final ResourceLocation ENDOFLAME_INFO = GeneratingFloraIdentifier("endoflame_info");
    public static final ResourceLocation THERMALILY_INFO = GeneratingFloraIdentifier("thermalily_info");
    public static final ResourceLocation ROSA_ARCANA_INFO = GeneratingFloraIdentifier("rosa_arcana_info");
    public static final ResourceLocation MUNCHDEW_INFO = GeneratingFloraIdentifier("munchdew_info");
    public static final ResourceLocation ENTROPINNYUM_INFO = GeneratingFloraIdentifier("entropinnyum_info");

    private static ResourceLocation MangaStorageIdentifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "mana_storage." + name);
    }

    private static ResourceLocation MiscFloraIdentifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "misc_flora_info." + name);
    }

    private static ResourceLocation GeneratingFloraIdentifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "generating_flora_info." + name);
    }
}
