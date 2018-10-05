package com.frontanilla.farminghappyness.game.structures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Constants;

public class Turret {

    private float x, y;

    public Turret(Tile tile) {
        this.x = tile.getX() + (Constants.TILE_SIZE - Constants.TURRET_SIZE) / 2;
        this.y = tile.getY() + (Constants.TILE_SIZE - Constants.TURRET_SIZE) / 2;
    }

    public void render(SpriteBatch batch) {
        batch.draw(
                Assets.turret,
                x,
                y,
                Constants.TURRET_SIZE,
                Constants.TURRET_SIZE);
    }
}
