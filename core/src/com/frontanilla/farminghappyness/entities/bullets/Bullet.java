package com.frontanilla.farminghappyness.entities.bullets;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.entities.Entity;
import com.frontanilla.farminghappyness.entities.cellables.Cellable;

import java.util.ArrayList;

public abstract class Bullet extends Entity {

    protected Vector2 velocity;
    protected ArrayList<Cellable> collidableCellables;

    public Bullet(Player player, Polygon bounds, int health, float width, float height,
                  float rotation, Vector2 velocity) {
        super(player, bounds, health, width, height, rotation);
        this.velocity = velocity;
        collidableCellables = new ArrayList<>();
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setCollidableCellables(ArrayList<Cellable> collidableCellables) {
        this.collidableCellables = collidableCellables;
    }
}