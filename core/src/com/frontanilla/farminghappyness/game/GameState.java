package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.areas.AmbientArea;
import com.frontanilla.farminghappyness.game.areas.DefenseArea;
import com.frontanilla.farminghappyness.game.areas.DisplayArea;
import com.frontanilla.farminghappyness.game.areas.FarmingArea;
import com.frontanilla.farminghappyness.game.areas.RiverArea;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.entities.plants.Plant;
import com.frontanilla.farminghappyness.game.entities.units.Enemy;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.game.other.Level;

public class GameState {

    // Areas
    private RiverArea riverArea;
    private AmbientArea ambientArea;
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
    private int availableWorkers, maxWorkers;
    // Level
    private Level level;

    public GameState() {
        restart();
    }

    //----------------------------
    //          METHODS
    //----------------------------

    public void restart() {
        riverArea = new RiverArea();
        ambientArea = new AmbientArea();
        farmingArea = new FarmingArea();
        defenseArea = new DefenseArea();
        displayArea = new DisplayArea();
        plants = new DelayedRemovalArray<>();
        enemies = new DelayedRemovalArray<>();
        defenses = new DelayedRemovalArray<>();
        bullets = new DelayedRemovalArray<>();
        money = 7;
        availableWorkers = 5;
        maxWorkers = 5;
        level = Level.level1.restart();
    }

    //----------------------------
    //      GETTERS & SETTERS
    //----------------------------

    public RiverArea getRiverArea() {
        return riverArea;
    }

    public AmbientArea getAmbientArea() {
        return ambientArea;
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

    public int getMaxWorkers() {
        return maxWorkers;
    }

    public void setMaxWorkers(int maxWorkers) {
        this.maxWorkers = maxWorkers;
    }

    public int getAvailableWorkers() {
        return availableWorkers;
    }

    public void setAvailableWorkers(int availableWorkers) {
        this.availableWorkers = availableWorkers;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
