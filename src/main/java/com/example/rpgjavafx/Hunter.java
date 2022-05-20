package com.example.rpgjavafx;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class Hunter extends Hero {
    private int arrows;
    public Hunter() {
        arrows = 20;
        lifePoints = 60;
        armor = 50;
        weaponDamage = 40;
    }

    @Override
    public void attack(Enemy ennemy) {
        if (arrows <= 0) {
            io.print("Vous n'avez plus de flèches !");
            return;
        }
        super.attack(ennemy);
        arrows--;
    }

    public String getName() {
        return "Chasseur";
    }

    public String getWeaponName() {
        return "Flèches";
    }

    public int getArrows() {
        return Math.max(0, arrows);
    }

}
