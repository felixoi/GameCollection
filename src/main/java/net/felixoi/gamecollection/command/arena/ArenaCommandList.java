package net.felixoi.gamecollection.command.arena;

import net.felixoi.gamecollection.GameCollection;
import net.felixoi.gamecollection.Permissions;
import net.felixoi.gamecollection.SimpleArena;
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
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArenaCommandList extends CommandSpecDefined implements CommandExecutor {

    @Override
    public CommandSpec getCommandSpec() {
        return CommandSpec.builder()
                .description(Text.of("Lists all arenas"))
                .permission(Permissions.Arena.LIST)
                .arguments(GenericArguments.none())
                .executor(this)
                .build();
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("list");
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        List<Arena> arenas = GameCollection.getInstance().getArenaManager().getArenas();

        if(arenas.size() > 0) {
            MessageUtil.sendMessage(src, MessageTypes.INFO, Text.of("Currently these arenas are present:"));
            String arenaString = StringUtils.join(arenas.stream().map(Arena::getName).toArray(), ", ");
            MessageUtil.sendMessage(src, MessageTypes.INFO, Text.of(arenaString));
        } else {
            MessageUtil.sendMessage(src, MessageTypes.INFO, Text.of("Currently no arenas are present."));
            if(src.hasPermission("gamecollection.arena.add")) {
                MessageUtil.sendMessage(src, MessageTypes.INFO, Text.of("Try adding one by using ", TextColors.GRAY,
                        Text.builder("/gamecollection arena add <name> <maxPlayers>")
                                .onHover(TextActions.showText(Text.of("Click me!")))
                                .onClick(TextActions.suggestCommand("/gamecollection arena add <name> <maxPlayers>"))
                                .build(),
                        TextColors.WHITE, "."));
            }
        }

        return CommandResult.success();
    }

}
