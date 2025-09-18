package org.example.service;

import org.example.entities.Player;
import org.example.entities.Enemy;
import org.example.entities.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CombatServiceTest {
    private Player player;
    private Enemy enemy;

    @BeforeEach
    void setUp() {
        player = new Player(0, 0);
        enemy = new Enemy("Test Monster", 50, 10);
    }

    @Test
    void testCalculateDamage() {
        int damage = CombatService.calculateDamage(player);
        // Should be between 80% and 120% of base damage (20)
        assertTrue(damage >= 16 && damage <= 24);
    }

    @Test
    void testCalculateDamageWithWeapon() {
        Item sword = new Item("Test Sword", "weapon", 10, "Test weapon");
        player.addItem(sword);

        int damage = CombatService.calculateDamage(player);
        // Should be between 80% and 120% of (base damage + weapon damage) = (20 + 10) = 30
        assertTrue(damage >= 24 && damage <= 36);
    }

    @Test
    void testExecuteCombatRound() {
        String result = CombatService.executeCombatRound(player, enemy);
        assertNotNull(result);
        assertTrue(result.contains("You attack"));
        assertTrue(result.contains("damage"));
    }

    @Test
    void testEnemyDefeat() {
        // Set enemy to low health
        enemy.takeDamage(45); // 5 health remaining

        String result = CombatService.executeCombatRound(player, enemy);
        assertTrue(result.contains("You defeated it"));
        assertFalse(enemy.isAlive());
    }

    @Test
    void testUsePotionWhenHealthy() {
        Item potion = new Item("Health Potion", "potion", 30, "Healing potion");
        player.addItem(potion);

        String result = CombatService.usePotion(player);
        assertTrue(result.contains("already at full health"));
    }

    @Test
    void testUsePotionWhenInjured() {
        Item potion = new Item("Health Potion", "potion", 30, "Healing potion");
        player.addItem(potion);
        player.takeDamage(40); // Player at 60 health

        String result = CombatService.usePotion(player);
        assertTrue(result.contains("restored"));
        assertEquals(90, player.getHealth());
        assertFalse(player.hasPotion()); // Potion should be consumed
    }
}