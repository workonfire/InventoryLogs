package pl.workonfire.inventorylogs.storage;

import pl.workonfire.inventorylogs.InventoryLogs;

import java.util.List;

public abstract class StorageManager {
    private static final String STORAGE_PATH = "logs";
    private static final List<String> currentLogs = InventoryLogs.getInstance().getConfig().getStringList(STORAGE_PATH);

    public static void add(String logLine) {
        currentLogs.add(logLine);
        InventoryLogs.getInstance().getConfig().set(STORAGE_PATH, currentLogs);
    }

    public static void remove(String logLine) {
        currentLogs.remove(logLine);
        InventoryLogs.getInstance().getConfig().set(STORAGE_PATH, currentLogs);
    }

    public static void save() {
        InventoryLogs.getInstance().saveConfig();
        InventoryLogs.getInstance().reloadConfig();
    }
}
