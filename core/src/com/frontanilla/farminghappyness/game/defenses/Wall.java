package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SPACING;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_INITIAL_HEALTH;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_SIZE;

public class Wall extends Defense {

    public Wall(Tile tile) {
        super(new Rectangle(
                tile.getX(),
                tile.getY(),
                WALL_SIZE,
                WALL_SIZE
        ), WALL_INITIAL_HEALTH);

        position = new Point(bounds.x, bounds.y);
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(
                Assets.wall,
                position.getX() - TILE_SPACING,
                position.getY(),
                WALL_SIZE + TILE_SPACING * 2,
                WALL_SIZE);
    }
}
