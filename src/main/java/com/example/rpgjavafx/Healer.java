package com.example.rpgjavafx;

import javafx.scene.control.TextArea;

public class Healer extends SpellCaster {
    public Healer() {
        mana = 100;
        lifePoints = 30;
        armor = 5;
        weaponDamage = 0;
    }


    public String getName() {
        return "Soigneur";
    }

    public String getWeaponName() {
        return "Bâton";
    }

}
