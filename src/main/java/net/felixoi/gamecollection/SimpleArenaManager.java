package net.felixoi.gamecollection;

import net.felixoi.gamecollection.api.Arena;
import net.felixoi.gamecollection.api.ArenaManager;

import java.util.ArrayList;
import java.util.List;

public final class SimpleArenaManager implements ArenaManager {

    private List<Arena> arenas;

    public SimpleArenaManager() {
        this.arenas = new ArrayList<>();
    }

    @Override
    public List<Arena> getArenas() {
        return this.arenas;
    }

    @Override
    public void addArena(Arena arena) {
        this.arenas.add(arena);
    }

    @Override
    public void removeArena(Arena arena) {
        this.arenas.remove(arena);
    }

}
