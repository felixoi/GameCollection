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

public class ArenaCommandJoin extends CommandSpecDefined implements CommandExecutor {

    @Override
    public CommandSpec getCommandSpec() {
        return CommandSpec.builder()
                .description(Text.of("Join to a specific arena"))
                .permission(Permissions.Arena.JOIN)
                .arguments(GenericArguments.string(Text.of("arena")))
                .executor(this)
                .build();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("join", "j");
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        if(src instanceof Player) {
            Player player = (Player) src;

            if(GameCollection.getInstance().getArenaManager().getArenaByPlayer((player).getUniqueId()).isPresent()) {
                MessageUtil.sendMessage(src, MessageTypes.ERROR, Text.of("You are already playing in an arena!"));
                return CommandResult.empty();
            } else {
                String arenaName = args.<String>getOne("arena").get();
                Optional<Arena> optionalArena = GameCollection.getInstance().getArenaManager().getArena(arenaName);

                if(optionalArena.isPresent()) {
                    Arena arena = optionalArena.get();

                    if(arena.getCurrentPlayerCount() < arena.getMaxPlayerCount()) {
                        arena.addPlayer(player);
                        MessageUtil.sendMessage(player, MessageTypes.SUCCESS, Text.of("You joined the arena ", TextColors.GREEN, arenaName, TextColors.WHITE, "!"));
                        return CommandResult.success();
                    } else {
                        MessageUtil.sendMessage(player, MessageTypes.ERROR, Text.of("The arena ", TextColors.RED, arenaName, TextColors.WHITE, " reached the maximum player limit! Try again later."));
                        return CommandResult.empty();
                    }
                } else {
                    MessageUtil.sendMessage(player, MessageTypes.ERROR, Text.of("There is no arena with the name ", TextColors.RED, arenaName, TextColors.WHITE, "!"));
                    return CommandResult.empty();
                }
            }
        } else {
            MessageUtil.sendMessage(src, MessageTypes.ERROR, Text.of("This command is only for players!"));
            return CommandResult.empty();
        }
    }

}
