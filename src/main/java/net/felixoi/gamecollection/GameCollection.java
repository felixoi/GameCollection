package net.felixoi.gamecollection;

import com.google.inject.Inject;
import net.felixoi.gamecollection.api.ArenaManager;
import net.felixoi.gamecollection.listener.ListenerRegistry;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "gamecollection",
        name = "GameCollection",
        description = "A nice minigame collection",
        authors = {
                "felixoi"
        }
)
public class GameCollection {

    @Inject
    private Logger logger;

    private ArenaManager arenaManager;

    @Listener
    public void onInit(GameInitializationEvent event) {
        this.arenaManager = new SimpleArenaManager();

        ListenerRegistry.registerListeners(this);
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Initialized!");
    }

    public ArenaManager getArenaManager() {
        return this.arenaManager;
    }

}
