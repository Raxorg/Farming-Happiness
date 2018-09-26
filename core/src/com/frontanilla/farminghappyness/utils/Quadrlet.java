package com.frontanilla.farminghappyness.utils;

import com.frontanilla.farminghappyness.core.Player;

public class Quadrlet {

    private Player owner;
    private int representation;
    private int health;
    private float rotation;

    public Quadrlet(Player owner, int representation, int health, float rotation) {
        this.owner = owner;
        this.representation = representation;
        this.health = health;
        this.rotation = rotation;
    }

    public Player owner() {
        return owner;
    }

    public int representation() {
        return representation;
    }

    public int health() {
        return health;
    }

    public float rotation() {
        return rotation;
    }
}
