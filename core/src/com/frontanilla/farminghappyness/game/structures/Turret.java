package com.frontanilla.farminghappyness.game.structures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.Bullet;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.game.units.Enemy;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_COOL_DOWN;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_RANGE;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_SIZE;

public class Turret {

    private Point position;
    private float cannonRotation;
    private float coolDown;

    public Turret(Tile tile) {
        float x = tile.getX() + (TILE_SIZE - TURRET_SIZE) / 2;
        float y = tile.getY() + (TILE_SIZE - TURRET_SIZE) / 2;
        position = new Point(x, y);
    }

    public void update(float delta) {
        if (coolDown > 0) {
            coolDown = Math.max(0, coolDown - delta);
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(
                Assets.turret,
                position.getX(),
                position.getY(),
                TURRET_SIZE,
                TURRET_SIZE);
        batch.draw(
                Assets.turretCannon,
                position.getX(),
                position.getY(),
                TURRET_SIZE / 2,
                TURRET_SIZE / 2,
                TURRET_SIZE,
                TURRET_SIZE,
                1,
                1,
                cannonRotation);
        batch.draw(
                Assets.rangeCircle,
                position.getX() - TURRET_RANGE + TURRET_SIZE / 2,
                position.getY() - TURRET_RANGE + TURRET_SIZE / 2,
                TURRET_RANGE * 2,
                TURRET_RANGE * 2);
    }

    public Bullet shoot(Enemy e) {
        coolDown = TURRET_COOL_DOWN;
        Point bulletPosition = getCenter();
        Bullet bullet = new Bullet(bulletPosition);
        return bullet;
    }

    public float getCoolDown() {
        return coolDown;
    }

    public Point getCenter() {
        return new Point(position.getX() + TURRET_SIZE / 2, position.getY() + TURRET_SIZE / 2);
    }

    public void setCannonRotation(float cannonRotation) {
        this.cannonRotation = cannonRotation;
    }
}
