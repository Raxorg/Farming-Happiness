package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.areas.DefenseArea;
import com.frontanilla.farminghappyness.game.areas.FarmingArea;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.GRASS_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.GRASS_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.GRASS_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.GRASS_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILES;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;

public class GameMap {

    private FarmingArea farmingArea;
    private DefenseArea defenseArea;

    public GameMap() {
        farmingArea = new FarmingArea();
        defenseArea = new DefenseArea();
        // TODO randomize river tiles and save them
    }

    public void render(SpriteBatch batch) {
        renderGround(batch);
        renderRiver(batch);

        farmingArea.render(batch);
        defenseArea.render(batch);
    }

    private void renderRiver(SpriteBatch batch) {
        for (int i = 0; i < RIVER_TILES; i++) {
            batch.draw(
                    Assets.river1,
                    RIVER_TILE_SIZE * i,
                    WORLD_HEIGHT - RIVER_TILE_SIZE,
                    RIVER_TILE_SIZE,
                    RIVER_TILE_SIZE);
        }
    }

    private void renderGround(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        for (int column = 0; column < GRASS_COLUMNS; column++) {
            for (int row = 0; row < GRASS_ROWS; row++) {
                batch.draw(
                        Assets.grass,
                        column * GRASS_WIDTH,
                        row * GRASS_HEIGHT,
                        GRASS_WIDTH,
                        GRASS_HEIGHT);
            }
        }
    }

    public Tile[][] getDefenseTiles() {
        return defenseArea.getTiles();
    }

    public FarmingArea getFarmingArea() {
        return farmingArea;
    }

    public DefenseArea getDefenseArea() {
        return defenseArea;
    }
}
