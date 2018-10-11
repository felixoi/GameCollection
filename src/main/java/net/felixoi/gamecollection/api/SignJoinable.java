package net.felixoi.gamecollection.api;

import com.flowpowered.math.vector.Vector3i;

import java.util.List;

public interface SignJoinable {

    List<Vector3i> getJoinSigns();

    void addJoinSign(Vector3i signPos);

    void removeJoinSign(Vector3i signPos);

}
