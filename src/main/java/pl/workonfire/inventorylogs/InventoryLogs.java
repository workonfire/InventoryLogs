package pl.workonfire.inventorylogs;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.workonfire.inventorylogs.commands.ForceSaveCommand;
import pl.workonfire.inventorylogs.events.InventoryClick;
import pl.workonfire.inventorylogs.storage.StorageManager;

public final class InventoryLogs extends JavaPlugin {
    private static InventoryLogs instance;

    @Override
    public void onEnable() {
        instance = this;
        StorageManager.initializeStorage();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), StorageManager::save, 0, 18000);
        getServer().getPluginManager().registerEvents(new InventoryClick(), getInstance());

        new ForceSaveCommand();

    }

    @Override
    public void onDisable() {
        StorageManager.save();
    }

    public static InventoryLogs getInstance() {
        return instance;
    }
}
