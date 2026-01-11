package com.simondmc.borderhoarder.cmd;

import com.simondmc.borderhoarder.game.ItemDictionary;
import com.simondmc.borderhoarder.game.ItemHandler;
import com.simondmc.borderhoarder.util.DataTypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.round;

public class IsCollectedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("iscollected")) {
            if (args.length < 1) {
                sender.sendMessage("§cUsage: /iscollected <pattern>");
                return true;
            }

            String queryString = StringUtils.join(args, " ");
            List<String> negativeArgs = Arrays.stream(args).filter((a) -> a.startsWith("-")).map((a) -> a.substring(1)).toList();
            List<String> positiveArgs = Arrays.stream(args).filter((a) -> !a.startsWith("-")).toList();

            String itemName = StringUtils.join(positiveArgs, " ");
            Material item;

            List<String> itemList = ItemDictionary.getDict().values().stream().toList();

            List<String> matchingItems = itemList.stream().filter((i) -> i.toLowerCase().contains(itemName.toLowerCase())).toList();

            for (String neg: negativeArgs) {
                matchingItems = matchingItems.stream().filter((m) -> !m.toLowerCase().contains(neg.toLowerCase())).toList();
            }

            if (matchingItems.isEmpty()) {
                sender.sendMessage("§c Did not find any items matching '" + queryString + "'!");
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

            sender.sendMessage("§eResults for '" + queryString + "':");

            for (String m : matchingCollected) {
                sender.sendMessage("§a☑ " + m);
            }
            for (String m : matchingNotCollected) {
                sender.sendMessage("§c☐ " + m);
            }

            int matchCount = matchingCollected.size();
            int totalCount = matchCount + matchingNotCollected.size();
            sender.sendMessage("§e" + matchCount + "/" + totalCount + " items collected (" + round(((double) matchCount / (double) totalCount) * 100d) + "%) matching '" + queryString + "'");

            return true;
        }
        return false;
    }
}
