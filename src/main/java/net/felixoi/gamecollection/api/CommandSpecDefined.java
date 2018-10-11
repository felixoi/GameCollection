package net.felixoi.gamecollection.api;

import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.spec.CommandSpec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CommandSpecDefined {

    protected Map<List<String>, CommandCallable> children;

    public CommandSpecDefined() {
        this.children = new HashMap<>();
    }

    public abstract CommandSpec getCommandSpec();

    public abstract List<String> getAliases();

    protected void registerChildCommandByClass(Class<? extends CommandSpecDefined> clazz) {
        try {
            CommandSpecDefined command = clazz.newInstance();
            this. children.put(command.getAliases(), command.getCommandSpec());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
