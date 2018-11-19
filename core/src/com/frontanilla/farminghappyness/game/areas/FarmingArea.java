package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.components.ButtonTile;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_X;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_Y;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_TILE_SIZE;

public class FarmingArea {

    private DelayedRemovalArray<ButtonTile> farmingTiles;
    private Point center;

    public FarmingArea() {
        farmingTiles = new DelayedRemovalArray<>();
        for (int row = 0; row < FARMING_AREA_ROWS; row++) {
            // NinePatcherTile row
            for (int column = 0; column < FARMING_AREA_COLUMNS; column++) {
                ButtonTile farmingNinePatcherTile = new ButtonTile(
                        Enums.TileType.FARMING_TILE,
                        Assets.farmlandTile,
                        FARMING_AREA_X + column * FARMING_TILE_SIZE,
                        FARMING_AREA_Y + row * FARMING_TILE_SIZE,
                        FARMING_TILE_SIZE,
                        FARMING_TILE_SIZE);
                farmingTiles.add(farmingNinePatcherTile);
            }
        }
        center = new Point(FARMING_AREA_X + FARMING_AREA_SIZE / 2, FARMING_AREA_Y + FARMING_AREA_SIZE / 2);
    }

    public void render(SpriteBatch batch) {
        for (ButtonTile ninePatcherTile : farmingTiles) {
            ninePatcherTile.render(batch);
            if (ninePatcherTile.getGameEntity() != null) {
                ninePatcherTile.getGameEntity().render(batch);
            }
        }
    }

    public Point getCenter() {
        return center;
    }

    public DelayedRemovalArray<ButtonTile> getTiles() {
        return farmingTiles;
    }
}
