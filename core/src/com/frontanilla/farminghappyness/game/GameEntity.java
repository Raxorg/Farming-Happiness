package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.LifeBar;
import com.frontanilla.farminghappyness.utils.Point;

public abstract class GameEntity {

    protected Rectangle bounds;

    public GameEntity(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
