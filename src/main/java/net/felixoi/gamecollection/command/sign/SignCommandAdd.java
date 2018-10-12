package net.felixoi.gamecollection.command.sign;

import net.felixoi.gamecollection.GameCollection;
import net.felixoi.gamecollection.Permissions;
import net.felixoi.gamecollection.api.Arena;
import net.felixoi.gamecollection.api.CommandSpecDefined;
import net.felixoi.gamecollection.sign.SignModifications;
import net.felixoi.gamecollection.util.message.MessageTypes;
import net.felixoi.gamecollection.util.message.MessageUtil;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SignCommandAdd extends CommandSpecDefined implements CommandExecutor {

    @Override
    public CommandSpec getCommandSpec() {
        return CommandSpec.builder()
                .description(Text.of("Adds a new join and info sign for a specified arena"))
                .permission(Permissions.Sign.ADD)
                .arguments(GenericArguments.string(Text.of("arena")))
                .executor(this)
                .build();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("add", "a");
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        String arenaName = args.<String>getOne("arena").get();

        if(src instanceof Player) {
            Player player = (Player) src;
            Optional<Arena> optionalArena = GameCollection.getInstance().getArenaManager().getArena(arenaName);

            if(optionalArena.isPresent()) {
                GameCollection.getInstance().getSignModificationManager().addModifier(player.getUniqueId(), optionalArena.get(), SignModifications.CREATE);
                MessageUtil.sendMessage(player, MessageTypes.SUCCESS, Text.of("Left click on a sign to create the arena sign!"));
                return CommandResult.success();
            } else {
                MessageUtil.sendMessage(player, MessageTypes.ERROR, Text.of("There is no arena with the name ", TextColors.RED, arenaName, TextColors.WHITE, "!"));
                return CommandResult.empty();
            }
        } else {
            MessageUtil.sendMessage(src, MessageTypes.ERROR, Text.of("This command is only for players!"));
            return CommandResult.empty();
        }
    }

}
