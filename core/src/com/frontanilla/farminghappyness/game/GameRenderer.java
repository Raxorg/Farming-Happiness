package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.components.Background;
import com.frontanilla.farminghappyness.components.Tile;
import com.frontanilla.farminghappyness.game.defenses.Turret;
import com.frontanilla.farminghappyness.game.entities.units.Enemy;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.TURRET_RANGE;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_WIDTH;

public class GameRenderer {

    private GameConnector connector;
    private SpriteBatch dynamicBatch, staticBatch;
    private Background background;

    public GameRenderer(GameConnector connector) {
        this.connector = connector;
        dynamicBatch = new SpriteBatch();
        staticBatch = new SpriteBatch();
        background = new Background();
    }

    public void render() {
        // Begin batch
        dynamicBatch.begin();
        // Render background
        background.render(dynamicBatch);
        // Render bullets
        for (Bullet b : connector.getGameState().getBullets()) {
            b.render(dynamicBatch);
        }
        // Render Turret ranges TODO improve?
        for (Tile tile : connector.getGameState().getDefenseArea().getTiles()) {
            if (tile.getGameEntity() instanceof Turret) {
                dynamicBatch.draw(
                        Assets.rangeCircle,
                        tile.getGameEntity().getBounds().x - TURRET_RANGE + TURRET_WIDTH / 2,
                        tile.getGameEntity().getBounds().y - TURRET_RANGE + TURRET_WIDTH / 2,
                        TURRET_RANGE * 2,
                        TURRET_RANGE * 2);
            }
        }
        // Render defenses TODO improve logic and placement of code?
        for (Tile tile : connector.getGameState().getDefenseArea().getTiles()) {
            if (tile.getGameEntity() != null) {
                tile.getGameEntity().render(dynamicBatch);
            }
        }
        // Render Areas
        connector.getGameState().getRiverArea().render(dynamicBatch);
        connector.getGameState().getFarmingArea().render(dynamicBatch);
        connector.getGameState().getDefenseArea().render(dynamicBatch);
        // Render enemies
        for (Enemy e : connector.getGameState().getEnemies()) {
            e.render(dynamicBatch);
        }
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
