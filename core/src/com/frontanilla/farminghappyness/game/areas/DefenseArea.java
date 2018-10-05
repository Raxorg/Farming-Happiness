package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Constants;

import static com.frontanilla.farminghappyness.utils.Enums.TileType.DEFENSIVE_TILE;
import static com.frontanilla.farminghappyness.utils.Enums.TileType.HIDDEN_TILE;

public class DefenseArea {

    private Tile[][] tileMatrix;

    public DefenseArea() {
        int columns = (int) (Constants.DEFENSE_AREA_WIDTH / Constants.TILE_SIZE);
        float xSpace = Constants.DEFENSE_AREA_WIDTH - columns * Constants.TILE_SIZE;
        int rows = (int) (Constants.DEFENSE_AREA_HEIGHT / Constants.TILE_SIZE);
        tileMatrix = new Tile[rows][columns];
        for (int row = 0; row < rows; row++) {
            // Tile row
            tileMatrix[row] = new Tile[columns];
            for (int column = 0; column < columns; column++) {
                if (column <= 3 || row <= 3) {
                    tileMatrix[row][column] = new Tile(
                            DEFENSIVE_TILE,
                            Constants.WORLD_WIDTH - Constants.DEFENSE_AREA_WIDTH + column * Constants.TILE_SIZE + xSpace,
                            Constants.WORLD_HEIGHT - Constants.DEFENSE_AREA_HEIGHT + Constants.TILE_SIZE * row);
                } else {
                    tileMatrix[row][column] = new Tile(
                            HIDDEN_TILE,
                            Constants.WORLD_WIDTH - Constants.DEFENSE_AREA_WIDTH + column * Constants.TILE_SIZE + xSpace,
                            Constants.WORLD_HEIGHT - Constants.DEFENSE_AREA_HEIGHT + Constants.TILE_SIZE * row);
                }
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

    public Tile[][] getTiles() {
        return tileMatrix;
    }
}
