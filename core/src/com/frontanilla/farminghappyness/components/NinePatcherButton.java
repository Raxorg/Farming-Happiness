package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.utils.NinePatcher;

public class NinePatcherButton extends Button {

    private NinePatcher ninePatcher;

    public NinePatcherButton(TextureRegion textureRegion, float borderSize, int borderPixels,
                             float x, float y, float width, float height) {
        super(textureRegion, x, y, width, height);
        if (textureRegion != null) {
            ninePatcher = new NinePatcher(textureRegion, borderSize, borderPixels);
            ninePatcher.setPosition(x, y);
            ninePatcher.setWidth(width);
            ninePatcher.setHeight(height);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if (ninePatcher != null) {
            batch.setColor(color);
            ninePatcher.render(batch);
        }
    }
}
