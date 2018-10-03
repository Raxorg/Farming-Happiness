package com.frontanilla.farminghappyness.game.structures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Constants;

public class Turret {

    public void render(SpriteBatch batch) {
        batch.draw(
                Assets.turret,
                0,
                0,
                Constants.TURRET_WIDTH,
                Constants.TURRET_HEIGHT);
    }
}
