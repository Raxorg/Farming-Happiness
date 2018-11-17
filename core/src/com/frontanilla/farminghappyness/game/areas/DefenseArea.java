package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.BOTTOM_DEFENSE_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.BOTTOM_DEFENSE_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.BOTTOM_DEFENSE_X;
import static com.frontanilla.farminghappyness.utils.Constants.BOTTOM_DEFENSE_Y;
import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.LEFT_DEFENSE_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.LEFT_DEFENSE_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.LEFT_DEFENSE_X;
import static com.frontanilla.farminghappyness.utils.Constants.LEFT_DEFENSE_Y;
import static com.frontanilla.farminghappyness.utils.Constants.NPTEST_BORDER_PIXELS;
import static com.frontanilla.farminghappyness.utils.Enums.TileType.DEFENSIVE_TILE;

public class DefenseArea {

    private DelayedRemovalArray<Tile> defenseTiles;

    public DefenseArea() {
        defenseTiles = new DelayedRemovalArray<>();
        // Add left tiles
        for (int row = 0; row < LEFT_DEFENSE_ROWS; row++) {
            for (int column = 0; column < LEFT_DEFENSE_COLUMNS; column++) {
                defenseTiles.add(new Tile(
                        DEFENSIVE_TILE,
                        Assets.nptest,
                        DEFENSE_TILE_SIZE / 10f,
                        NPTEST_BORDER_PIXELS,
                        LEFT_DEFENSE_X + DEFENSE_TILE_SIZE * column,
                        LEFT_DEFENSE_Y + DEFENSE_TILE_SIZE * row));
            }
        }
        // Add bottom tiles
        for (int row = 0; row < BOTTOM_DEFENSE_ROWS; row++) {
            for (int column = 0; column < BOTTOM_DEFENSE_COLUMNS; column++) {
                defenseTiles.add(new Tile(
                        DEFENSIVE_TILE,
                        Assets.nptest,
                        DEFENSE_TILE_SIZE / 10f,
                        NPTEST_BORDER_PIXELS,
                        BOTTOM_DEFENSE_X + DEFENSE_TILE_SIZE * column,
                        BOTTOM_DEFENSE_Y + DEFENSE_TILE_SIZE * row));
            }
        }
        // Add extra tiles
        defenseTiles.add(new Tile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE));
        defenseTiles.add(new Tile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X + DEFENSE_TILE_SIZE,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE));
        defenseTiles.add(new Tile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X + DEFENSE_TILE_SIZE * 2,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE));
        defenseTiles.add(new Tile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X + DEFENSE_TILE_SIZE,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE * 2));
        defenseTiles.add(new Tile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X + DEFENSE_TILE_SIZE * 2,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE * 2));
        defenseTiles.add(new Tile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X + DEFENSE_TILE_SIZE * 2,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE * 3));
    }

    public void render(SpriteBatch batch) {
        for (Tile defenseTile : defenseTiles) {
            if (defenseTile.getDefense() == null) {
                defenseTile.render(batch);
            }
        }
    }

    public DelayedRemovalArray<Tile> getTiles() {
        return defenseTiles;
    }
}
