package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.entities.units.Enemy;
import com.frontanilla.farminghappyness.game.other.Bullet;

public class GameRenderer {

    private GameConnector connector;
    private SpriteBatch dynamicBatch, staticBatch;

    public GameRenderer(GameConnector connector) {
        this.connector = connector;
        dynamicBatch = new SpriteBatch();
        staticBatch = new SpriteBatch();
    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Begin batch
        dynamicBatch.begin();
        // Render grass
        connector.getGameState().getAmbientArea().renderGrass(dynamicBatch);
        // Render Areas
        connector.getGameState().getRiverArea().render(dynamicBatch);
        connector.getGameState().getFarmingArea().render(dynamicBatch);
        connector.getGameState().getDefenseArea().render(dynamicBatch);
        // Render bullets
        for (Bullet b : connector.getGameState().getBullets()) {
            b.render(dynamicBatch);
        }
        // Render trunks
        connector.getGameState().getAmbientArea().renderTrunks(dynamicBatch);
        // Render enemies
        for (Enemy e : connector.getGameState().getEnemies()) {
            e.render(dynamicBatch);
        }
        // Render leaves
        connector.getGameState().getAmbientArea().renderLeaves(dynamicBatch);
        // Render clouds
        connector.getGameState().getAmbientArea().renderClouds(dynamicBatch);
        // End batch
        dynamicBatch.end();
        // Render static display area
        staticBatch.begin();
        connector.getGameState().getDisplayArea().render(staticBatch);
        staticBatch.end();
    }

    public SpriteBatch getDynamicBatch() {
        return dynamicBatch;
    }
}
