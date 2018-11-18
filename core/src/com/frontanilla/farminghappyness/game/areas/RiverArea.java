package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILES;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;

public class RiverArea {

    private float time;

    public RiverArea() {
        time = 0;
    }

    public void update(float delta) {
        // TODO make a fish appear every now and then
        time += delta;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        for (int i = 0; i < RIVER_TILES; i++) {
            batch.draw(
                    Assets.riverAnimation.getKeyFrame(time),
                    RIVER_TILE_SIZE * i,
                    WORLD_HEIGHT - RIVER_TILE_SIZE,
                    RIVER_TILE_SIZE,
                    RIVER_TILE_SIZE);
        }
    }
}
