package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.game.GameEntity;

public abstract class Progressable extends GameEntity {

    protected float progress;

    public Progressable(Rectangle bounds) {
        super(bounds);
    }

    public float getProgress() {
        return progress;
    }
}
