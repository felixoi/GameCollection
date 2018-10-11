package net.felixoi.gamecollection.command.arena;

import net.felixoi.gamecollection.GameCollection;
import net.felixoi.gamecollection.Permissions;
import net.felixoi.gamecollection.SimpleArena;
import net.felixoi.gamecollection.api.ArenaManager;
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

import java.util.Arrays;
import java.util.List;

public class ArenaCommandAdd extends CommandSpecDefined implements CommandExecutor {

    @Override
    public CommandSpec getCommandSpec() {
        return CommandSpec.builder()
                .description(Text.of("Adds a new arena"))
                .permission(Permissions.Arena.ADD)
                .arguments(GenericArguments.seq(
                        GenericArguments.string(Text.of("name")),
                        GenericArguments.integer(Text.of("maxPlayers"))
                ))
                .executor(this)
                .build();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("add", "a");
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        String name = args.<String>getOne("name").get();
        Integer maxPlayers = args.<Integer>getOne("maxPlayers").get();

        if(GameCollection.getInstance().getArenaManager().getArena(name).isPresent()) {
            MessageUtil.sendMessage(src, MessageTypes.ERROR, Text.of("An arena with the name ", TextColors.RED, name, TextColors.WHITE, " already exists!"));
            return CommandResult.empty();
        } else {
            if(maxPlayers <= 1) {
                MessageUtil.sendMessage(src, MessageTypes.ERROR, Text.of("The player limit for an arena has to be higher than 1!"));
                return CommandResult.empty();
            } else {
                GameCollection.getInstance().getArenaManager().addArena(new SimpleArena(name, maxPlayers));
                MessageUtil.sendMessage(src, MessageTypes.SUCCESS, Text.of("The arena ", TextColors.GREEN, name, TextColors.WHITE, " has been created successfully!"));
                return CommandResult.success();
            }
        }
    }

}
