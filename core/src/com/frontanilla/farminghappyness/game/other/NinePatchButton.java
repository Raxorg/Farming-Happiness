package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NinePatchButton extends Button {

    private NinePatch ninePatch;

    public NinePatchButton(NinePatch ninePatch, float x, float y, float width, float height) {
        super(null, x, y, width, height);
        this.ninePatch = ninePatch;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (ninePatch == null) {
            return;
        }
        batch.setColor(color);
        ninePatch.draw(
                batch,
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
    }
}
