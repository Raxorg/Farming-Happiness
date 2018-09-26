package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class ImageButton extends Button {

    protected TextureRegion image;
    protected float textureSize;

    public ImageButton(float x, float y, float width, float height,
                       TextureRegion texture, TextureRegion image,
                       Color color, boolean hooverButton) {
        super(x, y, width, height, texture, color, hooverButton);
        textureSize = (height / 10) * 6;
        this.image = image;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        batch.draw(
                image,
                bounds.x + bounds.width / 2 - textureSize / 2,
                bounds.y + bounds.height / 2 - textureSize / 2,
                textureSize,
                textureSize
        );
    }
}
