package org.example.entities;

public class Player {
    private int x;
    private int y;
    private int health;
    private int damage;
    private boolean isAlive;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.health = 100;
        this.damage = 20;
        this.isAlive = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveUp() {
        y--;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // --Combat methods--
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            this.isAlive = false;
        }
    }

    public int attack() {
        return this.damage;
    }

    public void heal(int amount) {
        this.health += amount;
        if (this.health > 100) {
            this.health = 100;
        }
    }

    // --New getters--
    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isAlive() {
        return isAlive;
    }
}