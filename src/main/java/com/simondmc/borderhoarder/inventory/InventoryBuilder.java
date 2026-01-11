package com.simondmc.borderhoarder.inventory;

import com.simondmc.borderhoarder.game.ItemDictionary;
import com.simondmc.borderhoarder.game.ItemHandler;
import com.simondmc.borderhoarder.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryBuilder {

    public static int INVENTORY_VIEW_ROWS = 5;  // not counting the bottom row for navigation buttons

    public static int ITEMS_PER_PAGE = INVENTORY_VIEW_ROWS * 9;  // by definition, see createInventory
    public static int CELLS_PER_PAGE = (INVENTORY_VIEW_ROWS + 1) * 9;  // by definition, see createInventory

    private static void prepareInventoryView(Inventory inv, int page, int totalPages) {
        // blank bottom row
        ItemStack i = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName("§k");
        i.setItemMeta(m);
        for (int j = ITEMS_PER_PAGE; j < CELLS_PER_PAGE; j++) {
            inv.setItem(j, i);
        }

        // previous page button
        if (page > 1) {
            i = new ItemStack(Material.ARROW);
            m = i.getItemMeta();
            m.setDisplayName("§aPrevious Page");
            i.setItemMeta(m);
            inv.setItem(ITEMS_PER_PAGE, i);  // bottom row, left cell
        }

        // next page button
        if (page < totalPages) {
            i = new ItemStack(Material.ARROW);
            m = i.getItemMeta();
            m.setDisplayName("§aNext Page");
            i.setItemMeta(m);
            inv.setItem(CELLS_PER_PAGE-1, i);  // bottom row, right cell
        }
    }

    public static Inventory buildCompletedInventory(int page) {
        final int totalPages = (int) Math.ceil(ItemHandler.getCollectedItems().size() / ((double) ITEMS_PER_PAGE));

        Inventory inv = Bukkit.createInventory(null, CELLS_PER_PAGE, "Collected Items§a §r- Page " + page + "/" + (totalPages == 0 ? 1 : totalPages));

        // add bottom row with previous and next buttons
        prepareInventoryView(inv, page, totalPages);

        // fill contents
        int start = (page - 1) * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, ItemHandler.getCollectedItems().size());
        // reverse list
        List<Material> collectedItems = new ArrayList<>(ItemHandler.getCollectedItems().keySet());
        Collections.reverse(collectedItems);
        for (int j = start; j < end; j++) {
            Material itemType = collectedItems.get(j);
            ItemStack i = new ItemStack(itemType);
            ItemMeta m = i.getItemMeta();
            if (m == null) {
                continue;
            }
            m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            String playerName = ItemHandler.getCollectedItems().get(itemType) != null ? PlayerUtil.getNameFromUUID(ItemHandler.getCollectedItems().get(itemType)) : "who knows!";
            m.setLore(Collections.singletonList("§eCollected by " + playerName));
            i.setItemMeta(m);
            inv.addItem(i);
        }

        return inv;
    }

    public static Inventory buildMissingInventory(int page) {
        final List<Material> missingItems = ItemDictionary.getDict().keySet().stream()
                .filter(item -> !ItemHandler.getCollectedItems().containsKey(item)).sorted().toList();
        final int totalPages = (int) Math.ceil(missingItems.size() / ((double) ITEMS_PER_PAGE));

        Inventory inv = Bukkit.createInventory(null, CELLS_PER_PAGE, "Missing Items§a §r- Page " + page + "/" + totalPages);

        // add bottom row with previous and next buttons
        prepareInventoryView(inv, page, totalPages);

        // fill contents
        int start = (page - 1) * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, missingItems.size());
        for (int j = start; j < end; j++) {
            Material itemType = missingItems.get(j);
            ItemStack i = new ItemStack(itemType);
            ItemMeta m = i.getItemMeta();
            if (m == null) {
                continue;
            }
            m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            i.setItemMeta(m);
            inv.addItem(i);
        }

        return inv;
    }
}
