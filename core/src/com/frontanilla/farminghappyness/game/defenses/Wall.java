package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.NinePatcherTile;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SPACING;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_INITIAL_HEALTH;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_WIDTH;

public class Wall extends Defense {

    public Wall(NinePatcherTile ninePatcherTile) {
        super(new Rectangle(ninePatcherTile.getX(), ninePatcherTile.getY(), WALL_WIDTH, WALL_HEIGHT), WALL_INITIAL_HEALTH);
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(
                Assets.wall,
                bounds.x - TILE_SPACING,
                bounds.y,
                bounds.width + TILE_SPACING * 2,
                bounds.height);
    }

    @Override
    public void takeDamage(int damage) {

    }
}
