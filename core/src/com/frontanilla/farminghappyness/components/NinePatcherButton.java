package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NinePatcherButton extends Button {

    private NinePatcher ninePatcher;

    public NinePatcherButton(TextureRegion textureRegion, float borderSize, int borderPixels,
                             float x, float y, float width, float height) {
        super(textureRegion, x, y, width, height);
        ninePatcher = new NinePatcher(textureRegion, borderSize, borderPixels);
        ninePatcher.setPosition(x, y);
        ninePatcher.setWidth(width);
        ninePatcher.setHeight(height);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setColor(color);
        ninePatcher.render(batch);
    }
}
