package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.utils.Point;

public abstract class Defense {

    protected Rectangle bounds;
    protected Point position, center;

    public Point getCenter() {
        return center;
    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

    public Point getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}