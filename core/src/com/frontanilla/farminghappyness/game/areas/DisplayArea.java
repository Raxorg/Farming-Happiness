package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Assets;

public class DisplayArea {

    public void render(SpriteBatch staticBatch) {
        staticBatch.begin();
        staticBatch.draw(
                Assets.triangle,
                Gdx.graphics.getWidth() / 2f - Assets.triangle.getRegionWidth() / 2f,
                0,
                Gdx.graphics.getWidth() / 10f,
                Gdx.graphics.getWidth() / 20f);
        staticBatch.end();
    }
}
