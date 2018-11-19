package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.BACKGROUND_GRASS_TILE_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.BACKGROUND_GRASS_TILE_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.BACKGROUND_GRASS_TILE_SIZE;

public class Background {

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        renderGrass(batch);
    }

    private void renderGrass(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        for (int row = 0; row < BACKGROUND_GRASS_TILE_ROWS; row++) {
            for (int column = 0; column < BACKGROUND_GRASS_TILE_COLUMNS; column++) {
                batch.draw(
                        Assets.grassTile,
                        column * BACKGROUND_GRASS_TILE_SIZE,
                        row * BACKGROUND_GRASS_TILE_SIZE,
                        BACKGROUND_GRASS_TILE_SIZE,
                        BACKGROUND_GRASS_TILE_SIZE);
            }
        }
    }
}
