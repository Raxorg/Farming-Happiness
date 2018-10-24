package com.frontanilla.farminghappyness.game;

public class GameStuff {

    private int money;
    private int workers;

    public GameStuff() {
        money = 100;
        workers = 0;
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
