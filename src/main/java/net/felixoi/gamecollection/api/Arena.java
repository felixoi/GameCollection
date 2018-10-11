package net.felixoi.gamecollection.api;

public interface Arena {

    String getName();

    Integer getMaxPlayerCount();

    Integer getCurrentPlayerCount();

    Minigame getCurrentMinigame();

}
