package com.aimbotcode.simpleblockbattles;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static com.aimbotcode.simpleblockbattles.SimpleBlockBattles.plugin;

public class Api {

    public static File getConfigFile(String fileName){
        return new File(plugin.getDataFolder(), fileName + ".yml");
    }

    public static FileConfiguration getFileConfiguration(String fileName){
        return YamlConfiguration.loadConfiguration(getConfigFile(fileName));
    }
}