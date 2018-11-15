package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Button {

    protected TextureRegion texture;
    protected Rectangle bounds;
    protected Color color;

    public Button(TextureRegion texture, float x, float y, float w, float h) {
        this.texture = texture;
        bounds = new Rectangle(x, y, w, h);
        color = Color.WHITE;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(color);
        batch.draw(
                texture,
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
    }

    public float getX() {
        return bounds.x;
    }

    public void setX(float x) {
        bounds.x = x;
    }

    public float getY() {
        return bounds.y;
    }

    public void setY(float y) {
        bounds.y = y;
    }

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
