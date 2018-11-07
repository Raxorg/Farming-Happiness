package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.game.units.Enemy;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_CANNON_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_CANNON_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_CANNON_X_OFFSET;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_CANNON_Y_OFFSET;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_COOL_DOWN;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_INITIAL_HEALTH;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_WIDTH;

public class Turret extends Defense {

    private float cannonRotation;
    private float coolDown;

    public Turret(Tile tile) {
        super(
                new Rectangle(
                        tile.getX() + (TILE_SIZE - TURRET_WIDTH) / 2,
                        tile.getY() + (TILE_SIZE - TURRET_WIDTH) / 2,
                        TURRET_WIDTH,
                        TURRET_HEIGHT),
                TURRET_INITIAL_HEALTH);

        position = new Point(bounds.x, bounds.y);
    }

    public void update(float delta) {
        if (coolDown > 0) {
            coolDown = Math.max(0, coolDown - delta);
        }
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                Assets.turretGlow,
                position.getX(),
                position.getY(),
                TURRET_WIDTH,
                TURRET_HEIGHT);
        batch.draw(
                Assets.turretCannon,
                position.getX() + TURRET_CANNON_X_OFFSET,
                position.getY() + TURRET_CANNON_Y_OFFSET,
                TURRET_CANNON_WIDTH / 2,
                TURRET_CANNON_HEIGHT / 2,
                TURRET_CANNON_WIDTH,
                TURRET_CANNON_HEIGHT,
                1,
                1,
                cannonRotation);
        lifeBar.render(batch);
    }

    public Bullet shoot(Enemy e) {
        coolDown = TURRET_COOL_DOWN;
        Point bulletSpawnPoint = new Point(
                getCenter().getX(),
                getCenter().getY() + TURRET_CANNON_HEIGHT / 2f + TURRET_CANNON_Y_OFFSET - TURRET_HEIGHT / 2f);
        return new Bullet(bulletSpawnPoint, e);
    }

    public float getCoolDown() {
        return coolDown;
    }

    public Point getCenter() {
        return new Point(position.getX() + TURRET_WIDTH / 2, position.getY() + TURRET_HEIGHT / 2);
    }

    public void setCannonRotation(float cannonRotation) {
        this.cannonRotation = cannonRotation;
    }
}
