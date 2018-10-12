package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.TURRET_BULLET_SIZE;

public class Bullet {

    private Point position;
    private Rectangle bounds;

    public Bullet(Point position) {
        this.position = position;
        bounds = new Rectangle(position.getX(), position.getY(), TURRET_BULLET_SIZE, TURRET_BULLET_SIZE);
    }

    public void update(float delta) {

    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                Assets.turretBullet,
                position.getX(),
                position.getY(),
                TURRET_BULLET_SIZE,
                TURRET_BULLET_SIZE);
    }
}
