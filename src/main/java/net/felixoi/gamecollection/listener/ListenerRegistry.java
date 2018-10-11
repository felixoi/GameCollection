package net.felixoi.gamecollection.listener;

import net.felixoi.gamecollection.GameCollection;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;

public final class ListenerRegistry {

    public static void registerListeners(GameCollection instance) {
        Sponge.getEventManager().registerListener(instance, InteractBlockEvent.class, new InteractBlockListener());
        Sponge.getEventManager().registerListener(instance, ChangeBlockEvent.class, new ChangeBlockListener());
    }

}
