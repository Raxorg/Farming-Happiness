package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class ImageTextButton extends Button {
    public ImageTextButton(float x, float y, float width, float height, boolean centered,
                           TextureRegion texture, TextureRegion image,
                           Color color, boolean hooverButton) {
        super(x, y, width, height, texture, color, hooverButton);
    }
}
