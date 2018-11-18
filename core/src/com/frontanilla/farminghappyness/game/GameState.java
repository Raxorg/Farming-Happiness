package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.areas.DefenseArea;
import com.frontanilla.farminghappyness.game.areas.DisplayArea;
import com.frontanilla.farminghappyness.game.areas.FarmingArea;
import com.frontanilla.farminghappyness.game.areas.RiverArea;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.entities.plants.Plant;
import com.frontanilla.farminghappyness.game.entities.units.Enemy;
import com.frontanilla.farminghappyness.game.other.Bullet;

public class GameState {

    // Areas
    private RiverArea riverArea;
    private FarmingArea farmingArea;
    private DefenseArea defenseArea;
    private DisplayArea displayArea;
    // Entities
    private DelayedRemovalArray<Plant> plants;
    private DelayedRemovalArray<Enemy> enemies;
    private DelayedRemovalArray<Bullet> bullets;
    private DelayedRemovalArray<Defense> defenses;
    // Values
    private int money;
    private int workers;

    public GameState() {
        restart();
    }

    //----------------------------
    //          METHODS
    //----------------------------

    public void restart() {
        riverArea = new RiverArea();
        farmingArea = new FarmingArea();
        defenseArea = new DefenseArea();
        displayArea = new DisplayArea();
        plants = new DelayedRemovalArray<>();
        enemies = new DelayedRemovalArray<>();
        defenses = new DelayedRemovalArray<>();
        bullets = new DelayedRemovalArray<>();
        money = 100;
        workers = 0;
    }

    //----------------------------
    //      GETTERS & SETTERS
    //----------------------------


    public RiverArea getRiverArea() {
        return riverArea;
    }

    public FarmingArea getFarmingArea() {
        return farmingArea;
    }

    public DefenseArea getDefenseArea() {
        return defenseArea;
    }

    public DisplayArea getDisplayArea() {
        return displayArea;
    }

    public DelayedRemovalArray<Plant> getPlants() {
        return plants;
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }

    public DelayedRemovalArray<Bullet> getBullets() {
        return bullets;
    }

    public DelayedRemovalArray<Defense> getDefenses() {
        return defenses;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    } // TODO more setters?
}
