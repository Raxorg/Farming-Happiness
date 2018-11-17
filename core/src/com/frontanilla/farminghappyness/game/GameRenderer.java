package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.components.Background;
import com.frontanilla.farminghappyness.game.areas.DefenseArea;
import com.frontanilla.farminghappyness.game.areas.FarmingArea;
import com.frontanilla.farminghappyness.game.plants.Plant;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILES;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;

public class GameRenderer {

    private Background background;
    private FarmingArea farmingArea;
    private DefenseArea defenseArea;
    private Plant plant;

    public GameRenderer() {
        background = new Background();
        farmingArea = new FarmingArea();
        defenseArea = new DefenseArea();
        // TODO randomize river tiles and save them

        plant = new Plant(Plant.AYARN, farmingArea.getTiles().get(4));// TODO TEST WITH USER INPUT
    }

    public void render(SpriteBatch batch) {
        background.update(Gdx.graphics.getDeltaTime());
        background.render(batch);
        renderRiver(batch);

        farmingArea.render(batch);
        defenseArea.render(batch);

        plant.render(batch);// TODO TEST
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

    public FarmingArea getFarmingArea() {
        return farmingArea;
    }

    public DefenseArea getDefenseArea() {
        return defenseArea;
    }
}
