package pl.workonfire.inventorylogs.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pl.workonfire.inventorylogs.storage.StorageManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked().getGameMode() == GameMode.CREATIVE) {
            String playerName = event.getWhoClicked().getName();
            ItemStack item = event.getCurrentItem();
            String date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
            String finalText = String.format("%s | %s | %s", playerName, item, date);
            StorageManager.add(finalText);
        }
    }
}
