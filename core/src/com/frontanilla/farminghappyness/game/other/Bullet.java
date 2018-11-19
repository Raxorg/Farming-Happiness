package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.frontanilla.farminghappyness.game.entities.units.Enemy;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Point;
import com.frontanilla.farminghappyness.utils.Util;

import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_BULLET_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_BULLET_SPEED;

public class Bullet {

    private Point position;
    private float angle;
    private Enemy target;
    private boolean exploded;

    // TODO Put this in Turret class

    public Bullet(Point position, Enemy target) {
        this.position = new Point(position.getX() - TURRET_BULLET_SIZE / 2, position.getY() - TURRET_BULLET_SIZE / 2);
        this.target = target;
        angle = Util.getAngle(this.position, target.getCenter());
    }

    public void update(float delta) {
        // Movement
        float cos = MathUtils.cosDeg(angle);
        float sin = MathUtils.sinDeg(angle);
        float x = delta * cos;
        float y = delta * sin;
        position.setX(position.getX() + x * TURRET_BULLET_SPEED);
        position.setY(position.getY() + y * TURRET_BULLET_SPEED);
        // Collision detection
        if (Util.getDistance(getCenter(), target.getCenter()) < ENEMY_WIDTH) {
            target.setLife(target.getLife() - 1);
            exploded = true;
        }
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

    private Point getCenter() {
        return new Point(position.getX() + TURRET_BULLET_SIZE / 2, position.getY() + TURRET_BULLET_SIZE / 2);
    }

    public boolean hasExploded() {
        return exploded;
    }
}
