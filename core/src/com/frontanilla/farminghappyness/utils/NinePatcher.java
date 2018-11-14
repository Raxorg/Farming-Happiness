package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class NinePatcher {

    private TextureRegion topLeft, topRight, bottomLeft, bottomRight;
    private TextureRegion left, top, right, bottom, center;
    private Rectangle bounds;
    private float borderSize;

    public NinePatcher(TextureRegion texture, float borderSize, int borderPixels) {
        this.borderSize = borderSize;
        // Corners
        topLeft = new TextureRegion(texture,
                0,
                0,
                borderPixels,
                borderPixels);
        topRight = new TextureRegion(texture,
                texture.getRegionWidth() - borderPixels,
                0,
                borderPixels,
                borderPixels);
        bottomLeft = new TextureRegion(texture,
                0,
                texture.getRegionHeight() - borderPixels,
                borderPixels,
                borderPixels);
        bottomRight = new TextureRegion(texture,
                texture.getRegionWidth() - borderPixels,
                texture.getRegionHeight() - borderPixels,
                borderPixels,
                borderPixels);
        // Sides
        left = new TextureRegion(texture,
                0,
                borderPixels,
                borderPixels,
                texture.getRegionHeight() - borderPixels - borderPixels);
        top = new TextureRegion(texture,
                borderPixels,
                0,
                texture.getRegionWidth() - borderPixels - borderPixels,
                borderPixels);
        right = new TextureRegion(texture,
                texture.getRegionWidth() - borderPixels,
                borderPixels,
                borderPixels,
                texture.getRegionHeight() - borderPixels - borderPixels);
        bottom = new TextureRegion(texture,
                borderPixels,
                texture.getRegionHeight() - borderPixels,
                texture.getRegionWidth() - borderPixels - borderPixels,
                borderPixels);
        // Center
        center = new TextureRegion(texture,
                borderPixels,
                borderPixels,
                texture.getRegionWidth() - borderPixels - borderPixels,
                texture.getRegionHeight() - borderPixels - borderPixels);
        bounds = new Rectangle(0, 0, texture.getRegionWidth(), texture.getRegionHeight());
    }

    public void render(SpriteBatch batch) {
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
}
