package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SPACING;
import static com.frontanilla.farminghappyness.utils.Constants.TRAP_SIZE;

public class Trap extends Defense {

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(
                Assets.trap,
                position.getX() - TILE_SPACING,
                position.getY(),
                TRAP_SIZE + TILE_SPACING * 2,
                TRAP_SIZE);
    }
}
