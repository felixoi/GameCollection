package net.felixoi.gamecollection.command.arena;

import net.felixoi.gamecollection.GameCollection;
import net.felixoi.gamecollection.Permissions;
import net.felixoi.gamecollection.api.Arena;
import net.felixoi.gamecollection.api.CommandSpecDefined;
import net.felixoi.gamecollection.util.message.MessageTypes;
import net.felixoi.gamecollection.util.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ArenaCommandLeave extends CommandSpecDefined implements CommandExecutor {

    @Override
    public CommandSpec getCommandSpec() {
        return CommandSpec.builder()
                .description(Text.of("Leaves the arena"))
                .permission(Permissions.Arena.LIST)
                .arguments(GenericArguments.none())
                .executor(this)
                .build();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("leave", "l");
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        if(src instanceof Player) {
            Player player = (Player) src;
            Optional<Arena> optionalArena = GameCollection.getInstance().getArenaManager().getArenaByPlayer(player.getUniqueId());

            if(optionalArena.isPresent()) {
                Arena arena = optionalArena.get();
                arena.removePlayer(player);
                MessageUtil.sendMessage(src, MessageTypes.SUCCESS, Text.of("You left the arena ", TextColors.GREEN, arena.getName(), TextColors.WHITE, " successfully."));
                return CommandResult.success();
            } else {
                MessageUtil.sendMessage(src, MessageTypes.ERROR, Text.of("You are currently not in an arena."));
                return CommandResult.empty();
            }
        } else {
            MessageUtil.sendMessage(src, MessageTypes.ERROR, Text.of("This command is only for players!"));
            return CommandResult.empty();
        }
    }

}
