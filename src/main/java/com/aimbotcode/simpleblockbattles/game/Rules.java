package com.aimbotcode.simpleblockbattles.game;

import com.aimbotcode.simpleblockbattles.Api;
import com.aimbotcode.simpleblockbattles.Board;
import com.aimbotcode.simpleblockbattles.SimpleBlockBattles;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Rules implements Listener {

    private static FileConfiguration config = Api.getFileConfiguration("config");

    @EventHandler
    public static void blockRules(BlockPlaceEvent event) {
        //blocks
        //purple wool
        if(event.getBlockPlaced().equals(Material.PURPLE_WOOL)) {
            if(!(SimpleBlockBattles.server.getWorld(config.getString("Board.World")).getBlockAt(event.getBlockPlaced().getX(), event.getBlockPlaced().getY() - 1, event.getBlockPlaced().getZ())
                    != Board.getBlock(event.getBlockPlaced().getX(), event.getBlockPlaced().getZ()))) {
                if(event.getPlayer().getScoreboardTags().contains("BlockBattlesPlayer1")) {
                    for(Player player : SimpleBlockBattles.server.getOnlinePlayers()) {
                        if(player.getScoreboardTags().contains("BlockBattlesPlayer2")) {
                            event.getPlayer().setHealth(0);
                            event.getPlayer().removeScoreboardTag("BlockBattlesPlayer1");
                            player.removeScoreboardTag("BlockBattlesPlayer2");

                        }
                    }

                } else if(event.getPlayer().getScoreboardTags().contains("BlockBattlesPlayer1")) {

                }
            }
        }
        //counter blocks
    }

}
