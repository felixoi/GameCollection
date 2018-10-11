package net.felixoi.gamecollection.command;

import net.felixoi.gamecollection.GameCollection;
import net.felixoi.gamecollection.api.CommandSpecDefined;
import org.spongepowered.api.Sponge;

public final class CommandRegistry {

    public static void registerCommands() {
        registerByClass(ArenaCommand.class);
        registerByClass(GameCollectionCommand.class);
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
