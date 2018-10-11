package net.felixoi.gamecollection.api;

import org.spongepowered.api.entity.living.player.Player;

import java.util.Optional;

public interface Arena extends SignJoinable {

    String getName();

    Integer getMaxPlayerCount();

    Integer getCurrentPlayerCount();

    Optional<Minigame> getCurrentMinigame();

    void addPlayer(Player player);

    void removePlayer(Player player);

}
