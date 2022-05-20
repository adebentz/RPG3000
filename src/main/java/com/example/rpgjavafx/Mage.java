package com.example.rpgjavafx;

import javafx.scene.control.TextArea;

public class Mage extends SpellCaster {
    public Mage() {
        mana = 100;
        lifePoints = 30;
        armor = 20;
        weaponDamage = 70;
    }

    public String getName() {
        return "Mage";
    }

    public String getWeaponName() {
        return "Staff";
    }

}
