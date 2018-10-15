package com.frontanilla.farminghappyness.game.structures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Point;

public abstract class Content {

    protected Point position, center;

    public Point getCenter() {
        return center;
    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

    public Point getPosition() {
        return position;
    }
}