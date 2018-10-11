package net.felixoi.gamecollection.command;

import net.felixoi.gamecollection.api.CommandSpecDefined;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import java.util.Arrays;
import java.util.List;

public class GameCollectionCommand extends CommandSpecDefined {

    @Override
    public CommandSpec getCommandSpec() {
        registerChildCommandByClass(ArenaCommand.class);
        registerChildCommandByClass(InfoCommand.class);
        registerChildCommandByClass(SignCommand.class);

        return CommandSpec.builder()
                .description(Text.of("Information about the GameCollection plugin"))
                .permission("gamecollection.info")
                .arguments(GenericArguments.none())
                .executor(new InfoCommand())
                .children(this.children)
                .build();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("gamecollection", "gamecoll", "gc");
    }

}
