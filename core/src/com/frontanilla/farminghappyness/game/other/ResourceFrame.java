package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_IMAGE_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_IMAGE_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_QUANTITY_X_OFFSET;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_QUANTITY_Y_OFFSET;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_WIDTH;

public class ResourceFrame {

    private BitmapFont font;
    private Color frameColor;
    private Point position;
    private TextureRegion image;
    private int quantity;

    public ResourceFrame(Color frameColor, Point position, TextureRegion image) {
        font = new BitmapFont();
        font.getData().scale(1);
        this.frameColor = frameColor;
        this.position = position;
        this.image = image;
    }

    public void render(SpriteBatch batch) {
        // Frame
        batch.setColor(frameColor);
        batch.draw(
                Assets.resourceFrame,
                position.getX(),
                position.getY(),
                RESOURCE_FRAME_WIDTH,
                RESOURCE_FRAME_HEIGHT);
        // Image
        batch.setColor(Color.WHITE);
        batch.draw(
                image,
                position.getX() + RESOURCE_FRAME_WIDTH / 3 * 2,
                position.getY() + RESOURCE_FRAME_HEIGHT / 2f - RESOURCE_FRAME_IMAGE_HEIGHT / 2f,
                RESOURCE_FRAME_IMAGE_WIDTH,
                RESOURCE_FRAME_IMAGE_HEIGHT);
        // Text
        font.draw(
                batch,
                quantity + "",
                position.getX() + RESOURCE_FRAME_QUANTITY_X_OFFSET,
                position.getY() + RESOURCE_FRAME_QUANTITY_Y_OFFSET + 10f); // TODO use text size calculator
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
