package com.aimbotcode.simpleblockbattles.game;

import com.aimbotcode.simpleblockbattles.Api;
import com.aimbotcode.simpleblockbattles.Board;
import com.aimbotcode.simpleblockbattles.SimpleBlockBattles;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Game {

    private static FileConfiguration config = Api.getFileConfiguration("config");

    public static void startGame(Player player1, Player player2) {
        new BukkitRunnable() {

            int count = 0;
            public void run() {
                if(count == 5) {
                    cancel();
                    player1.teleport(new Location((World) config.get("Board.World"), Board.getBlock(0, 0).getX(), Board.getBlock(0, 0).getY() + 1, Board.getBlock(0, 0).getZ()));
                    player2.teleport(new Location((World) config.get("Board.World"), Board.getBlock(7, 7).getX(), Board.getBlock(7, 7).getY(), Board.getBlock(7, 7).getZ()));

                    player1.getScoreboardTags().add("BlockBattlesPlayer1");
                    player2.getScoreboardTags().add("BlockBattlesPlayer2");

                    player1.sendMessage(ChatColor.GREEN + "Begin!");
                    player2.sendMessage(ChatColor.GREEN + "Begin!");
                }
                player1.sendMessage(ChatColor.GREEN + "" + count);
                player2.sendMessage(ChatColor.GREEN + "" + count);
                count++;
            }

        }.runTaskTimer(SimpleBlockBattles.getPlugin(SimpleBlockBattles.class), 0, 20);
    }

    public static void endGame(Player player1, Player player2) {

    }

}
