package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Assets;

public class NormalArea {

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                batch.draw(
                        Assets.grass,
                        Gdx.graphics.getWidth() / 5 * i,
                        Gdx.graphics.getHeight() / 4 * j,
                        Gdx.graphics.getWidth() / 5,
                        Gdx.graphics.getHeight() / 4);
            }
        }
        batch.draw(
                Assets.river1,
                Assets.river1.getRegionWidth() * 0,
                Gdx.graphics.getHeight() - Assets.river1.getRegionHeight());
        batch.draw(
                Assets.river2,
                Assets.river1.getRegionWidth() * 1,
                Gdx.graphics.getHeight() - Assets.river1.getRegionHeight());
        batch.draw(
                Assets.river3,
                Assets.river1.getRegionWidth() * 2,
                Gdx.graphics.getHeight() - Assets.river1.getRegionHeight());
        batch.draw(
                Assets.river2,
                Assets.river1.getRegionWidth() * 3,
                Gdx.graphics.getHeight() - Assets.river1.getRegionHeight());
        batch.draw(
                Assets.river4,
                Assets.river1.getRegionWidth() * 4,
                Gdx.graphics.getHeight() - Assets.river1.getRegionHeight());
    }
}
