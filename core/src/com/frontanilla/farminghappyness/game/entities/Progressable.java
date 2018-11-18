package com.frontanilla.farminghappyness.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.ProgressBar;

public abstract class Progressable extends GameEntity {

    protected float progress, maxProgress;
    protected ProgressBar progressBar;

    public Progressable(Rectangle bounds, float maxProgress) {
        super(bounds);
        this.maxProgress = maxProgress;
        progressBar = new ProgressBar(this);
    }

    public float getProgress() {
        return progress;
    }

    public float getMaxProgress() {
        return maxProgress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
