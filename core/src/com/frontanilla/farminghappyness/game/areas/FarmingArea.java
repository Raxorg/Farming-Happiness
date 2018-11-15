package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_X;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_Y;
import static com.frontanilla.farminghappyness.utils.Constants.NPTEST_BORDER_PIXELS;
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
                TextureRegion textureRegion = Assets.empty;
                if (row > 0 && row < FARMING_AREA_ROWS - 1 && column > 0 && column < FARMING_AREA_COLUMNS - 1) {
                    textureRegion = Assets.farmingCenter;
                }
                // Bottom
                if (row == 0 && column > 0 && column < FARMING_AREA_COLUMNS - 1) {
                    textureRegion = Assets.farmingBottom;
                }
                // Top
                if (row == FARMING_AREA_ROWS - 1 && column > 0 && column < FARMING_AREA_COLUMNS - 1) {
                    textureRegion = Assets.farmingTop;
                }
                // Left
                if (column == 0 && row > 0 && row < FARMING_AREA_ROWS - 1) {
                    textureRegion = Assets.farmingLeft;
                }
                // Right
                if (column == FARMING_AREA_COLUMNS - 1 && row > 0 && row < FARMING_AREA_ROWS - 1) {
                    textureRegion = Assets.farmingRight;
                }
                // Bottom left corner
                if (row == 0 && column == 0) {
                    textureRegion = Assets.farmingCornerBottomLeft;
                }
                // Top left corner
                if (row == FARMING_AREA_ROWS - 1 && column == 0) {
                    textureRegion = Assets.farmingCornerTopLeft;
                }
                // Bottom Right corner
                if (row == 0 && column == FARMING_AREA_COLUMNS - 1) {
                    textureRegion = Assets.farmingCornerBottomRight;
                }
                // Top Right corner
                if (row == FARMING_AREA_ROWS - 1 && column == FARMING_AREA_COLUMNS - 1) {
                    textureRegion = Assets.farmingCornerTopRight;
                }
                tileMatrix[row][column] = new Tile(
                        Enums.TileType.FARMING_TILE,
                        Assets.nptest,
                        TILE_SIZE / 10f,
                        NPTEST_BORDER_PIXELS,
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

    public Tile getTile(int row, int column) {
        return tileMatrix[row][column];
    }

    public Point getCenter() {
        return center;
    }
}
