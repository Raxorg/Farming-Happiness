package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.components.Background;
import com.frontanilla.farminghappyness.game.areas.DefenseArea;
import com.frontanilla.farminghappyness.game.areas.FarmingArea;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.NinePatcher;

import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILES;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;

public class GameRenderer {

    private Background background;
    private FarmingArea farmingArea;
    private DefenseArea defenseArea;
    private NinePatcher ninePatcher, ninePatcher2; // TODO TEST

    public GameRenderer() {
        background = new Background();
        farmingArea = new FarmingArea();
        defenseArea = new DefenseArea();
        // TODO randomize river tiles and save them
        ninePatcher = new NinePatcher(Assets.nptest, 10); // TODO TEST
        ninePatcher.setWidth(200f);
        ninePatcher.setHeight(100f);
        ninePatcher.setMargin(10f);
        ninePatcher.setPosition(500, 100);
        ninePatcher2 = new NinePatcher(Assets.nptest, 15); // TODO TEST
        ninePatcher.setWidth(120f);
        ninePatcher.setHeight(120f);
        ninePatcher.setPosition(0, 0);
    }

    public void render(SpriteBatch batch) {
        //background.update(Gdx.graphics.getDeltaTime());
        //background.render(batch);
        //renderRiver(batch);

        //farmingArea.render(batch);
        //defenseArea.render(batch);
        ninePatcher.render(batch);
        ninePatcher2.render(batch);
    }

    private void renderRiver(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        for (int i = 0; i < RIVER_TILES; i++) {
            batch.draw(
                    Assets.river1,
                    RIVER_TILE_SIZE * i,
                    WORLD_HEIGHT - RIVER_TILE_SIZE,
                    RIVER_TILE_SIZE,
                    RIVER_TILE_SIZE);
        }
    }

    public Tile[][] getDefenseTiles() {
        return defenseArea.getTiles();
    }

    public FarmingArea getFarmingArea() {
        return farmingArea;
    }

    public DefenseArea getDefenseArea() {
        return defenseArea;
    }
}
