package net.felixoi.gamecollection.command;

import net.felixoi.gamecollection.Permissions;
import net.felixoi.gamecollection.api.CommandSpecDefined;
import net.felixoi.gamecollection.command.sign.SignCommandAdd;
import net.felixoi.gamecollection.command.sign.SignCommandRemove;
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

public class SignCommand extends CommandSpecDefined implements CommandExecutor {

    @Override
    public CommandSpec getCommandSpec() {
        registerChildCommandByClass(SignCommandAdd.class);
        registerChildCommandByClass(SignCommandRemove.class);

        return CommandSpec.builder()
                .description(Text.of("Information about the GameCollection plugin"))
                .permission(Permissions.Sign.INFO)
                .arguments(GenericArguments.none())
                .executor(this)
                .build();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("sign", "s");
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        src.sendMessage(Text.of("sign info"));
        return CommandResult.success();
    }

}
