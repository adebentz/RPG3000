package com.example.rpgjavafx;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Hero {

    protected int lifePoints;
    protected int armor;
    protected int weaponDamage;
    protected final List<Potion> potions = new ArrayList<>();
    protected final List<Food> lembas = new ArrayList<>();
    protected final IoParser io = new IoParser();
    protected boolean isDefending = false;
    public Hero() {
        int potionCount = new Random().nextInt(3);
        int lembasCount = new Random().nextInt(3);

        for (int i = 0; i < potionCount; i++) {
            potions.add(new Potion());
        }
        for (int i = 0; i < lembasCount; i++) {
            lembas.add(new Food());
        }
    }

    public void attack(Enemy ennemy) {
        io.print(getName() + " attaque " + ennemy.getName() + " avec " + getWeaponName());
        ennemy.takeDamage(getWeaponDamage());
    }

    public void defend() {
        io.print(getName() + " se défend d'une future attaque");
        isDefending = true;
    }

    public void takeDamage(Enemy ennemy) {
        int defendArmor = 0;
        io.print(ennemy.getName() + " attaque " + getName() + " dommage maximum : " + ennemy.getDamage());
        if (isDefending()) {
            defendArmor = new Random().nextInt(100);
            io.print(getName() + " se défend contre " + ennemy.getName() + ". Dégats absorbés: " + defendArmor + "%.");
        }
        int damage = (ennemy.getDamage() - getArmor() / 10) * ((100 - defendArmor) / 100);
        lifePoints -= damage % lifePoints;
        armor -= 10;   // -10 d'armure
        io.print(getName() + " perd " + damage + " points de vie. Il lui reste " +  getLife() + " points de vie.");
        io.print(getName() + " perd 10 points d'armure. Il lui reste " + getArmor() + " points d'armure.");
    }

    public void usePotion() {
        if (potions.isEmpty()) {
            io.print("Vous n'avez plus de potion");
            return;
        }
        io.print(getName() + " utilise une potion, il gagne " + potions.get(0).getLifePoints() + " points de vie.");
        lifePoints += potions.get(0).getLifePoints();
        potions.remove(0);
        io.print(getName() + " a maintenant " + lifePoints + " points de vie.");
    }
    public void useLembas() {
        if (lembas.isEmpty()) {
            io.print("Vous n'avez plus de lembas");
            return;
        }
        io.print(getName() + " mange un lemba, il gagne " + lembas.get(0).getLifePoints() + " points de vie.");
        lifePoints += lembas.get(0).getLifePoints();
        lembas.remove(0);
        io.print(getName() + " a maintenant " + lifePoints + " points de vie.");
    }

    public boolean isDead() {
        return lifePoints <= 0;
    }
    public boolean isDefending() {
        if (isDefending) {
            isDefending = false;
            return true;
        }
        return false;
    }
    public int getLife() {
        return Math.max(0, lifePoints);
    }
    public int getArmor() {
        return Math.max(0, armor);
    }
    public int getWeaponDamage() {
        return weaponDamage;
    }
    public int getPotionsCount() {
        return potions.size();
    }
    public int getLembasCount() {
        return lembas.size();
    }
    public abstract String getName();
    public abstract String getWeaponName();
}
