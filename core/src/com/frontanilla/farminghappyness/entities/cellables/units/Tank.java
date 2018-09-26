package com.frontanilla.farminghappyness.entities.cellables.units;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.entities.bullets.TankBullet;
import com.frontanilla.farminghappyness.grid.Cell;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.Util;

import static com.frontanilla.farminghappyness.utils.Constants.CELL_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.TANK_BULLET_INITIAL_HEALTH;
import static com.frontanilla.farminghappyness.utils.Constants.TANK_REPRESENTATION;
import static com.frontanilla.farminghappyness.utils.Enums.BulletType.TANK_BULLET;

public class Tank extends Unit {

    public Tank(Player player, Cell cell, int health, float rotation) {
        super(player, cell, health, CELL_SIZE, CELL_SIZE, rotation, TANK_REPRESENTATION);
    }

    public TankBullet fire() {
        Vector2 rotationVector = Util.getVector2FromRotation(rotation);
        Vector2 bulletPosition = new Vector2(bounds.getX(), bounds.getY());
        bulletPosition.add(CELL_SIZE / 2, CELL_SIZE / 2);
        bulletPosition.add(-Constants.TANK_BULLET_WIDTH / 2, -Constants.TANK_BULLET_HEIGHT / 2);
        bulletPosition.mulAdd(rotationVector, CELL_SIZE / 2);
        return new TankBullet(
                owner,
                Util.PolygonFromBullet(TANK_BULLET, bulletPosition, rotation),
                TANK_BULLET_INITIAL_HEALTH,
                rotation
        );
    }

    @Override
    public TextureRegion textureOfHealth() {
        switch (health) {
            case 1:
                return Assets.instance.gameAssets.tank;
            default:
                return null;
        }
    }

    @Override
    public void update(float delta) {
        // Nothing happens by default
        // TODO maybe glows?
    }
}