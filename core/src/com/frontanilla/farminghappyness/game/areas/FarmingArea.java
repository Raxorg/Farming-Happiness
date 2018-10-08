package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_X;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_Y;
import static com.frontanilla.farminghappyness.utils.Constants.TILE_SIZE;

public class FarmingArea {

    private Tile[][] tileMatrix;
    private Point center;

    public FarmingArea() {
        tileMatrix = new Tile[FARMING_AREA_ROWS][FARMING_AREA_COLUMNS];
        for (int row = 0; row < FARMING_AREA_ROWS; row++) {
            // Tile row
            tileMatrix[row] = new Tile[FARMING_AREA_COLUMNS];
            for (int column = 0; column < FARMING_AREA_COLUMNS; column++) {
                tileMatrix[row][column] = new Tile(
                        Enums.TileType.FARMING_TILE,
                        FARMING_AREA_X + column * TILE_SIZE,
                        FARMING_AREA_Y + row * TILE_SIZE);
            }
        }
        center = new Point(FARMING_AREA_X + FARMING_AREA_WIDTH / 2, FARMING_AREA_Y + FARMING_AREA_HEIGHT / 2);
    }

    public void render(SpriteBatch batch) {
        for (Tile[] tileRow : tileMatrix) {
            for (Tile tile : tileRow) {
                tile.render(batch);
            }
        }
    }

    public Point getCenter() {
        return center;
    }
}
