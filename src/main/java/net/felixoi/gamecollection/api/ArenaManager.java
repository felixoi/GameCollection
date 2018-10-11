package net.felixoi.gamecollection.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArenaManager {

    List<Arena> getArenas();

    void addArena(Arena arena);

    void removeArena(Arena arena);

    Optional<Arena> getArena(String name);

    Optional<Arena> getArenaByPlayer(UUID uuid);

}
