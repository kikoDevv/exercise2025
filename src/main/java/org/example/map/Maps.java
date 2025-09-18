package org.example.map;

import org.example.utils.Funcs;

public class Maps {
    public static void gameMap(int playerLevel, int playerPosition) {
        Funcs.spacer(2);
        Funcs.print("═══════════════════════════════════════════════════════════════════════");
        Funcs.print("                    🏰 CLIMB TO VICTORY QUEST 🏰                       ");
        Funcs.print("═══════════════════════════════════════════════════════════════════════");
        Funcs.print("");

        //-- LEVEL 4 VICTORY  Level 3 in array --
        Funcs.print("                    ┌─────────┐  ");
        if (playerLevel == 3) {
            Funcs.print("                    │ CASTLE  │");
            Funcs.print("                    │   👑    │");
            Funcs.print("                    │   🤴    │  ← 🎯GOAL: You WIN!");
            Funcs.print("                    │  <) )✌️  │");
            Funcs.print("                    │   /\\    │");
        } else {
            Funcs.print("                    │ CASTLE  │");
            Funcs.print("                    │   🏰    │  ← 🎯GOAL: Reach here to WIN!");
            Funcs.print("                    │   👑    │");
            Funcs.print("                    │   🛡️     │");
        }
        Funcs.print("                    └────┬────┘");
        Funcs.print("                        💂 Guard");
        Funcs.print("                         ↑ ");
        Funcs.print("                         │");

        //-- LEVEL 3 - BRIDGE CHALLENGE Level 2 in array--
        Funcs.print("     ┌─────────┐    ┌────┴────┐    ┌─────────┐");
        if (playerLevel == 2) {
            Funcs.print("     │ MOUNTAIN│    │ BRIDGE  │    │ FOREST  │");

            String leftIcon = (playerPosition == 0) ? "   😎    " : "  ⛰️🏔️⛰️    ";
            String centerIcon = (playerPosition == 1) ? "   😎    " : " 🌉🚪🌉  ";
            String rightIcon = (playerPosition == 2) ? "   😎    " : "  🌲🌲🌲 ";

            Funcs.print("     │" + leftIcon + "├────┤" + centerIcon + "├────│" + rightIcon + "│");

            String leftFeet = (playerPosition == 0) ? "  <) )   " : "  🏔️💎🏔️   ";
            String centerFeet = (playerPosition == 1) ? "  <) )   " : " 🚪🔒🚪  ";
            String rightFeet = (playerPosition == 2) ? "  <) )   " : "    ⛺️  ";

            Funcs.print("     │" + leftFeet + "│    │" + centerFeet + "│    │" + rightFeet + "│");

            String leftEnv = (playerPosition == 0) ? "   /\\    " : "  ⛰️💎⛰️   ";
            String centerEnv = (playerPosition == 1) ? "   /\\    " : " 🌉🚪🌉  ";
            String rightEnv = (playerPosition == 2) ? "   /\\    " : "  🌲🌲🌲 ";

            Funcs.print("     │" + leftEnv + "│    │" + centerEnv + "│    │" + rightEnv + "│");
        } else {
            Funcs.print("     │ MOUNTAIN│    │ BRIDGE  │    │ FOREST  │");
            Funcs.print("     │  ⛰️🏔️⛰️    │    │ 🌉🚪🌉  │    │  🌲🌲🌲 │");
            Funcs.print("     │  🏔️💎🏔️   ├────┤ 🚪🔒🚪  ├────│    ⛺️    │");
            Funcs.print("     │  ⛰️💎⛰️   │    │ 🌉🚪🌉  │    │  🌲🌲🌲 │");
        }
        Funcs.print("     └─────────┘    └────┬────┘    └─────────┘");
        Funcs.print("                         ↑ LOCKED BRIDGE");
        Funcs.print("                         │");
        Funcs.print("                         │");

        //-- LEVEL 2 - VILLAGE & EXPLORATION Level 1 in array--
        Funcs.print("     ┌─────────┐    ┌────┴────┐    ┌─────────┐");
        if (playerLevel == 1) {
            Funcs.print("     │ DUNGEON │    │ VILLAGE │    │LAKE+HEAL│");

            String leftIcon = (playerPosition == 0) ? "   😎    " : "  ⚔️💀⚔️   ";
            String centerIcon = (playerPosition == 1) ? "   😎    " : "  🏘️🏘️🏘️    ";
            String rightIcon = (playerPosition == 2) ? "   😎    " : " ⛰️ 🏔️ ⛰️   ";

            Funcs.print("     │" + leftIcon + "├────┤" + centerIcon + "├────│" + rightIcon + "│");

            String leftFeet = (playerPosition == 0) ? "  <) )   " : "💀 🔑 💀 ";
            String centerFeet = (playerPosition == 1) ? "  <) )   " : "🏘️ 🗡️🗡️  🏘️  ";
            String rightFeet = (playerPosition == 2) ? "  <) )   " : " ⛰️ 🩸⛰️   ";

            Funcs.print("     │" + leftFeet + "│    │" + centerFeet + "│    │" + rightFeet + "│");

            String leftEnvVillage = (playerPosition == 0) ? "   /\\    " : "  ⚔️💀⚔️   ";
            String centerEnvVillage = (playerPosition == 1) ? "   /\\    " : "  🏘️🏘️🏘️    ";
            String rightEnvVillage = (playerPosition == 2) ? "   /\\    " : " ⛰️ 🏔️ ⛰️   ";

            Funcs.print("     │" + leftEnvVillage + "│    │" + centerEnvVillage + "│    │" + rightEnvVillage + "│");
        } else {
            Funcs.print("     │ DUNGEON │    │ VILLAGE │    │LAKE+HEAL│");
            Funcs.print("     │  ⚔️💀⚔️   │    │  🏘️🏘️🏘️    │    │ ⛰️ 🏔️ ⛰️   │");
            Funcs.print("     │💀 🔑 💀 ├────┤🏘️ 🗡️🗡️  🏘️  ├────│ ⛰️ 🩸⛰️   │");
            Funcs.print("     │  ⚔️💀⚔️   │    │  🏘️🏘️🏘️    │    │ ⛰️ 🏔️ ⛰️   │");
        }
        Funcs.print("     └─────────┘    └────┬────┘    └─────────┘");
        Funcs.print("                         │");
        Funcs.print("                         │");
        Funcs.print("                         │");
        Funcs.print("                         │");
        Funcs.print("                         ↑ START CLIMBING");
        Funcs.print("                         │");
        Funcs.print("                         │");
        Funcs.print("                         │");
        Funcs.print("                         │");

        //-- LEVEL 1 - SAFE HOUSE START Level 0 in array --
        if (playerLevel == 0) {
            Funcs.print("                    ┌─────────┐");
            Funcs.print("                    │  HOME   │    ");
            Funcs.print("                    │   😎👋  │    ");
            Funcs.print("                    │  <) )   │    ");
            Funcs.print("                 ───┤   /\\    ├─── ");
            Funcs.print("                    │ 🏕️ 🔥🏕️   │     ");
            Funcs.print("                    └─────────┘    ");
        } else {
            Funcs.print("                    ┌─────────┐");
            Funcs.print("                    │  HOME   │    ");
            Funcs.print("                    │         │    ");
            Funcs.print("                    │         │    ");
            Funcs.print("                 ───┤         ├─── ");
            Funcs.print("                    │ 🏕️ 🔥🏕️   │     ");
            Funcs.print("                    └─────────┘    ");
        }

        Funcs.print("═══════════════════════════════════════════════════════════════════════");
        Funcs.print("🎮 OBJECTIVE: Climb from SAFE HOUSE (🏕️🔥) to CASTLE (🏰) to WIN! 🏆");
        Funcs.print("⚔️ BEWARE: Guards and monsters block your path to victory!");
        Funcs.print("🗝️ COLLECT: Keys and treasures to help your journey upward!");
        Funcs.print("🎮 COMMANDS: up (climb), down (descend), left, right, quit");
        Funcs.print("═══════════════════════════════════════════════════════════════════════");
        Funcs.spacer(2);
    }
}
