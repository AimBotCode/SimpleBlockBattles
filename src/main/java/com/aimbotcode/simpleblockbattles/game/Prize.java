package com.aimbotcode.simpleblockbattles.game;

import com.aimbotcode.simpleblockbattles.Api;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Prize {

    private static FileConfiguration config = Api.getFileConfiguration("config");

    public static ItemStack prizeItem() {
        ItemStack item = new ItemStack(Material.getMaterial(config.getString("Prize.Material")));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = config.isSet("Prize.Lore")
                ? config.getStringList("Prize.Lore")
                : new ArrayList<>();
        meta.setDisplayName((String) config.get("Prize.Name"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
