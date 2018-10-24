package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_AREA_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_AREA_DEFENSE_LINES;
import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_AREA_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_AREA_X;
import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_AREA_Y;
import static com.frontanilla.farminghappyness.utils.Constants.TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.TILE_SPACING;
import static com.frontanilla.farminghappyness.utils.Enums.TileType.DEFENSIVE_TILE;
import static com.frontanilla.farminghappyness.utils.Enums.TileType.HIDDEN_TILE;

public class DefenseArea {

    private Tile[][] tileMatrix;

    public DefenseArea() {
        tileMatrix = new Tile[DEFENSE_AREA_ROWS][DEFENSE_AREA_COLUMNS];
        for (int row = 0; row < DEFENSE_AREA_ROWS; row++) {
            // Tile row
            tileMatrix[row] = new Tile[DEFENSE_AREA_COLUMNS];
            for (int column = 0; column < DEFENSE_AREA_COLUMNS; column++) {
                if (column < DEFENSE_AREA_DEFENSE_LINES || row < DEFENSE_AREA_DEFENSE_LINES) {
                    tileMatrix[row][column] = new Tile(
                            DEFENSIVE_TILE,
                            Assets.defenseTile,
                            DEFENSE_AREA_X + column * (TILE_SIZE + TILE_SPACING),
                            DEFENSE_AREA_Y + row * (TILE_SIZE + TILE_SPACING));
                } else {
                    tileMatrix[row][column] = new Tile(
                            HIDDEN_TILE,
                            Assets.empty,
                            DEFENSE_AREA_X + column * (TILE_SIZE + TILE_SPACING),
                            DEFENSE_AREA_Y + row * (TILE_SIZE + TILE_SPACING));
                }
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Tile[] tileRow : tileMatrix) {
            for (Tile tile : tileRow) {
                if (tile.getDefense() == null) {
                    tile.render(batch);
                }
            }
        }
    }

    public Tile[][] getTiles() {
        return tileMatrix;
    }
}
