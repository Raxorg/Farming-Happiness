package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.GameEntity;
import com.frontanilla.farminghappyness.game.other.Progressable;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.LIFE_BAR_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.LIFE_BAR_SIDES_WIDTH;

public class ProgressBar {

    private Progressable progressable;
    private float portionWidth;

    public ProgressBar(Progressable progressable) {
        this.progressable = progressable;
        portionWidth = (progressable.getBounds().width - LIFE_BAR_SIDES_WIDTH * 2) / 100;
        portionWidth = (progressable.getBounds().width - LIFE_BAR_SIDES_WIDTH * 2) / 100;
    }

    public void render(SpriteBatch batch) {
        // Left side
        batch.draw(
                Assets.sidesLifeBar,
                progressable.getBounds().getX(),
                progressable.getBounds().getY() + ENEMY_HEIGHT,
                LIFE_BAR_SIDES_WIDTH,
                LIFE_BAR_HEIGHT);
        // Center TODO, draw this according to HP
        for (int i = 0; i < progressable.getProgress(); i++) {
            batch.setColor(Color.CYAN);
            batch.draw(
                    Assets.centerLifeBar,
                    progressable.getBounds().getX() + LIFE_BAR_SIDES_WIDTH + portionWidth * i,
                    progressable.getBounds().getY() + progressable.getBounds().height,
                    portionWidth,
                    LIFE_BAR_HEIGHT);
        }
        // Right side
        batch.draw(
                Assets.sidesLifeBar,
                progressable.getBounds().getX() + ENEMY_WIDTH - LIFE_BAR_SIDES_WIDTH - portionWidth * (100 - progressable.getProgress()),
                progressable.getBounds().getY() + ENEMY_HEIGHT,
                LIFE_BAR_SIDES_WIDTH,
                LIFE_BAR_HEIGHT);
    }
}
