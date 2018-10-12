package com.frontanilla.farminghappyness.game.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.frontanilla.farminghappyness.game.other.LifeBar;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_WIDTH;

public abstract class Enemy {

    protected TextureRegion texture;
    protected Point position;
    protected float speed, angle;
    protected int life;
    protected LifeBar lifeBar;

    public Enemy(TextureRegion texture, float x, float y, float speed, int life) {
        this.texture = texture;
        position = new Point(x, y);
        this.speed = speed;
        this.life = life;
        lifeBar = new LifeBar(this);
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                texture,
                position.getX(),
                position.getY(),
                ENEMY_WIDTH,
                ENEMY_HEIGHT);
        lifeBar.render(batch);
    }

    public void move(float delta) {
        float cos = MathUtils.cosDeg(angle);
        float sin = MathUtils.sinDeg(angle);
        float x = delta * cos;
        float y = delta * sin;
        position.setX(position.getX() + x * speed);
        position.setY(position.getY() + y * speed);
    }

    public Point getPosition() {
        return position;
    }

    public Point getCenter() {
        return new Point(position.getX() + ENEMY_WIDTH / 2, position.getY() + ENEMY_HEIGHT / 2);
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public boolean isAlive() {
        return life > 0;
    }
}
