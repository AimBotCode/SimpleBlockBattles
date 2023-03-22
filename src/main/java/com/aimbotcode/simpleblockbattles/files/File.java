package com.aimbotcode.simpleblockbattles.files;

import com.aimbotcode.simpleblockbattles.SimpleBlockBattles;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class File {

    public static final SimpleBlockBattles plugin = getPlugin(SimpleBlockBattles.class);
    // Set up signLog.yml configuration file
    public void setup(){
        FileConfiguration pluginConfigCfg;
        java.io.File pluginConfigFile;
        // If the plugin folder does not exist, create the plugin folder
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        pluginConfigFile = new java.io.File(plugin.getDataFolder(),  "config.yml");
        pluginConfigCfg = YamlConfiguration.loadConfiguration(pluginConfigFile);
        //if the config.yml does not exist, create it
        if(!pluginConfigFile.exists()){
            try {
                pluginConfigCfg.save(pluginConfigFile);
                //make config
                pluginConfigCfg.set("StartCMD", "test");
                pluginConfigCfg.set("Spawn.World", "world");
                pluginConfigCfg.set("Spawn.X", 0);
                pluginConfigCfg.set("Spawn.Y", 64);
                pluginConfigCfg.set("Spawn.Z", 0);
                pluginConfigCfg.set("Board.World", "world");
                pluginConfigCfg.set("Board.X", 0);
                pluginConfigCfg.set("Board.Y", 64);
                pluginConfigCfg.set("Board.Z", 0);
                pluginConfigCfg.set("Prize.Active", true);
                pluginConfigCfg.set("Prize.Name", "prize :D");
                pluginConfigCfg.set("Prize.Material", "GOLD_INGOT");
                List<String> list = pluginConfigCfg.isSet("Prize.Lore")
                        ? pluginConfigCfg.getStringList("Prize.Lore")
                        : new ArrayList<>();
                pluginConfigCfg.set("Prize.Lore", "Credits for merchant! :D");
                pluginConfigCfg.save(pluginConfigFile);

            }
            catch(IOException e){
                System.out.println(ChatColor.RED + "Could not create the config.yml file");
            }
        }
    }
}
