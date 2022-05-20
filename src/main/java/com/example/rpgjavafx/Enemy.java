package com.example.rpgjavafx;

public abstract class Enemy {
    protected int lifePoints = 0;

    public void takeDamage(int damage) {
        lifePoints -= damage;
    }

    public int getDamage() {
        return 10;
    }

    public boolean isDead() {
        return this.lifePoints <= 0;
    }

    public int getLife() {
        return lifePoints;
    }

    public abstract String getName();
}
