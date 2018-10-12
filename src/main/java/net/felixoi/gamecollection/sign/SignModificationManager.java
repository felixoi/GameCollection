package net.felixoi.gamecollection.sign;

import net.felixoi.gamecollection.api.Arena;
import org.spongepowered.api.util.Tuple;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public final class SignModificationManager {

    private HashMap<UUID, Tuple<Optional<Arena>, SignModifications>> currentModifications;

    public SignModificationManager() {
        this.currentModifications = new HashMap<>();
    }

    public void addModifier(UUID uuid, Arena arena, SignModifications modification) {
        this.currentModifications.put(checkNotNull(uuid), Tuple.of(Optional.ofNullable(arena), checkNotNull(modification)));
    }

    public void removeModifier(UUID uuid) {
        this.currentModifications.remove(checkNotNull(uuid));
    }

    public boolean isModifying(UUID uuid) {
        return this.currentModifications.containsKey(uuid);
    }

    public Tuple<Optional<Arena>, SignModifications> getModification(UUID uuid) {
        return this.currentModifications.get(checkNotNull(uuid));
    }

}
