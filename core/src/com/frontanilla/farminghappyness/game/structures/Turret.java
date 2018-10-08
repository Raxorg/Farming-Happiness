package com.frontanilla.farminghappyness.game.structures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Constants;

import static com.frontanilla.farminghappyness.utils.Constants.TURRET_SIZE;

public class Turret {

    private float x, y;

    public Turret(Tile tile) {
        this.x = tile.getX() + (Constants.TILE_SIZE - TURRET_SIZE) / 2;
        this.y = tile.getY() + (Constants.TILE_SIZE - TURRET_SIZE) / 2;
    }

    public void render(SpriteBatch batch) {
        batch.draw(
                Assets.turret,
                x,
                y,
                TURRET_SIZE,
                TURRET_SIZE);
        batch.draw(
                Assets.turretCannon,
                x,
                y,
                TURRET_SIZE,
                TURRET_SIZE);
    }

    public float getXCenter() {
        return x + TURRET_SIZE;
    }

    public float getYCenter() {
        return y + TURRET_SIZE;
    }
}
