package github.aqumpusaxy.mana_jade.client;

import github.aqumpusaxy.mana_jade.util.ManaPoolCatalystManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientInit {
    @SubscribeEvent
    public static void onPlayerJoin(RecipesUpdatedEvent event) {
        ManaPoolCatalystManager.init();
    }
}
