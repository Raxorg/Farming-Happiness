package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.game.units.Enemy;

public class GameState {

    private DelayedRemovalArray<Enemy> enemies;
    private DelayedRemovalArray<Bullet> bullets;
    private DelayedRemovalArray<Defense> defenses;
    private int money;
    private int workers;

    public GameState() {
        enemies = new DelayedRemovalArray<>();
        defenses = new DelayedRemovalArray<>();
        bullets = new DelayedRemovalArray<>();
        money = 100;
        workers = 0;
    }

    //----------------------------
    //          METHODS
    //----------------------------

    public void restart() {
        enemies = new DelayedRemovalArray<>();
        defenses = new DelayedRemovalArray<>();
        bullets = new DelayedRemovalArray<>();
        money = 100;
        workers = 0;
    }

    //----------------------------
    //      GETTERS & SETTERS
    //----------------------------

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
    }
}
