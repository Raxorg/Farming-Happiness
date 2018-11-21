package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.entities.units.Enemy;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.game.other.Foliage;

public class GameRenderer {

    private GameConnector connector;
    private SpriteBatch dynamicBatch, staticBatch;
    private Foliage foliage;

    public GameRenderer(GameConnector connector) {
        this.connector = connector;
        dynamicBatch = new SpriteBatch();
        staticBatch = new SpriteBatch();
        foliage = new Foliage();
    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Begin batch
        dynamicBatch.begin();
        // Render grass
        foliage.renderGrass(dynamicBatch);
        // Render Areas
        connector.getGameState().getRiverArea().render(dynamicBatch);
        connector.getGameState().getFarmingArea().render(dynamicBatch);
        connector.getGameState().getDefenseArea().render(dynamicBatch);
        // Render bullets
        for (Bullet b : connector.getGameState().getBullets()) {
            b.render(dynamicBatch);
        }
        // Render trunks
        foliage.renderTrunks(dynamicBatch);
        // Render enemies
        for (Enemy e : connector.getGameState().getEnemies()) {
            e.render(dynamicBatch);
        }
        // Render leaves
        foliage.renderLeaves(dynamicBatch);
        // End batch
        dynamicBatch.end();
        // Render static display area
        staticBatch.begin();
        connector.getGameState().getDisplayArea().render(staticBatch);
        staticBatch.draw(new Texture("images/clouds.png"), 100, 100,150,25);
        staticBatch.end();
    }

    public SpriteBatch getDynamicBatch() {
        return dynamicBatch;
    }
}
