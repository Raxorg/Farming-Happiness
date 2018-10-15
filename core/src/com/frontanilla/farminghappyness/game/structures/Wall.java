package com.frontanilla.farminghappyness.game.structures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_SIZE;

public class Wall extends Content {

    public Wall(Tile tile) {
        float x = tile.getX() + (TILE_SIZE - WALL_SIZE) / 2;
        float y = tile.getY() + (TILE_SIZE - WALL_SIZE) / 2;
        position = new Point(x, y);
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(
                Assets.wall,
                position.getX(),
                position.getY(),
                TURRET_SIZE,
                TURRET_SIZE);
    }
}
