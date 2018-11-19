package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.NinePatcherTile;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.WALL_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_INITIAL_HEALTH;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_X_OFFSET;

public class Wall extends Defense {

    public Wall(NinePatcherTile ninePatcherTile) {
        super(new Rectangle(ninePatcherTile.getX(), ninePatcherTile.getY(), WALL_WIDTH, WALL_HEIGHT), WALL_INITIAL_HEALTH);
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                Assets.wall,
                bounds.x - WALL_X_OFFSET,
                bounds.y);
    }

    @Override
    public void takeDamage(int damage) {

    }
}
