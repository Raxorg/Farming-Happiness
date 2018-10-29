package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.GameEntity;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.LIFE_BAR_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.LIFE_BAR_SIDES_WIDTH;

public class LifeBar {

    private GameEntity entity;
    private float portionWidth;

    public LifeBar(GameEntity entity) {
        this.entity = entity;
        portionWidth = (ENEMY_WIDTH - LIFE_BAR_SIDES_WIDTH * 2) / entity.getInitialLife();
        portionWidth = (ENEMY_WIDTH - LIFE_BAR_SIDES_WIDTH * 2) / entity.getInitialLife();
    }

    public void render(SpriteBatch batch) {
        // Left side
        batch.draw(
                Assets.sidesLifeBar,
                entity.getPosition().getX(),
                entity.getPosition().getY() + ENEMY_HEIGHT,
                LIFE_BAR_SIDES_WIDTH,
                LIFE_BAR_HEIGHT);
        // Center TODO, draw this according to HP
        for (int i = 0; i < entity.getLife(); i++) {
            batch.setColor(Color.RED);
            batch.draw(
                    Assets.centerLifeBar,
                    entity.getPosition().getX() + LIFE_BAR_SIDES_WIDTH + portionWidth * i,
                    entity.getPosition().getY() + ENEMY_HEIGHT,
                    portionWidth,
                    LIFE_BAR_HEIGHT);
        }
        // Left side
        batch.draw(
                Assets.sidesLifeBar,
                entity.getPosition().getX() + ENEMY_WIDTH - LIFE_BAR_SIDES_WIDTH - portionWidth * (entity.getInitialLife() - entity.getLife()),
                entity.getPosition().getY() + ENEMY_HEIGHT,
                LIFE_BAR_SIDES_WIDTH,
                LIFE_BAR_HEIGHT);
    }
}
