package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class NinePatcher {

    private TextureRegion topLeft, topRight, bottomLeft, bottomRight;
    private TextureRegion left, top, right, bottom, center;
    private Rectangle bounds;
    private float margin;

    public NinePatcher(TextureRegion texture, int margin) {
        this.margin = margin;
        // Corners
        topLeft = new TextureRegion(texture, 0, 0, margin, margin);
        topRight = new TextureRegion(texture, texture.getRegionWidth() - margin, 0, margin, margin);
        bottomLeft = new TextureRegion(texture, 0, texture.getRegionHeight() - margin, margin, margin);
        bottomRight = new TextureRegion(texture, texture.getRegionWidth() - margin, texture.getRegionHeight() - margin, margin, margin);
        // Sides
        left = new TextureRegion(texture,
                0,
                margin,
                margin,
                texture.getRegionHeight() - margin - margin);
        top = new TextureRegion(texture,
                margin,
                0,
                texture.getRegionWidth() - margin - margin,
                margin);
        right = new TextureRegion(texture,
                texture.getRegionWidth() - margin,
                margin,
                margin,
                texture.getRegionHeight() - margin - margin);
        bottom = new TextureRegion(texture,
                margin,
                texture.getRegionHeight() - margin,
                texture.getRegionWidth() - margin - margin,
                margin);
        // Lastly
        center = new TextureRegion(texture,
                margin,
                margin,
                texture.getRegionWidth() - margin - margin,
                texture.getRegionHeight() - margin - margin);
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
                bounds.y + bounds.height - margin,
                margin,
                margin);
        // Top Right
        batch.draw(
                topRight,
                bounds.x + bounds.width - margin,
                bounds.y + bounds.height - margin,
                margin,
                margin);
        // Bottom Left
        batch.draw(
                bottomLeft,
                bounds.x,
                bounds.y,
                margin,
                margin);
        // Bottom Right
        batch.draw(
                bottomRight,
                bounds.x + bounds.width - margin,
                bounds.y,
                margin,
                margin);
    }

    private void renderSides(SpriteBatch batch) {
        // Top
        batch.draw(
                top,
                bounds.x + margin,
                bounds.y + bounds.height - margin,
                bounds.width - margin - margin,
                margin);
        // Right
        batch.draw(
                right,
                bounds.x + bounds.width - margin,
                bounds.y + margin,
                margin,
                bounds.height - margin - margin);
        // Bottom
        batch.draw(
                bottom,
                bounds.x + margin,
                bounds.y,
                bounds.width - margin - margin,
                margin);
        // Left
        batch.draw(
                left,
                bounds.x,
                bounds.y + margin,
                margin,
                bounds.height - margin - margin);
    }

    private void renderCenter(SpriteBatch batch) {
        batch.draw(
                center,
                bounds.x + margin,
                bounds.y + margin,
                bounds.width - margin - margin,
                bounds.height - margin - margin);
    }

    public void setWidth(float width) {
        bounds.width = width;
    }

    public void setHeight(float height) {
        bounds.height = height;
    }

    public void setMargin(float margin) {
        this.margin = margin;
    }

    public void setPosition(float x, float y) {
        bounds.x = x;
        bounds.y = y;
    }
}
