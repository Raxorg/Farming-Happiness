package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.ProgressBar;
import com.frontanilla.farminghappyness.game.GameEntity;

public abstract class Progressable extends GameEntity {

    protected float progress;
    protected ProgressBar progressBar;

    public Progressable(Rectangle bounds) {
        super(bounds);
        progressBar = new ProgressBar(this);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
