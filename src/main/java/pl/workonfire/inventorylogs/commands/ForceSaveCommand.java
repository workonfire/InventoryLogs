package pl.workonfire.inventorylogs.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.workonfire.inventorylogs.InventoryLogs;
import pl.workonfire.inventorylogs.storage.StorageManager;
import pl.workonfire.inventorylogs.util.Command;
import pl.workonfire.inventorylogs.util.CommandProcessor;

@Command(name = "logsforcesave")
public class ForceSaveCommand extends CommandProcessor implements CommandExecutor {

    public ForceSaveCommand() {
        register(InventoryLogs.getInstance(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender.hasPermission("inventorylogs.forcesave")) {
            StorageManager.save();
            sender.sendMessage(ChatColor.GREEN + "The InventoryLogs storage was reloaded.");
        }
        return false;
    }
}
