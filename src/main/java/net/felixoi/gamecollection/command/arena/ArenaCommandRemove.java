package net.felixoi.gamecollection.command.arena;

import net.felixoi.gamecollection.GameCollection;
import net.felixoi.gamecollection.api.Arena;
import net.felixoi.gamecollection.api.CommandSpecDefined;
import net.felixoi.gamecollection.util.message.MessageTypes;
import net.felixoi.gamecollection.util.message.MessageUtil;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ArenaCommandRemove extends CommandSpecDefined implements CommandExecutor {

    @Override
    public CommandSpec getCommandSpec() {
        return CommandSpec.builder()
                .description(Text.of("Removes an existing arena"))
                .permission("gamecollection.arena.remove")
                .arguments(GenericArguments.string(Text.of("name")))
                .executor(this)
                .build();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("remove", "rm");
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        String name = args.<String>getOne("name").get();

        Optional<Arena> arena = GameCollection.getInstance().getArenaManager().getArena(name);
        if(arena.isPresent()) {
            GameCollection.getInstance().getArenaManager().removeArena(arena.get());
            MessageUtil.sendMessage(src, MessageTypes.SUCCESS, Text.of("The arena " + TextColors.GREEN, name, TextColors.WHITE, "has been removed successfully!"));
            return CommandResult.success();
        } else {
            MessageUtil.sendMessage(src, MessageTypes.ERROR, Text.of("There is no arena with the name ", TextColors.RED, name, TextColors.WHITE, "!"));
            return CommandResult.empty();
        }
    }

}
