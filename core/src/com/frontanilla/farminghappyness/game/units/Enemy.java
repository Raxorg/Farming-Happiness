package com.frontanilla.farminghappyness.game.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_WIDTH;

public abstract class Enemy {

    protected TextureRegion texture;
    protected Point position;
    protected float speed;

    public Enemy(TextureRegion texture, float x, float y, float speed) {
        this.texture = texture;
        position = new Point(x, y);
        this.speed = speed;
    }

    public void render(SpriteBatch batch) {
        batch.draw(
                texture,
                position.getX(),
                position.getY(),
                ENEMY_WIDTH,
                ENEMY_HEIGHT);
    }

    public void move(float x, float y) {
        position.setX(position.getX() + x * speed);
        position.setY(position.getY() + y * speed);
    }

    public Point getCenter() {
        return new Point(position.getX() + ENEMY_WIDTH / 2, position.getY() + ENEMY_HEIGHT / 2);
    }
}
