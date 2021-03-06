package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.NinePatcherTile;
import com.frontanilla.farminghappyness.game.entities.units.Enemy;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_CANNON_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_COOL_DOWN;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_INITIAL_HEALTH;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_RANGE;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_WIDTH;

public class Turret extends Defense {

    private float coolDown;

    public Turret(NinePatcherTile ninePatcherTile) {
        super(
                new Rectangle(
                        ninePatcherTile.getX() + (DEFENSE_TILE_SIZE - TURRET_WIDTH) / 2,
                        ninePatcherTile.getY() + (DEFENSE_TILE_SIZE - TURRET_WIDTH) / 2,
                        TURRET_WIDTH,
                        TURRET_HEIGHT),
                TURRET_INITIAL_HEALTH);
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
                bounds.x,
                bounds.y);
        lifeBar.render(batch);
    }

    public void renderRangeCircle(SpriteBatch batch) {
        batch.draw(
                Assets.rangeCircle,
                bounds.x - TURRET_RANGE + TURRET_WIDTH / 2,
                bounds.y - TURRET_RANGE + TURRET_WIDTH / 2,
                TURRET_RANGE * 2,
                TURRET_RANGE * 2);
    }

    public Bullet shoot(Enemy e) {
        coolDown = TURRET_COOL_DOWN;
        Point bulletSpawnPoint = new Point(
                center.getX(),
                bounds.y + TURRET_CANNON_HEIGHT);
        return new Bullet(bulletSpawnPoint, e);
    }

    public float getCoolDown() {
        return coolDown;
    }
}
