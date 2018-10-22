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
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_COOL_DOWN;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_WIDTH;

public class Turret extends Defense {

    private float cannonRotation;
    private float coolDown;

    public Turret(Tile tile) {
        float x = tile.getX() + (TILE_SIZE - TURRET_WIDTH) / 2;
        float y = tile.getY() + (TILE_SIZE - TURRET_WIDTH) / 2;
        position = new Point(x, y);

        bounds = new Rectangle(x, y, TURRET_WIDTH, TURRET_HEIGHT);
    }

    public void update(float delta) {
        if (coolDown > 0) {
            coolDown = Math.max(0, coolDown - delta);
        }
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                Assets.turret,
                position.getX(),
                position.getY(),
                TURRET_WIDTH,
                TURRET_HEIGHT);
        batch.draw(
                Assets.turretCannon,
                position.getX(),
                position.getY(),
                TURRET_WIDTH / 2,
                TURRET_HEIGHT / 2,
                TURRET_WIDTH,
                TURRET_HEIGHT,
                1,
                1,
                cannonRotation);
    }

    public Bullet shoot(Enemy e) {
        coolDown = TURRET_COOL_DOWN;
        return new Bullet(getCenter(), e);
    }

    public float getCoolDown() {
        return coolDown;
    }

    public Point getCenter() {
        return new Point(position.getX() + TURRET_WIDTH / 2, position.getY() + TURRET_WIDTH / 2);
    }

    public void setCannonRotation(float cannonRotation) {
        this.cannonRotation = cannonRotation;
    }
}
