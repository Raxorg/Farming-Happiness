package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.areas.DefenseArea;
import com.frontanilla.farminghappyness.game.areas.PlantsArea;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Constants;

public class GameMap {

    private PlantsArea plantsArea;
    private DefenseArea defenseArea;

    public GameMap() {
        plantsArea = new PlantsArea();
        defenseArea = new DefenseArea();
    }

    public void render(SpriteBatch batch) {
        renderGround(batch);
        plantsArea.render(batch);
        defenseArea.render(batch);
    }

    private void renderGround(SpriteBatch batch) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                batch.draw(
                        Assets.grass,
                        i * Constants.WORLD_WIDTH / 10,
                        j * Constants.WORLD_HEIGHT / 10,
                        Constants.WORLD_WIDTH / 10,
                        Constants.WORLD_HEIGHT / 10);
            }
        }
    }

    public Tile[][] getDefenseTiles() {
        return defenseArea.getTiles();
    }
}
