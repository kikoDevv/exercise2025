package org.example.game;

import org.example.entities.Player;
import org.example.map.Maps;
import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner scanner;
    private boolean running;

    //-- Area names for each level and position --
    private String[][] areaNames = {
        {"Home", "Home", "Home"},           // Level 0 - all positions are Home
        {"Dungeon", "Village", "Lake"},     // Level 1 - left, center, right
        {"Mountain", "Bridge", "Forest"},   // Level 2 - left, center, right
        {"Castle", "Castle", "Castle"}      // Level 3 - all positions are Castle
    };

    //-- Area descriptions and actions --
    private String[][] areaDescriptions = {
        {"🏕️ You are at your safe Home base. Rest and prepare for the journey ahead!",
         "🏕️ You are at your safe Home base. Rest and prepare for the journey ahead!",
         "🏕️ You are at your safe Home base. Rest and prepare for the journey ahead!"},
        {"⚔️ Dark Dungeon filled with skeletons! Collect the golden key here! 🔑",
         "🏘️ Peaceful Village with friendly people. Get weapons and supplies! 🗡️",
         "💧 Healing Lake with magical waters. Restore your health here! 🩸"},
        {"⛰️ Treacherous Mountain peaks! Collect precious diamonds! 💎",
         "🌉 Mysterious Bridge guarded by locks. Use your key to pass! 🚪🔒",
         "🌲 Enchanted Forest with hidden treasures. Find camping supplies! ⛺"},
        {"🏰 Victory! You've reached the Castle! You are now the ruler! 👑",
         "🏰 Victory! You've reached the Castle! You are now the ruler! 👑",
         "🏰 Victory! You've reached the Castle! You are now the ruler! 👑"}
    };

    //-- Start at level 0, center position--
    public Game() {
        this.player = new Player(1, 0);
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public void start() {
        System.out.println("🎮 Welcome to Climb to Victory Quest!");
        System.out.println("Commands: up (climb), down (descend), left, right, quit");
        System.out.println();

        while (running) {
            // --Show current map with player position--
            Maps.gameMap(player.getY(), player.getX());

            // Show current location and description
            String currentArea = areaNames[player.getY()][player.getX()];
            String description = areaDescriptions[player.getY()][player.getX()];

            System.out.println("📍 You are currently at: " + currentArea + " (Level " + (player.getY() + 1) + ")");
            System.out.println("ℹ️  " + description);
            System.out.println();

            // Check for victory
            if (player.getY() == 3) {
                System.out.println("🏆 CONGRATULATIONS! You reached the Castle and WON the game! 🏆");
                System.out.println("👑 You are now the ruler of this realm! 👑");
                break;
            }

            //- Get user input --
            System.out.print("What to do~~: ");
            String command = scanner.nextLine().toLowerCase().trim();

            processCommand(command);
        }

        scanner.close();
    }

    private void processCommand(String command) {
        switch (command) {
            case "up":
            case "climb":
                if (isValidVerticalMove(player.getY() + 1)) {
                    player.moveDown(); // moveDown increases Y coordinate
                    String newArea = areaNames[player.getY()][player.getX()];
                    System.out.println("✅ Climbed up to " + newArea + "!");
                } else {
                    System.out.println("Cant climb higher! You reached the top!");
                }
                break;

            case "down":
            case "descend":
                if (isValidVerticalMove(player.getY() - 1)) {
                    player.moveUp(); // moveUp decreases Y coordinate
                    String newArea = areaNames[player.getY()][player.getX()];
                    System.out.println("Descended to " + newArea + "!");
                } else {
                    System.out.println("Cant go lower! You are at the bottom!");
                }
                break;            case "left":
                if (isValidHorizontalMove(player.getX() - 1)) {
                    player.moveLeft();
                    String newArea = areaNames[player.getY()][player.getX()];
                    System.out.println("✅ Moved left to " + newArea + "!");
                } else {
                    System.out.println("Cant go further left!");
                }
                break;

            case "right":
                if (isValidHorizontalMove(player.getX() + 1)) {
                    player.moveRight();
                    String newArea = areaNames[player.getY()][player.getX()];
                    System.out.println("✅ Moved right to " + newArea + "!");
                } else {
                    System.out.println("Cant go further right!");
                }
                break;

            case "quit":
                System.out.println("👋 Thanks for playing! Come back to continue your quest!");
                running = false;
                break;

            default:
                System.out.println("Unknown command. Use: up (climb), down (descend), left, right, quit");
                break;
        }

        System.out.println();
    }

    private boolean isValidVerticalMove(int level) {
        return level >= 0 && level < 4; // 4 levels total (0-3)
    }

    private boolean isValidHorizontalMove(int position) {
        return position >= 0 && position < 3; // 3 positions per level (0-2)
    }
}