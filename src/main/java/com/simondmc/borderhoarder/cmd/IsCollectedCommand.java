package com.simondmc.borderhoarder.cmd;

import com.simondmc.borderhoarder.game.ItemDictionary;
import com.simondmc.borderhoarder.game.ItemHandler;
import com.simondmc.borderhoarder.util.DataTypeUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IsCollectedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("iscollected")) {
            if (args.length < 1) {
                sender.sendMessage("§cUsage: /iscollected <item>");
                return true;
            }

            String itemName = DataTypeUtil.joinStringArray(args, " ");
            Material item;

            List<String> itemList = ItemDictionary.getDict().values().stream().toList();

            List<String> matchingItems = itemList.stream().filter((i) -> i.toLowerCase().contains(itemName.toLowerCase())).toList();

//            if (matchingItems.size() > 50) {
//                sender.sendMessage("Too many matching items!");
//                return true;
//            }
            if (matchingItems.isEmpty()) {
                sender.sendMessage("§c Did not find any items matching '" + itemName + "'!");
                return true;
            }
            List<String> matchingCollected = new ArrayList<>();
            List<String> matchingNotCollected = new ArrayList<>();

            for (String matching: matchingItems) {
                item = DataTypeUtil.getKeyByCaseInsensitiveString(ItemDictionary.getDict(), matching);
                if (ItemHandler.getCollectedItems().containsKey(item)) {
                    matchingCollected.add(ItemDictionary.getDict().get(item));
                } else {
                    matchingNotCollected.add(ItemDictionary.getDict().get(item));
                }
            }

            sender.sendMessage("§eFound " + (matchingCollected.size() + matchingNotCollected.size()) + " items matching '" + itemName + "' (" + matchingCollected.size() + " collected)");

            for (String m : matchingCollected) {
                sender.sendMessage("§a" + m + " has been collected");
            }
            for (String m : matchingNotCollected) {
                sender.sendMessage("§c" + m + " has not been collected");
            }

            return true;
        }
        return false;
    }
}
