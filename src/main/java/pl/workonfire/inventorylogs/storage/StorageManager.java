package pl.workonfire.inventorylogs.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.workonfire.inventorylogs.InventoryLogs;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class StorageManager {
    private static final String STORAGE_PATH = "logs";
    private static final String FILE_NAME =
            new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date()) + ".yml";
    private static final File STORAGE_FILE = new File(InventoryLogs.getInstance().getDataFolder(), FILE_NAME);
    private static FileConfiguration dataStorage;

    public static void initializeStorage() {
        boolean justCreated = false;
        if (!STORAGE_FILE.exists()) {
            STORAGE_FILE.getParentFile().mkdirs();
            justCreated = true;
        }
        dataStorage = YamlConfiguration.loadConfiguration(STORAGE_FILE);
        if (justCreated) dataStorage.addDefault("logs", new ArrayList<>());
    }

    public static void add(String logLine) {
        List<String> currentLogs = getDataStorage().getStringList(STORAGE_PATH);
        currentLogs.add(logLine);
        getDataStorage().set(STORAGE_PATH, currentLogs);
    }

    public static void save() {
        try {
            getDataStorage().save(STORAGE_FILE);
            dataStorage = YamlConfiguration.loadConfiguration(STORAGE_FILE);
        } catch (IOException exception) {
            System.err.println("An error occured while trying to update the storage file.");
        }
    }

    public static FileConfiguration getDataStorage() {
        return dataStorage;
    }
}
