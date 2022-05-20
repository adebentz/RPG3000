package com.example.rpgjavafx;

import javafx.scene.control.TextArea;

public class Warrior extends Hero {

    public Warrior() {
        lifePoints = 80;
        armor = 50;
        weaponDamage = 50;
    }


    public String getName() {
        return "Guerrier";
    }

    public String getWeaponName() {
        return "Epée";
    }

}
