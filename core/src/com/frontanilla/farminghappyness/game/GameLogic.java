package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.game.structures.Turret;
import com.frontanilla.farminghappyness.game.units.Enemy;
import com.frontanilla.farminghappyness.game.units.Tourist;

import static com.frontanilla.farminghappyness.utils.Enums.TileType.DEFENSIVE_TILE;

public class GameLogic {

    private GameScreen gameScreen;
    private DelayedRemovalArray<Enemy> enemies;
    private float time;

    public GameLogic(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        enemies = new DelayedRemovalArray<>();
        time = 0;
    }

    public void update(float delta) {
        gameScreen.getCamera().handleInput();
        gameScreen.getCamera().update();
        gameScreen.getBatch().setProjectionMatrix(gameScreen.getCamera().combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        time += delta;
        if (time >= 2) {
            spawnEnemy();
            time -= 2;
        }
    }

    private void spawnEnemy() {
        Tourist t = new Tourist(100, 100);
        enemies.add(t);
    }

    public void touchDown(Vector3 usefulVector) {
        for (Tile[] tileRow : gameScreen.getMap().getDefenseTiles()) {
            for (Tile tile : tileRow) {
                if (tile.contains(usefulVector.x, usefulVector.y) && tile.getType() == DEFENSIVE_TILE) {
                    tile.setContent(new Turret(tile));
                    return;
                }
            }
        }
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }
}
