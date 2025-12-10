package github.aqumpusaxy.mana_jade.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Block;
import vazkii.botania.common.crafting.BlockStateIngredient;
import vazkii.botania.common.crafting.BotaniaRecipeTypes;

import java.util.HashSet;
import java.util.Set;

public class ManaPoolCatalystManager {
    public static Set<Block> catalysts = new HashSet<>();

    public static void init() {
        var level = Minecraft.getInstance().level;
        if (level == null) return;

        var recipes = BotaniaRecipeTypes.getRecipes(level, BotaniaRecipeTypes.MANA_INFUSION_TYPE).values();
        for (var recipe : recipes) {
            if (recipe.getRecipeCatalyst() instanceof BlockStateIngredient catalyst) {
                catalysts.add(catalyst.getBlock());
            }
        }
    }
}
