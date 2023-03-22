package com.aimbotcode.simpleblockbattles;

import com.aimbotcode.simpleblockbattles.events.Events;
import com.aimbotcode.simpleblockbattles.files.File;
import com.aimbotcode.simpleblockbattles.game.Game;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Logger;


public final class SimpleBlockBattles extends JavaPlugin implements Listener {

    public static Plugin plugin;
    public static Server server;
    public static Logger log;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        this.plugin = getPlugin(SimpleBlockBattles.class);
        this.server = getServer();
        this.log = getLogger();
        this.config = Api.getFileConfiguration("config");
        log.info("SimpleBlockBattles is enabled");
        server.getPluginManager().registerEvents(new Events(), this);
        createConfigurationFiles();
        Board board = new Board(this);
        board.buildBoard();
        new BukkitRunnable() {

            public void run() {

                for(Player player : getServer().getOnlinePlayers()) {
                    if(player.getScoreboardTags().contains("BlockBattlesPlayer1")) {
                        for (int i = 0; i < 8; i++) {
                            for (int a = 0; a < 8; a++) {
                                if (getServer().getWorld((String) Api.getFileConfiguration("config").get("Board.World")).getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ()) != Board.getBlock(i, a)) {
                                    player.setHealth(0);
                                    for(Player p : getServer().getOnlinePlayers()) {
                                        if(p.getScoreboardTags().contains("BlockBattlesPlayer2")) {
                                            System.out.println(player.getName() + " Has died! " + p.getName() + " wins!");
                                            //prize

                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(player.getScoreboardTags().contains("BlockBattlesPlayer2")) {
                        for (int i = 0; i < 8; i++) {
                            for (int a = 0; a < 8; a++) {
                                if (getServer().getWorld((String) Api.getFileConfiguration("config").get("Board.World")).getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ()) != Board.getBlock(i, a)) {
                                    player.setHealth(0);
                                    for(Player p : getServer().getOnlinePlayers()) {
                                        if(p.getScoreboardTags().contains("BlockBattlesPlayer1")) {
                                            player.setHealth(0);
                                            System.out.println(player.getName() + " Has died! " + p.getName() + " wins!");
                                            //prize

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }.runTaskTimer(SimpleBlockBattles.getPlugin(SimpleBlockBattles.class), 0, 20);
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equals(config.get("StartCMD"))) {
            if (sender instanceof Player) {
                ((Player) sender).getScoreboardTags().add("BlockBattlesPlayer1");
            }
        }
        return false;
    }

    @Override
    public void onDisable() {
        log.info("SimpleBlockBattles is disabled");
    }

    public static void createConfigurationFiles(){
        File fileCfgm = new File();
        fileCfgm.setup();
    }
}
