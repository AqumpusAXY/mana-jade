package github.aqumpusaxy.mana_jade.plugin;

import github.aqumpusaxy.mana_jade.ManaJade;
import net.minecraft.resources.ResourceLocation;

public class Identifiers {
    public static final ResourceLocation MISC_FLORA_INFO =
            ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "misc_flora_info");

    //杂项花
    public static final ResourceLocation PURE_DAISY_INFO = FloraIdentifier("pure_daisy_info");
    public static final ResourceLocation MANA_STAR_INFO = FloraIdentifier("mana_star_info");

    private static ResourceLocation FloraIdentifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(ManaJade.MODID, "misc_flora_info." + name);
    }
}
