package com.simondmc.borderhoarder.game;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ItemDictionary {
    private static final Map<Material, String> list = new HashMap<>();

    public static HashMap<Material, String> getDict() {
        return (HashMap<Material, String>) list;
    }

    public static void populateDictUsingBlacklist() {
        HashSet<Material> BLACKLIST = new HashSet<>(Arrays.asList(
                Material.AIR,
                Material.BARRIER,
                Material.STRUCTURE_VOID,
                Material.STRUCTURE_BLOCK,
                Material.LIGHT,
                Material.COMMAND_BLOCK,
                Material.CHAIN_COMMAND_BLOCK,
                Material.REPEATING_COMMAND_BLOCK,
                Material.JIGSAW,
                Material.PETRIFIED_OAK_SLAB,
                Material.PLAYER_HEAD,
                Material.KNOWLEDGE_BOOK,
                Material.DEBUG_STICK,
                Material.BEDROCK,
                Material.REINFORCED_DEEPSLATE,
                Material.BUDDING_AMETHYST,
                Material.CHORUS_PLANT,
                Material.DIRT_PATH,
                Material.END_PORTAL_FRAME,
                Material.FARMLAND,
                Material.INFESTED_CHISELED_STONE_BRICKS,
                Material.INFESTED_CRACKED_STONE_BRICKS,
                Material.INFESTED_COBBLESTONE,
                Material.INFESTED_DEEPSLATE,
                Material.INFESTED_MOSSY_STONE_BRICKS,
                Material.INFESTED_STONE,
                Material.INFESTED_STONE_BRICKS,
                Material.SPAWNER,
                Material.COMMAND_BLOCK_MINECART,
                Material.TRIAL_SPAWNER,
                Material.TEST_BLOCK,
                Material.TEST_INSTANCE_BLOCK,
                Material.FROGSPAWN,
                Material.VAULT
        ));

        for (Material m: Material.values()) {
            ItemType itemType = m.asItemType();
            if (itemType != null) {  // many non-collectable materials have no item, so have a null itemType
                // remove all spawn eggs
                if (m.name().endsWith("_SPAWN_EGG")) {
                    continue;
                }
                // remove if listed in blacklist
                if (BLACKLIST.contains(m)) {
                    continue;
                }

                String mDisplayName = PlainTextComponentSerializer.plainText().serialize(Component.translatable(m));
                list.put(m, mDisplayName);
            }
        }
    }
}
