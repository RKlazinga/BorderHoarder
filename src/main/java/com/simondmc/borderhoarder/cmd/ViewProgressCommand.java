package com.simondmc.borderhoarder.cmd;

import com.simondmc.borderhoarder.game.ItemDictionary;
import com.simondmc.borderhoarder.game.ItemHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.lang.Math.round;

public class ViewProgressCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("viewprogress")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                int total = ItemDictionary.getDict().size();
                int collected = ItemHandler.getCollectedItems().size();
                int percentage = round(((float) collected) / ((float) total) * 100.0f);

                p.sendMessage("§eYou have collected " + collected + " of " + total + " items (" + percentage + "%)");
            }
            return true;
        }
        return false;
    }
}
