package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.entities.Damageable;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.LIFE_BAR_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.LIFE_BAR_SIDES_WIDTH;

public class LifeBar {

    private Damageable damageable;
    private float portionWidth;

    public LifeBar(Damageable damageable) {
        this.damageable = damageable;
        portionWidth = (damageable.getBounds().width - LIFE_BAR_SIDES_WIDTH * 2) / damageable.getInitialLife();
    }

    public void render(SpriteBatch batch) {
        // Left side
        batch.draw(
                Assets.barSides,
                damageable.getBounds().getX(),
                damageable.getBounds().getY() + damageable.getBounds().height,
                LIFE_BAR_SIDES_WIDTH,
                LIFE_BAR_HEIGHT);
        // Center
        for (int i = 0; i < damageable.getLife(); i++) {
            batch.setColor(Color.SKY);
            batch.draw(
                    Assets.centerLifeBar,
                    damageable.getBounds().getX() + LIFE_BAR_SIDES_WIDTH + portionWidth * i,
                    damageable.getBounds().getY() + damageable.getBounds().height,
                    portionWidth,
                    LIFE_BAR_HEIGHT);
        }
        // Right side
        batch.draw(
                Assets.barSides,
                damageable.getBounds().getX() + damageable.getBounds().width - LIFE_BAR_SIDES_WIDTH - portionWidth * (damageable.getInitialLife() - damageable.getLife()),
                damageable.getBounds().getY() + damageable.getBounds().height,
                LIFE_BAR_SIDES_WIDTH,
                LIFE_BAR_HEIGHT);
    }
}
