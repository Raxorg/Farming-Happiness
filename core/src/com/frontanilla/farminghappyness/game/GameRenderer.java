package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.components.Background;
import com.frontanilla.farminghappyness.game.areas.DefenseArea;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILES;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;

public class GameRenderer {

    private GameConnector connector;
    private Background background;
    private DefenseArea defenseArea;
    private float time; // TODO move

    public GameRenderer(GameConnector connector) {
        this.connector = connector;
        background = new Background();
        defenseArea = new DefenseArea();
        // TODO randomize river tiles and save them
        time = 0;// TODO move
    }

    public void render(SpriteBatch batch) {
        background.update(Gdx.graphics.getDeltaTime());
        background.render(batch);
        renderRiver(batch);

        connector.getGameState().getFarmingArea().render(batch);
        defenseArea.render(batch);
        time += Gdx.graphics.getDeltaTime(); // TODO move
    }

    private void renderRiver(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        for (int i = 0; i < RIVER_TILES; i++) {
            batch.draw(
                    Assets.riverAnimation.getKeyFrame(time),
                    RIVER_TILE_SIZE * i,
                    WORLD_HEIGHT - RIVER_TILE_SIZE,
                    RIVER_TILE_SIZE,
                    RIVER_TILE_SIZE);
        }
    }

    public DefenseArea getDefenseArea() {
        return defenseArea;
    }
}
