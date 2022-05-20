package com.example.rpgjavafx;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Game implements Initializable {
    private final List<Hero> heroes = new ArrayList<>();
    private int enemyCount;
    private int heroIndex;
    private Enemy enemy;
    private final Random random = new Random();
    private final IoParser io = new IoParser();

    /**
     * @return Return true to restart the game, false to quit
     */
    public boolean playGame() {
        io.print("Démarage du jeu");
        int size = io.askInt("Combien de heros voulez vous ?", 1, 30);
        for (int i = 0; i < size; i++) {
            int heroType = io.askInt("Quel type de hero voulez vous ? (1: Guerrier, 2: Chasseur, 3: Mage, 4: Médecin)", 1, 4);
            Hero hero = null;
            switch (heroType) {
                case 1 -> hero = new Warrior();
                case 2 -> hero = new Hunter();
                case 3 -> hero = new Mage();
                case 4 -> hero = new Healer();
            }
            heroes.add(hero);
        }
        io.print("Combats !");
        heroIndex = random.nextInt(heroes.size());
        enemy = (random.nextInt(3) == 0) ? new BasicEnemy() : new Boss();
        enemyCount = heroes.size();
        Hero hero = heroes.get(heroIndex);
        // While there is still a hero alive and still an enemy alive
        while ((!hero.isDead() || !heroDies(hero)) && (!enemy.isDead() || !enemyDies())) {
            hero = heroes.get(heroIndex);
            io.print("Nouveau tour");
            if (enemy instanceof Boss)
                io.print("Vous êtes tombé sur un boss!");
            io.print("Vous avez " + hero.getLife() + " points de vie");
            io.print("Votre ennemie a " + enemy.getLife() + " points de vie");
            io.print(hero.getName());
            io.print("Nom: " + hero.getName());
            io.print("Vie: " + hero.getLife());
            if (hero instanceof Hunter)
                io.print("Attaque: " + hero.getWeaponDamage() + " - Flèches: " + ((Hunter) hero).getArrows());
            else
                io.print("Attaque: " + hero.getWeaponDamage());
            io.print("Armure: " + hero.getArmor());
            if (hero instanceof SpellCaster)
                io.print("Mana: " + ((SpellCaster) hero).getMana());
            io.print("Ennemie");
            io.print("Nom: " + enemy.getName());
            io.print("Vie: " + enemy.getLife());
            io.print("Actions");
            io.print("Que voulez vous faire ?");
            io.print("1: Attaquer");
            io.print("2: Se défendre");
            io.print("3: Utiliser une potion (" + hero.getPotionsCount() + " restantes)");
            io.print("4: Manger un lembas (" + hero.getLembasCount() + " restants)");
            int choice = io.askInt("Votre choix ?", 1, 6);
            io.print("Hero");
            switch (choice) {
                case 1 -> hero.attack(enemy);
                case 2 -> hero.defend();
                case 3 -> hero.usePotion();
                case 4 -> hero.useLembas();
            }
            if (enemy.isDead())
                continue;
            io.print("Ennemie");
            hero.takeDamage(enemy);
        }
        return io.askBoolean("Voulez vous rejouer ? [y/n]: ");
    }

    /**
     * @param hero The dead hero
     * @return true if there is still a hero alive, false if the game is over
     */
    private boolean heroDies(Hero hero) {
        io.print("Votre hero est mort");
        heroes.remove(hero);
        if (heroes.size() == 0) {
            io.print("Vous n'avez aucun héro restant, vous avez perdu");
            return true;
        } else {
            heroIndex = random.nextInt(heroes.size());
            io.print("Vous avez " + heroes.size() + " heros");
            io.print("Votre nouveau hero apparait ! Il est " + hero.getName());
            return false;
        }
    }

    /**
     * @return true if there is still an enemy alive, false if the game is over
     */
    private boolean enemyDies() {
        io.print("Vous avez battu votre ennemie");
        enemyCount--;
        if (enemyCount == 0) {
            io.print("Vous avez battu tous les ennemies, vous avez gagné");
            return true;
        } else {
            enemy = (random.nextInt(3) == 0) ? new BasicEnemy() : new Boss();
            return false;
        }
    }

    private String getCenteredTitle(String title) {
        int size = (int) (Math.floor(title.length() / 2.0));
        int border = (int) (Math.floor(50 / 2.0 - size / 2.0));
        return "-".repeat(border) + title + "-".repeat(border) + "-".repeat(border % 2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing view");
        playGame();
    }

}
