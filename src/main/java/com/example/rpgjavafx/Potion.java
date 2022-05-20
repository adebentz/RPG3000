package com.example.rpgjavafx;

import java.util.Random;

public class Potion implements Consumable {

    private final int lifePoints;
    public Potion() {
        lifePoints = (new Random()).nextInt(5,30) + 1;
    }
    @Override
    public int getLifePoints() {
        return lifePoints;
    }
}
