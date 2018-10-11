package net.felixoi.gamecollection;

import com.flowpowered.math.vector.Vector3i;
import net.felixoi.gamecollection.api.Arena;
import net.felixoi.gamecollection.api.Minigame;
import org.spongepowered.api.entity.living.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class SimpleArena implements Arena {

    private String name;
    private Integer maxPlayers;
    private List<UUID> currentPlayers;
    private List<Vector3i> joinSigns;

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
    }

    @Override
    public void removePlayer(Player player) {
        checkNotNull(player);

        this.currentPlayers.remove(player.getUniqueId());
    }

    @Override
    public List<Vector3i> getJoinSigns() {
        return this.joinSigns;
    }

    @Override
    public void addJoinSign(Vector3i signPos) {
        checkNotNull(signPos);

        this.joinSigns.add(signPos);
    }

    @Override
    public void removeJoinSign(Vector3i signPos) {
        checkNotNull(signPos);

        this.joinSigns.remove(signPos);
    }

}
