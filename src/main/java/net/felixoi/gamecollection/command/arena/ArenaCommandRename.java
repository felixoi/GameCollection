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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ArenaCommandRename extends CommandSpecDefined implements CommandExecutor {

    @Override
    public CommandSpec getCommandSpec() {
        return CommandSpec.builder()
                .description(Text.of("Renames an existing arena"))
                .permission("gamecollection.arena.rename")
                .arguments(GenericArguments.seq(
                        GenericArguments.string(Text.of("oldName")),
                        GenericArguments.string(Text.of("newName"))
                ))
                .executor(this)
                .build();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("rename", "rn");
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        String oldName = args.<String>getOne("oldName").get();
        String newName = args.<String>getOne("newName").get();

        Optional<Arena> arena = GameCollection.getInstance().getArenaManager().getArena(oldName);
        if(arena.isPresent()) {
            if(GameCollection.getInstance().getArenaManager().getArena(newName).isPresent()) {
                MessageUtil.sendMessage(src, MessageTypes.ERROR, Text.of("An arena with the name ", TextColors.RED, newName, TextColors.WHITE, " already exists!"));
                return CommandResult.empty();
            } else {
                arena.get().setName(newName);
                MessageUtil.sendMessage(src, MessageTypes.SUCCESS, Text.of("The arena ", TextColors.GREEN, oldName, TextColors.WHITE, " has been renamed to ", TextColors.GREEN, newName, TextColors.WHITE, " successfully!"));
                return CommandResult.success();
            }
        } else {
            MessageUtil.sendMessage(src, MessageTypes.ERROR, Text.of("There is no arena with the name ", TextColors.RED, oldName, TextColors.WHITE, "!"));
            return CommandResult.empty();
        }
    }

}
