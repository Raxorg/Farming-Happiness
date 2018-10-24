package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.utils.Point;

public abstract class Defense {

    protected Rectangle bounds;
    protected Point position, center;
    protected int health;

    public Rectangle getBounds() {
        return bounds;
    }

    public Point getPosition() {
        return position;
    }

    public Point getCenter() {
        return center;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);
}