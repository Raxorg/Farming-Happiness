package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.components.NinePatcherTile;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.BOTTOM_DEFENSE_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.BOTTOM_DEFENSE_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.BOTTOM_DEFENSE_X;
import static com.frontanilla.farminghappyness.utils.Constants.BOTTOM_DEFENSE_Y;
import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_X_SPACING;
import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_Y_SPACING;
import static com.frontanilla.farminghappyness.utils.Constants.LEFT_DEFENSE_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.LEFT_DEFENSE_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.LEFT_DEFENSE_X;
import static com.frontanilla.farminghappyness.utils.Constants.LEFT_DEFENSE_Y;
import static com.frontanilla.farminghappyness.utils.Constants.NPTEST_BORDER_PIXELS;
import static com.frontanilla.farminghappyness.utils.Enums.TileType.DEFENSIVE_TILE;

public class DefenseArea {

    private DelayedRemovalArray<NinePatcherTile> defenseTiles;

    public DefenseArea() {
        defenseTiles = new DelayedRemovalArray<>();
        // Add left tiles
        for (int row = LEFT_DEFENSE_ROWS - 1; row >= 0; row--) {
            for (int column = 0; column < LEFT_DEFENSE_COLUMNS; column++) {
                defenseTiles.add(new NinePatcherTile(
                        DEFENSIVE_TILE,
                        Assets.nptest,
                        DEFENSE_TILE_SIZE / 10f,
                        NPTEST_BORDER_PIXELS,
                        LEFT_DEFENSE_X + DEFENSE_TILE_SIZE * column + DEFENSE_X_SPACING * column,
                        LEFT_DEFENSE_Y + DEFENSE_TILE_SIZE * row + DEFENSE_Y_SPACING * row,
                        DEFENSE_TILE_SIZE,
                        DEFENSE_TILE_SIZE));
            }
        }
        // Add bottom tiles
        for (int row = 0; row < BOTTOM_DEFENSE_ROWS; row++) {
            for (int column = 0; column < BOTTOM_DEFENSE_COLUMNS; column++) {
                defenseTiles.add(new NinePatcherTile(
                        DEFENSIVE_TILE,
                        Assets.nptest,
                        DEFENSE_TILE_SIZE / 10f,
                        NPTEST_BORDER_PIXELS,
                        BOTTOM_DEFENSE_X + DEFENSE_TILE_SIZE * column + DEFENSE_X_SPACING * column,
                        BOTTOM_DEFENSE_Y + DEFENSE_TILE_SIZE * row + DEFENSE_X_SPACING * row,
                        DEFENSE_TILE_SIZE,
                        DEFENSE_TILE_SIZE));
            }
        }
        // Add extra tiles
        defenseTiles.add(new NinePatcherTile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE - DEFENSE_Y_SPACING,
                DEFENSE_TILE_SIZE,
                DEFENSE_TILE_SIZE));
        defenseTiles.add(new NinePatcherTile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X + DEFENSE_TILE_SIZE + DEFENSE_X_SPACING,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE - DEFENSE_Y_SPACING,
                DEFENSE_TILE_SIZE,
                DEFENSE_TILE_SIZE));
        defenseTiles.add(new NinePatcherTile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X + DEFENSE_TILE_SIZE * 2 + DEFENSE_X_SPACING * 2,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE - DEFENSE_Y_SPACING,
                DEFENSE_TILE_SIZE,
                DEFENSE_TILE_SIZE));
        defenseTiles.add(new NinePatcherTile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X + DEFENSE_TILE_SIZE + DEFENSE_X_SPACING,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE * 2 - DEFENSE_Y_SPACING * 2,
                DEFENSE_TILE_SIZE,
                DEFENSE_TILE_SIZE));
        defenseTiles.add(new NinePatcherTile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X + DEFENSE_TILE_SIZE * 2 + DEFENSE_X_SPACING * 2,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE * 2 - DEFENSE_Y_SPACING * 2,
                DEFENSE_TILE_SIZE,
                DEFENSE_TILE_SIZE));
        defenseTiles.add(new NinePatcherTile(
                DEFENSIVE_TILE,
                Assets.nptest,
                DEFENSE_TILE_SIZE / 10f,
                NPTEST_BORDER_PIXELS,
                LEFT_DEFENSE_X + DEFENSE_TILE_SIZE * 2 + DEFENSE_X_SPACING * 2,
                LEFT_DEFENSE_Y - DEFENSE_TILE_SIZE * 3 - DEFENSE_Y_SPACING * 3,
                DEFENSE_TILE_SIZE,
                DEFENSE_TILE_SIZE));
    }

    public void render(SpriteBatch batch) {
        for (NinePatcherTile defenseNinePatcherTile : defenseTiles) {
            if (defenseNinePatcherTile.getGameEntity() == null) {
                defenseNinePatcherTile.render(batch); // TODO draw the tile even when something is on it
            }
        }
    }

    public DelayedRemovalArray<NinePatcherTile> getTiles() {
        return defenseTiles;
    }
}
