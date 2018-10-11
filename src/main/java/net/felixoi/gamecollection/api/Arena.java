package net.felixoi.gamecollection.api;

import org.spongepowered.api.entity.living.player.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Arena extends SignJoinable {

    String getName();

    void setName(String name);

    Integer getMaxPlayerCount();

    Integer getCurrentPlayerCount();

    Optional<Minigame> getCurrentMinigame();

    List<UUID> getPlayers();

    void addPlayer(Player player);

    void removePlayer(Player player);

}
