package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.utils.Assets;

public class LevelLabel {

    private TextureRegion textureRegion;
    private float alpha;

    public LevelLabel() {
        startLevel(1);
    }

    public void startLevel(int level) {
        switch (level) {
            case 1:
                textureRegion = Assets.level1;
                break;
            case 2:
                textureRegion = Assets.level2;
                break;
            case 3:
                textureRegion = Assets.level3;
                break;
            case 4:
                textureRegion = Assets.level4;
                break;
        }
        alpha = 1;
    }

    public void update(float delta) {
        alpha -= delta * 0.5f;
        if (alpha <= 0) {
            alpha = 0;
        }
    }

    public void render(SpriteBatch batch) {
        batch.setColor(1, 1, 1, alpha);
        batch.draw(
                textureRegion,
                Gdx.graphics.getWidth() / 2 - (Gdx.graphics.getWidth() * 0.8f) / 2,
                Gdx.graphics.getHeight() / 2 - (Gdx.graphics.getHeight() * 0.4f) / 2,
                Gdx.graphics.getWidth() * 0.8f,
                Gdx.graphics.getHeight() * 0.4f);
    }
}
