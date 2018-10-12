package net.felixoi.gamecollection.api;

import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.List;

public interface SignJoinable {

    List<Location<World>> getJoinSigns();

    void addJoinSign(Location<World> location);

    void removeJoinSign(Location<World> location);

    void updateSigns();

}
