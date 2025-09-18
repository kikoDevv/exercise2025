package org.example.game;

import org.example.entities.Enemy;
import org.example.entities.Player;
import org.example.map.Maps;
import org.example.utils.Funcs;

import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner scanner;
    private boolean running;
    private Enemy dungeonEnemy;
    private String lastActionMessage;

    //-- area names for each level and position --
    private String[][] areaNames = {
        {"Home", "Home", "Home"},
        {"Dungeon", "Village", "Lake"},
        {"Mountain", "Bridge", "Forest"},
        {"Castle", "Castle", "Castle"}
    };

    //-- area descriptions and actions --
    private String[][] areaDescriptions = {
        {"🏕️ You are at your safe Home base. Rest and prepare for the journey ahead!",
         "🏕️ You are at your safe Home base. Rest and prepare for the journey ahead!",
         "🏕️ You are at your safe Home base. Rest and prepare for the journey ahead!"},
        {"⚔️ Dark Dungeon filled danger and rewards! Collect the golden key here, used to open the bridge gate! 🔑",
         "🏘️ Peaceful Village with friendly people. Get weapons and supplies! 🗡️",
         "💧 Healing Lake with magical waters. Restore your health here! 🩸"},
        {"⛰️ Treacherous Mountain peaks! Collect precious diamonds which some guards are intressted in💎",
         "🌉 Mysterious Bridge guarded by locks. Use your key to pass! 🚪🔒",
         "🌲 Enchanted Forest with hidden treasures. Find camping supplies! ⛺"},
        {"🏰 Victory! You've reached the Castle! You are now the ruler! 👑",
         "🏰 Victory! You've reached the Castle! You are now the ruler! 👑",
         "🏰 Victory! You've reached the Castle! You are now the ruler! 👑"}
    };

    //-- start at level 0, center position--
    public Game() {
        this.player = new Player(1, 0);
        this.scanner = new Scanner(System.in);
        this.running = true;
        this.dungeonEnemy = new Enemy("💀 Skeleton Guardian", 50, 15);
        this.lastActionMessage = "";
    }

    public void start() {
        System.out.println("🎮 Welcome to Climb to Victory Quest!");
        System.out.println("Commands: up, down, left, right, attack, quit");
        System.out.println();

        while (running) {
            // --show current map with player position--
            Maps.gameMap(player.getY(), player.getX());

            // show current location and description
            // String currentArea = areaNames[player.getY()][player.getX()];
            String description = areaDescriptions[player.getY()][player.getX()];

            Funcs.print("|----------------------------------------INFO----------------------------------------|");
            //--- Show last action ---------
            if (!lastActionMessage.isEmpty()) {
                System.out.println("| " + lastActionMessage);
                lastActionMessage = ""; // Clear message after showing
            }
            System.out.println("| " + description);

            // Check if player is in dungeon with enemy
            if (player.getY() == 1 && player.getX() == 0 && dungeonEnemy.isAlive()) {
                System.out.println("| ⚔️ " + dungeonEnemy.getName() + " blocks your path! (HP: " + dungeonEnemy.getHealth() + ")");
            }
            // -- show HP --
            System.out.println("| ❤️ Health: " + player.getHealth() + "/100");


            // check for victory
            if (player.getY() == 3) {
                System.out.println("🏆 CONGRATULATIONS! You reached the Castle and WON the game! 🏆");
                System.out.println("👑 You are now the ruler of this realm! 👑");
                break;
            }

            //- get user input --
            Funcs.print("| 🎮COMMANDS: up, down, left, right, attack, quit                                   |");
            Funcs.print("|----------------------------------------INFO----------------------------------------|");
            System.out.print("|~~>: ");

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
                    player.moveDown();
                    String newArea = areaNames[player.getY()][player.getX()];
                    lastActionMessage = "🔺Climbed up to " + newArea + "!";
                } else {

                    if (player.getY() + 1 >= 4) {
                        lastActionMessage = "❌Can't climb higher! You reached the top!";
                    } else {
                        lastActionMessage = "❌Can't climb from here! You must be in the center to climb up/down!";
                    }
                }
                break;

            case "down":
            case "descend":
                if (isValidVerticalMove(player.getY() - 1)) {
                    player.moveUp();
                    String newArea = areaNames[player.getY()][player.getX()];
                    lastActionMessage = "🔻 Descended to " + newArea + "!";
                } else {

                    if (player.getY() - 1 < 0) {
                        lastActionMessage = "❌ Can't go lower! You are at the bottom!";
                    } else {
                        lastActionMessage = "❌Can't descend from here! You must be in the Village or Bridge center to climb up/down!";
                    }
                }
                break;

            case "left":
                if (isValidHorizontalMove(player.getX() - 1)) {
                    player.moveLeft();
                    String newArea = areaNames[player.getY()][player.getX()];
                    lastActionMessage = "⬅️ Moved left to " + newArea + "!";
                } else {

                    if (player.getX() - 1 < 0) {
                        lastActionMessage = "❌ Can't go further left!";
                    } else {
                        lastActionMessage = "❌Can't move left from here! You must be in center";
                    }
                }
                break;

            case "right":
                if (isValidHorizontalMove(player.getX() + 1)) {
                    player.moveRight();
                    String newArea = areaNames[player.getY()][player.getX()];
                    lastActionMessage = "➡️ Moved right to " + newArea + "!";
                } else {

                    if (player.getX() + 1 >= 3) {
                        lastActionMessage = "❌ Can't go further right!";
                    } else {
                        lastActionMessage = "❌Can't move right from here! You must be in center to move horizontally!";
                    }
                }
                break;

            case "quit":
                System.out.println("👋 Thanks for playing! Come back to continue your quest!");
                running = false;
                break;

            case "attack":
                handleCombat();
                break;

            default:
                lastActionMessage = "❓ Unknown command. Use: up, down, left, right, attack, quit";
                break;
        }
    }

    private boolean isValidVerticalMove(int level) {
        // Check basic bounds
        if (level < 0 || level >= 4) {
            return false;
        }



        return player.getX() == 1;
    }

    private boolean isValidHorizontalMove(int position) {
        // Check basic bounds
        if (position < 0 || position >= 3) {
            return false;
        }

        return player.getY() == 1 || player.getY() == 2;  
    }

    private void handleCombat() {
        // --Check if player is in dungeon with alive enemy--
        if (player.getY() == 1 && player.getX() == 0 && dungeonEnemy.isAlive()) {
            // Player attacks enemy
            int playerDamage = player.attack();
            dungeonEnemy.takeDamage(playerDamage);

            if (!dungeonEnemy.isAlive()) {
                lastActionMessage = "⚔️ You attack " + dungeonEnemy.getName() + " for " + playerDamage + " damage! 🎉 You defeated it! 🔑 You found a golden key!";
                return;
            }

            // Enemy attacks back
            int enemyDamage = dungeonEnemy.attack();
            player.takeDamage(enemyDamage);
            lastActionMessage = "⚔️ You attack " + dungeonEnemy.getName() + " for " + playerDamage + " damage! 💀 It attacks you for " + enemyDamage + " damage!";

            if (!player.isAlive()) {
                lastActionMessage = "💀 You have been defeated! Game Over!";
                running = false;
            }
        } else {
            lastActionMessage = "❌ There's nothing to attack here!";
        }
    }
}