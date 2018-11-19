package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class NinePatcher {

    private TextureRegion topLeft, topRight, bottomLeft, bottomRight;
    private TextureRegion left, top, right, bottom, center;
    private Rectangle bounds;
    private float borderSize;
    private Color color;

    public NinePatcher(TextureRegion textureRegion, float borderSize, int borderPixels) {
        this.borderSize = borderSize;
        // Corners
        topLeft = new TextureRegion(textureRegion,
                0,
                0,
                borderPixels,
                borderPixels);
        topRight = new TextureRegion(textureRegion,
                textureRegion.getRegionWidth() - borderPixels,
                0,
                borderPixels,
                borderPixels);
        bottomLeft = new TextureRegion(textureRegion,
                0,
                textureRegion.getRegionHeight() - borderPixels,
                borderPixels,
                borderPixels);
        bottomRight = new TextureRegion(textureRegion,
                textureRegion.getRegionWidth() - borderPixels,
                textureRegion.getRegionHeight() - borderPixels,
                borderPixels,
                borderPixels);
        // Sides
        left = new TextureRegion(textureRegion,
                0,
                borderPixels,
                borderPixels,
                textureRegion.getRegionHeight() - borderPixels - borderPixels);
        top = new TextureRegion(textureRegion,
                borderPixels,
                0,
                textureRegion.getRegionWidth() - borderPixels - borderPixels,
                borderPixels);
        right = new TextureRegion(textureRegion,
                textureRegion.getRegionWidth() - borderPixels,
                borderPixels,
                borderPixels,
                textureRegion.getRegionHeight() - borderPixels - borderPixels);
        bottom = new TextureRegion(textureRegion,
                borderPixels,
                textureRegion.getRegionHeight() - borderPixels,
                textureRegion.getRegionWidth() - borderPixels - borderPixels,
                borderPixels);
        // Center
        center = new TextureRegion(textureRegion,
                borderPixels,
                borderPixels,
                textureRegion.getRegionWidth() - borderPixels - borderPixels,
                textureRegion.getRegionHeight() - borderPixels - borderPixels);
        // Lastly
        bounds = new Rectangle(0, 0, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        color = Color.WHITE;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(color);
        renderCorners(batch);
        renderSides(batch);
        renderCenter(batch);
    }

    private void renderCorners(SpriteBatch batch) {
        // Top Left
        batch.draw(
                topLeft,
                bounds.x,
                bounds.y + bounds.height - borderSize,
                borderSize,
                borderSize);
        // Top Right
        batch.draw(
                topRight,
                bounds.x + bounds.width - borderSize,
                bounds.y + bounds.height - borderSize,
                borderSize,
                borderSize);
        // Bottom Left
        batch.draw(
                bottomLeft,
                bounds.x,
                bounds.y,
                borderSize,
                borderSize);
        // Bottom Right
        batch.draw(
                bottomRight,
                bounds.x + bounds.width - borderSize,
                bounds.y,
                borderSize,
                borderSize);
    }

    private void renderSides(SpriteBatch batch) {
        // Top
        batch.draw(
                top,
                bounds.x + borderSize,
                bounds.y + bounds.height - borderSize,
                bounds.width - borderSize - borderSize,
                borderSize);
        // Right
        batch.draw(
                right,
                bounds.x + bounds.width - borderSize,
                bounds.y + borderSize,
                borderSize,
                bounds.height - borderSize - borderSize);
        // Bottom
        batch.draw(
                bottom,
                bounds.x + borderSize,
                bounds.y,
                bounds.width - borderSize - borderSize,
                borderSize);
        // Left
        batch.draw(
                left,
                bounds.x,
                bounds.y + borderSize,
                borderSize,
                bounds.height - borderSize - borderSize);
    }

    private void renderCenter(SpriteBatch batch) {
        batch.draw(
                center,
                bounds.x + borderSize,
                bounds.y + borderSize,
                bounds.width - borderSize - borderSize,
                bounds.height - borderSize - borderSize);
    }

    public void setWidth(float width) {
        bounds.width = width;
    }

    public void setHeight(float height) {
        bounds.height = height;
    }

    public void setBorderSize(float borderSize) {
        this.borderSize = borderSize;
    }

    public void setPosition(float x, float y) {
        bounds.x = x;
        bounds.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
