package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.Enums;

import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_WIDTH;

public class PlantsArea {

    private Tile[][] tileMatrix;

    public PlantsArea() {
        int columns = (int) (FARMING_AREA_WIDTH / Constants.TILE_SIZE);
        float xSpace = FARMING_AREA_WIDTH - columns * Constants.TILE_SIZE;
        int rows = (int) (Constants.DEFENSE_AREA_HEIGHT / Constants.TILE_SIZE);
        tileMatrix = new Tile[rows][columns];
        for (int row = 0; row < rows; row++) {
            // Tile row
            tileMatrix[row] = new Tile[columns];
            for (int column = 0; column < columns; column++) {
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
}
