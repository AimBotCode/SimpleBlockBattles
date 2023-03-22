package com.aimbotcode.simpleblockbattles;

import com.aimbotcode.simpleblockbattles.files.File;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private static Plugin main;

    private static FileConfiguration config = Api.getFileConfiguration("config");

    public Board(SimpleBlockBattles main) {
        this.main = main;
    }

    //top right block
    private static int[] corner = {(int) config.get("Board.X"), (int) config.get("Board.Y"), (int) config.get("Board.Z")};

    private static Block[][] board = new Block[8][8];

    public static void buildBoard() {
        for (int a = 0; a < 8; a++) {
            for (int i = 0; i < 8; i++) {
                board[a][i] = (main.getServer().getWorld((String) config.get("Board.World")).getBlockAt(corner[0] + i, corner[1], corner[2]));
            }
        }
    }

    public static Block getBlock(int width, int length) {
        return board[width][length];
    }
}
