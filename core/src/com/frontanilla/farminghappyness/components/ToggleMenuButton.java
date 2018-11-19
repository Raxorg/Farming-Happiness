package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.frontanilla.farminghappyness.utils.Constants.MENU_BUTTON_IMAGE_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_BUTTON_IMAGE_X_OFFSET;

public class ToggleMenuButton extends Button {

    private int cost;
    private TextureRegion image;
    private float imageWidth, imageHeight;

    public ToggleMenuButton(int cost, TextureRegion frame, TextureRegion image, float x, float y, float w, float h, float imageWidth, float imageHeight) {
        super(frame, x, y, w, h);
        this.cost = cost;
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        color = Color.BLUE;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        batch.setColor(Color.WHITE);
        batch.draw(
                image,
                bounds.x + MENU_BUTTON_IMAGE_X_OFFSET,
                bounds.y + MENU_BUTTON_IMAGE_X_OFFSET,
                MENU_BUTTON_IMAGE_WIDTH,
                imageHeight * MENU_BUTTON_IMAGE_WIDTH / imageWidth);
    }

    public int getCost() {
        return cost;
    }
}
