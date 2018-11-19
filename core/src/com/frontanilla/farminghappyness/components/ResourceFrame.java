package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_IMAGE_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_IMAGE_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_QUANTITY_X_OFFSET;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_QUANTITY_Y_OFFSET;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_WIDTH;

public class ResourceFrame {

    private BitmapFont font;
    private Color frameColor;
    private Rectangle bounds;
    private TextureRegion image;
    private int quantity;

    public ResourceFrame(Color frameColor, float x, float y, TextureRegion image) {
        font = new BitmapFont();
        font.getData().scale(2);
        this.frameColor = frameColor;
        bounds = new Rectangle(x, y, RESOURCE_FRAME_WIDTH, RESOURCE_FRAME_HEIGHT);
        this.image = image;
    }

    public void render(SpriteBatch batch) {
        // Frame
        batch.setColor(frameColor);
        batch.draw(
                Assets.resourceFrame,
                bounds.x,
                bounds.y,
                RESOURCE_FRAME_WIDTH,
                RESOURCE_FRAME_HEIGHT);
        // Image
        batch.setColor(Color.WHITE);
        batch.draw(
                image,
                bounds.x + RESOURCE_FRAME_WIDTH / 3 * 2,
                bounds.y + RESOURCE_FRAME_HEIGHT / 2f - RESOURCE_FRAME_IMAGE_HEIGHT / 2f,
                RESOURCE_FRAME_IMAGE_WIDTH,
                RESOURCE_FRAME_IMAGE_HEIGHT);
        // Text
        font.draw(
                batch,
                quantity + "",
                bounds.x + RESOURCE_FRAME_QUANTITY_X_OFFSET,
                bounds.y + RESOURCE_FRAME_QUANTITY_Y_OFFSET + 10f); // TODO use text size calculator
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
