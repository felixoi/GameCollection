package net.felixoi.gamecollection.api;

import org.spongepowered.api.command.spec.CommandSpec;

import java.util.List;

public interface CommandSpecDefined {

    CommandSpec getCommandSpec();

    List<String> getAliases();

}
