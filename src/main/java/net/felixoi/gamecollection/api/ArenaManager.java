package net.felixoi.gamecollection.api;

import java.util.List;

public interface ArenaManager {

    List<Arena> getArenas();

    void addArena(Arena arena);

    void removeArena(Arena arena);

}
