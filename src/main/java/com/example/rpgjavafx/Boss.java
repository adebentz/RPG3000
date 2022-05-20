package com.example.rpgjavafx;

import java.util.Random;

public class Boss extends Enemy {
    public Boss() {
        lifePoints = new Random().nextInt(50, 200);
    }

    public int getDamage() {
        return new Random().nextInt(20, 40);
    }

    public String getName() {
        return "Boss";
    }
}
