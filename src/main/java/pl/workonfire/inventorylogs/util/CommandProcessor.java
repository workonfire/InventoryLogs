package pl.workonfire.inventorylogs.util;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class CommandProcessor {

    public String getName(Object commandClass) {
        Class<?> clazz = commandClass.getClass();
        if (clazz.isAnnotationPresent(Command.class))
            return clazz.getAnnotation(Command.class).name();
        else {
            System.err.println("WARNING: The class " + clazz + " does not have a proper @Command annotation.");
            return null;
        }
    }

    public void register(JavaPlugin pluginInstance, CommandExecutor commandClass) {
        pluginInstance.getCommand(getName(this)).setExecutor(commandClass);
    }
}
