package net.felixoi.gamecollection.listener;

import net.felixoi.gamecollection.GameCollection;
import net.felixoi.gamecollection.api.Arena;
import net.felixoi.gamecollection.sign.SignModifications;
import net.felixoi.gamecollection.util.message.MessageTypes;
import net.felixoi.gamecollection.util.message.MessageUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.Sign;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.Tuple;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Arrays;
import java.util.Optional;

public class InteractBlockListener implements EventListener<InteractBlockEvent> {

    @Override
    public void handle(InteractBlockEvent event) {
        Optional<Player> optionalPlayer = event.getCause().first(Player.class);
        Optional<Location<World>> optionalLocation = event.getTargetBlock().getLocation();

        if(optionalPlayer.isPresent() && optionalLocation.isPresent()) {
            Player player = optionalPlayer.get();
            Location<World> location = optionalLocation.get();

            if(event.getTargetBlock().getState().getType() == BlockTypes.STANDING_SIGN || event.getTargetBlock().getState().getType() == BlockTypes.WALL_SIGN) {
                Optional<Arena> arenaBySign = GameCollection.getInstance().getArenaManager().getArenaBySign(location);

                if(event instanceof InteractBlockEvent.Primary) {
                    if(GameCollection.getInstance().getSignModificationManager().isModifying(player.getUniqueId())) {
                        Tuple<Optional<Arena>, SignModifications> modification = GameCollection.getInstance().getSignModificationManager().getModification(player.getUniqueId());

                        if(modification.getSecond().equals(SignModifications.CREATE) && modification.getFirst().isPresent()) {
                            Arena arena = modification.getFirst().get();
                            arena.addJoinSign(location);
                            MessageUtil.sendMessage(player, MessageTypes.SUCCESS, Text.of("You successfully added a sign for arena ", TextColors.GREEN, arena.getName(), TextColors.WHITE, "!"));
                            event.setCancelled(true);
                            GameCollection.getInstance().getSignModificationManager().removeModifier(player.getUniqueId());
                        } else if(modification.getSecond().equals(SignModifications.REMOVE) && arenaBySign.isPresent()) {
                            arenaBySign.get().removeJoinSign(location);
                            MessageUtil.sendMessage(player, MessageTypes.SUCCESS, Text.of("You successfully removed a sign for arena ", TextColors.GREEN, arenaBySign.get().getName(), TextColors.WHITE, "!"));
                            GameCollection.getInstance().getSignModificationManager().removeModifier(player.getUniqueId());

                            Sign sign = (Sign) location.getExtent().getTileEntity(location.getBlockPosition()).get();
                            sign.offer(Keys.SIGN_LINES, Arrays.asList(Text.of(), Text.of(), Text.of(), Text.of()));

                            event.setCancelled(true);
                        }
                    } else {
                        arenaBySign.ifPresent(arena -> event.setCancelled(true));
                    }
                } else if(event instanceof InteractBlockEvent.Secondary) {
                    arenaBySign.ifPresent(arena -> Sponge.getCommandManager().process(player, "gc a j " + arena.getName()));
                }
            }
        }
    }

}
