package net.felixoi.gamecollection.command;

import net.felixoi.gamecollection.GameCollection;
import net.felixoi.gamecollection.api.CommandSpecDefined;
import net.felixoi.gamecollection.listener.ChangeBlockListener;
import net.felixoi.gamecollection.listener.InteractBlockListener;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;

public final class CommandRegistry {

    public static void registerCommands() {
        registerByClass(ArenaCommand.class);
        registerByClass(InfoCommand.class);
    }

    private static void registerByClass(Class<? extends CommandSpecDefined> clazz) {
        try {
            CommandSpecDefined command = clazz.newInstance();
            Sponge.getCommandManager().register(GameCollection.getInstance(), command.getCommandSpec(), command.getAliases());
        } catch (InstantiationException | IllegalAccessException e) {
            GameCollection.getInstance().getLogger().error("Failed to register command based on class '" + clazz.getName() + "'!");
        }
    }

}
