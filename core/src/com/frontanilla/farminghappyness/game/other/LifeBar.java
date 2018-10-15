package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.units.Enemy;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_LIFE_BAR_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_LIFE_BAR_SIDES_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_WIDTH;

public class LifeBar {

    private Enemy enemy;
    private float portionWidth;

    public LifeBar(Enemy enemy) {
        this.enemy = enemy;
        portionWidth = (ENEMY_WIDTH - ENEMY_LIFE_BAR_SIDES_WIDTH * 2) / enemy.getInitialLife();
    }

    public void render(SpriteBatch batch) {
        // Left side
        batch.draw(
                Assets.sidesLifeBar,
                enemy.getPosition().getX(),
                enemy.getPosition().getY() + ENEMY_HEIGHT,
                ENEMY_LIFE_BAR_SIDES_WIDTH,
                ENEMY_LIFE_BAR_HEIGHT);
        // Center TODO, draw this according to HP
        for (int i = 0; i < enemy.getLife(); i++) {
            batch.setColor(Color.RED);
            batch.draw(
                    Assets.centerLifeBar,
                    enemy.getPosition().getX() + ENEMY_LIFE_BAR_SIDES_WIDTH + portionWidth * i,
                    enemy.getPosition().getY() + ENEMY_HEIGHT,
                    portionWidth,
                    ENEMY_LIFE_BAR_HEIGHT);
        }
        // Left side
        batch.draw(
                Assets.sidesLifeBar,
                enemy.getPosition().getX() + ENEMY_WIDTH - ENEMY_LIFE_BAR_SIDES_WIDTH - portionWidth * (enemy.getInitialLife() - enemy.getLife()),
                enemy.getPosition().getY() + ENEMY_HEIGHT,
                ENEMY_LIFE_BAR_SIDES_WIDTH,
                ENEMY_LIFE_BAR_HEIGHT);
    }
}
