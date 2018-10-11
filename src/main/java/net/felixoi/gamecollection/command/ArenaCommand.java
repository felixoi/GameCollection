package net.felixoi.gamecollection.command;

import net.felixoi.gamecollection.api.CommandSpecDefined;
import net.felixoi.gamecollection.command.arena.*;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import java.util.Arrays;
import java.util.List;

public class ArenaCommand extends CommandSpecDefined implements CommandExecutor {

    @Override
    public CommandSpec getCommandSpec() {
        registerChildCommandByClass(ArenaCommandAdd.class);
        registerChildCommandByClass(ArenaCommandRemove.class);
        registerChildCommandByClass(ArenaCommandRename.class);
        registerChildCommandByClass(ArenaCommandList.class);

        registerChildCommandsByClasses(ArenaCommandAdd.class, ArenaCommandRemove.class, ArenaCommandRename.class,
                ArenaCommandList.class, ArenaCommandJoin.class, ArenaCommandLeave.class);

        return CommandSpec.builder()
                .description(Text.of("Information about the arena command"))
                .permission("gamecollection.arena.info")
                .arguments(GenericArguments.none())
                .executor(this)
                .children(this.children)
                .build();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("arena", "a");
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        src.sendMessage(Text.of("arena"));
        return CommandResult.success();
    }

}
