package net.felixoi.gamecollection.arena;

import com.flowpowered.math.vector.Vector3i;
import net.felixoi.gamecollection.GameCollection;
import net.felixoi.gamecollection.api.Arena;
import net.felixoi.gamecollection.api.Minigame;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.Sign;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public final class SimpleArena implements Arena {

    private String name;
    private Integer maxPlayers;
    private List<UUID> currentPlayers;
    private List<Location<World>> joinSigns;

    public SimpleArena(String name, Integer maxPlayers) {
        this.name = checkNotNull(name);
        this.maxPlayers = checkNotNull(maxPlayers);
        this.currentPlayers = new ArrayList<>();
        this.joinSigns = new ArrayList<>();

        checkArgument(!this.name.trim().equals(""));
        checkArgument(this.maxPlayers > 1);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = checkNotNull(name);
        this.updateSigns();
    }

    @Override
    public Integer getMaxPlayerCount() {
        return this.maxPlayers;
    }

    @Override
    public Integer getCurrentPlayerCount() {
        return this.currentPlayers.size();
    }

    @Override
    public Optional<Minigame> getCurrentMinigame() {
        return Optional.empty();
    }

    @Override
    public List<UUID> getPlayers() {
        return this.currentPlayers;
    }

    @Override
    public void addPlayer(Player player) {
        checkNotNull(player);

        this.currentPlayers.add(player.getUniqueId());
        this.updateSigns();
    }

    @Override
    public void removePlayer(Player player) {
        checkNotNull(player);

        this.currentPlayers.remove(player.getUniqueId());
        this.updateSigns();
    }

    @Override
    public List<Location<World>> getJoinSigns() {
        return this.joinSigns;
    }

    @Override
    public void addJoinSign(Location<World> location) {
        checkNotNull(location);

        this.joinSigns.add(location);
        this.updateSigns();
    }

    @Override
    public void removeJoinSign(Location<World> location) {
        checkNotNull(location);

        this.joinSigns.remove(location);
        this.updateSigns();
    }

    @Override
    public void updateSigns() {
        this.joinSigns.forEach(location -> {
            if(location.getBlockType() == BlockTypes.WALL_SIGN || location.getBlockType() == BlockTypes.STANDING_SIGN) {
                Sign sign = (Sign) location.getExtent().getTileEntity(location.getBlockPosition()).get();
                
                DataTransactionResult result = sign.offer(Keys.SIGN_LINES, Arrays.asList(
                        Text.of(this.name),
                        Text.of(),
                        Text.of(this.currentPlayers.size(), " / ", this.maxPlayers),
                        this.currentPlayers.size() < this.maxPlayers ? Text.of(TextColors.DARK_GREEN, "[Click to join]") : Text.of(TextColors.RED, "[Full]")
                ));

                if(!result.isSuccessful()) {
                    GameCollection.getInstance().getLogger().warn("Failed to update sign for arena '" + this.name + "'!" +
                            "Sign at location " + location.toString() + " failed to update because of an unknown exception!");
                }
            } else {
                GameCollection.getInstance().getLogger().warn("Failed to update sign for arena '" + this.name + "'!" +
                        "Block at location " + location.toString() + " is not a sign!");
            }
        });
    }

}
