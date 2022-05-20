package com.example.rpgjavafx;

import javafx.scene.control.TextArea;

public abstract class SpellCaster extends Hero {
    protected int mana;

    @Override
    public void attack(Enemy ennemy) {
        if (mana < 10) {
            io.print("Vous n'avez pas assez de mana pour lancer un sort");
        }
        super.attack(ennemy);
        mana -= 10;
    }

    public int getMana() {
        return Math.max(0, mana);
    }
}
