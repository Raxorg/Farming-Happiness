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

    public LifeBar(Enemy enemy) {
        this.enemy = enemy;
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
        batch.setColor(Color.BLUE);
        batch.draw(
                Assets.centerLifeBar,
                enemy.getPosition().getX() + ENEMY_LIFE_BAR_SIDES_WIDTH,
                enemy.getPosition().getY() + ENEMY_HEIGHT,
                ENEMY_WIDTH - ENEMY_LIFE_BAR_SIDES_WIDTH * 2,
                ENEMY_LIFE_BAR_HEIGHT);
        // Left side
        batch.draw(
                Assets.sidesLifeBar,
                enemy.getPosition().getX() + ENEMY_WIDTH - ENEMY_LIFE_BAR_SIDES_WIDTH,
                enemy.getPosition().getY() + ENEMY_HEIGHT,
                ENEMY_LIFE_BAR_SIDES_WIDTH,
                ENEMY_LIFE_BAR_HEIGHT);
    }
}
