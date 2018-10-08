package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.Enums;

import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_WIDTH;

public class FarmingArea {

    private Tile[][] tileMatrix;

    public FarmingArea() {
        float xSpace = FARMING_AREA_WIDTH - FARMING_AREA_COLUMNS * Constants.TILE_SIZE;
        int rows = (int) (Constants.DEFENSE_AREA_HEIGHT / Constants.TILE_SIZE);
        tileMatrix = new Tile[rows][FARMING_AREA_COLUMNS];
        for (int row = 0; row < rows; row++) {
            // Tile row
            tileMatrix[row] = new Tile[FARMING_AREA_COLUMNS];
            for (int column = 0; column < FARMING_AREA_COLUMNS; column++) {
                tileMatrix[row][column] = new Tile(
                        Enums.TileType.FARMING_TILE,
                        Constants.WORLD_WIDTH - FARMING_AREA_WIDTH + column * Constants.TILE_SIZE + xSpace,
                        Constants.WORLD_HEIGHT - FARMING_AREA_HEIGHT + Constants.TILE_SIZE * row);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Tile[] tileRow : tileMatrix) {
            for (Tile tile : tileRow) {
                tile.render(batch);
            }
        }
    }

    public float getXCenter() {
        return 1;
    }

    public float getYCenter() {
        return 1;
    }
}
