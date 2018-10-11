package net.felixoi.gamecollection.command.sign;

import net.felixoi.gamecollection.Permissions;
import net.felixoi.gamecollection.api.CommandSpecDefined;
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

public class SignCommandAdd extends CommandSpecDefined implements CommandExecutor {

    @Override
    public CommandSpec getCommandSpec() {
        return CommandSpec.builder()
                .description(Text.of("Adds a new join and info sign for a specified arena"))
                .permission(Permissions.Sign.ADD)
                .arguments(GenericArguments.none())
                .executor(this)
                .build();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("add", "a");
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        src.sendMessage(Text.of("add sign"));
        return CommandResult.success();
    }

}
