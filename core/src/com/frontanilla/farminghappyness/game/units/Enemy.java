package com.frontanilla.farminghappyness.game.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_WIDTH;

public abstract class Enemy {

    protected TextureRegion texture;
    protected float x, y, speed;

    public Enemy(TextureRegion texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
    }

    public void render(SpriteBatch batch) {
        batch.draw(
                texture,
                x,
                y,
                ENEMY_WIDTH,
                ENEMY_HEIGHT);
    }

    public void move() {

    }

    public float getXCenter() {
        return x + ENEMY_WIDTH;
    }

    public float getYCenter() {
        return y + ENEMY_HEIGHT;
    }
}
