package com.frontanilla.farminghappyness.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameEntity {

    protected Rectangle bounds;

    public GameEntity(Rectangle bounds) {
        this.bounds = bounds;
    }

    public abstract void render(SpriteBatch batch);

    public Rectangle getBounds() {
        return bounds;
    }
}
