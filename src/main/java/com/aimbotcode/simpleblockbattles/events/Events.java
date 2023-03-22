package com.aimbotcode.simpleblockbattles.events;

import com.aimbotcode.simpleblockbattles.Api;
import com.aimbotcode.simpleblockbattles.files.File;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class Events implements Listener {

    private FileConfiguration config;

    public Events() {
        this.config = Api.getFileConfiguration(File.plugin.getName());
    }

    @EventHandler
    public void illegalBlockPlaceEvent(BlockPlaceEvent event) {

        List<String> list = config.isSet("IllegalItems") ? config.getStringList("IllegalItems") : new ArrayList<>();
        for(int i = 0; i < list.toArray().length; i++) {
            String illegalItem = list.get(i);
            if(event.getBlockPlaced().equals(Material.getMaterial(list.get(i)))) {
                event.getPlayer().sendMessage("Illegal Block Use!");
                event.getPlayer().setHealth(0);
            }
        }
    }

}
