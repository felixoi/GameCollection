package net.felixoi.gamecollection;

import net.felixoi.gamecollection.api.Arena;
import net.felixoi.gamecollection.api.ArenaManager;
import org.spongepowered.api.entity.living.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

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
        checkNotNull(arena);

        this.arenas.add(arena);
    }

    @Override
    public void removeArena(Arena arena) {
        checkNotNull(arena);

        this.arenas.remove(arena);
    }

    @Override
    public Optional<Arena> getArena(String name) {
        return this.arenas.stream().filter(arena -> arena.getName().equals(name)).findFirst();
    }

    @Override
    public Optional<Arena> getArenaByPlayer(UUID uuid) {
        return this.arenas.stream().filter(arena -> arena.getPlayers().contains(uuid)).findFirst();
    }

}
