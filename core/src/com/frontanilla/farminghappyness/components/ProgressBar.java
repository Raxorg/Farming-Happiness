package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.other.Progressable;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.PROGRESS_BAR_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.PROGRESS_BAR_SIDES_WIDTH;

public class ProgressBar {

    private Progressable progressable;
    private float portionWidth;

    public ProgressBar(Progressable progressable) {
        this.progressable = progressable;
        portionWidth = (progressable.getBounds().width - PROGRESS_BAR_SIDES_WIDTH * 2) / 100;
    }

    public void render(SpriteBatch batch) {
        // Left side
        batch.draw(
                Assets.barSides,
                progressable.getBounds().getX(),
                progressable.getBounds().getY() + progressable.getBounds().height,
                PROGRESS_BAR_SIDES_WIDTH,
                PROGRESS_BAR_HEIGHT);
        // Center TODO, draw this according to Progress
        for (int i = 0; i < progressable.getProgress(); i++) {
            batch.setColor(Color.CYAN);
            batch.draw(
                    Assets.centerLifeBar,
                    progressable.getBounds().getX() + PROGRESS_BAR_SIDES_WIDTH + portionWidth * i,
                    progressable.getBounds().getY() + progressable.getBounds().height,
                    portionWidth,
                    PROGRESS_BAR_HEIGHT);
        }
        // Right side
        batch.draw(
                Assets.barSides,
                progressable.getBounds().getX() + progressable.getBounds().width - PROGRESS_BAR_SIDES_WIDTH - portionWidth * (100 - progressable.getProgress()),
                progressable.getBounds().getY() + progressable.getBounds().height,
                PROGRESS_BAR_SIDES_WIDTH,
                PROGRESS_BAR_HEIGHT);
    }
}
