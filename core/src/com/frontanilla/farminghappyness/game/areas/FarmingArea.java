package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.components.ButtonTile;
import com.frontanilla.farminghappyness.game.other.FarmBase;
import com.frontanilla.farminghappyness.game.other.Laboratory;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_X;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_X_OFFSET;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_Y;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.FARM_BASE_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.FARM_BASE_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.FARM_BASE_X;
import static com.frontanilla.farminghappyness.utils.Constants.FARM_BASE_Y;
import static com.frontanilla.farminghappyness.utils.Constants.LABORATORY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.LABORATORY_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.LABORATORY_X;
import static com.frontanilla.farminghappyness.utils.Constants.LABORATORY_Y;

public class FarmingArea {

    private FarmBase farmBase;
    private Laboratory laboratory;
    private DelayedRemovalArray<ButtonTile> farmingTiles;
    private Point center;

    public FarmingArea() {
        // Farm base
        farmBase = new FarmBase(
                Assets.farmBase,
                FARM_BASE_X,
                FARM_BASE_Y,
                FARM_BASE_WIDTH,
                FARM_BASE_HEIGHT);
        // Laboratory
        laboratory = new Laboratory(
                Assets.laboratory,
                LABORATORY_X,
                LABORATORY_Y,
                LABORATORY_WIDTH,
                LABORATORY_HEIGHT);
        // Farming tiles
        farmingTiles = new DelayedRemovalArray<>();
        for (int row = 0; row < FARMING_AREA_ROWS; row++) {
            // NinePatcherTile row
            for (int column = 0; column < FARMING_AREA_COLUMNS; column++) {
                ButtonTile farmingNinePatcherTile = new ButtonTile(
                        Enums.TileType.FARMING_TILE,
                        Assets.farmlandTile,
                        FARMING_AREA_X + FARMING_AREA_X_OFFSET + column * FARMING_TILE_SIZE,
                        FARMING_AREA_Y + row * FARMING_TILE_SIZE,
                        FARMING_TILE_SIZE,
                        FARMING_TILE_SIZE);
                farmingTiles.add(farmingNinePatcherTile);
            }
        }
        center = new Point(FARMING_AREA_X + FARMING_AREA_SIZE / 2, FARMING_AREA_Y + FARMING_AREA_SIZE / 2);
    }

    public void render(SpriteBatch batch) {
        // Farm base
        farmBase.render(batch);
        for (int i = 0; i < 5; i++) {
            batch.draw(Assets.pathTile, FARM_BASE_X + 81, FARM_BASE_Y - 20 - i * 20);
        }
        // Laboratory
        laboratory.render(batch);
        for (int i = 0; i < 5; i++) {
            batch.draw(Assets.pathTile, LABORATORY_X + 83, LABORATORY_Y - 20 - i * 20);
        }
        // Farming tiles
        for (ButtonTile ninePatcherTile : farmingTiles) {
            ninePatcherTile.render(batch);
            if (ninePatcherTile.getGameEntity() != null) {
                ninePatcherTile.getGameEntity().render(batch);
            }
        }
    }

    public FarmBase getFarmBase() {
        return farmBase;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public DelayedRemovalArray<ButtonTile> getTiles() {
        return farmingTiles;
    }

    public Point getCenter() {
        return center;
    }
}
