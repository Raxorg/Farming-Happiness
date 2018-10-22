package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.game.structures.Turret;
import com.frontanilla.farminghappyness.game.units.Enemy;
import com.frontanilla.farminghappyness.game.units.Tourist;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.Util;

import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;
import static com.frontanilla.farminghappyness.utils.Enums.TileType.DEFENSIVE_TILE;

public class GameLogic {

    private GameScreen gameScreen;
    private DelayedRemovalArray<Enemy> enemies;
    private DelayedRemovalArray<Turret> turrets;
    private DelayedRemovalArray<Bullet> bullets;
    private float time;

    public GameLogic(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        enemies = new DelayedRemovalArray<>();
        turrets = new DelayedRemovalArray<>();
        bullets = new DelayedRemovalArray<>();
        time = 0;
    }

    public void update(float delta) {
        gameScreen.getCamera().handleInput();
        gameScreen.getCamera().update();
        gameScreen.getBatch().setProjectionMatrix(gameScreen.getCamera().combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateEnemies(delta);
        updateTurrets(delta);
        updateBullets(delta);

        time += delta;
        if (time >= Constants.SPAWN_RATE) {
            spawnEnemy();
            time -= Constants.SPAWN_RATE;
        }
    }

    private void updateEnemies(float delta) {
        enemies.begin();
        for (Enemy e : enemies) {
            e.move(delta);
            if (!e.isAlive()) {
                enemies.removeValue(e, true);
            }
        }
        enemies.end();
    }

    private void updateTurrets(float delta) {
        for (Tile[] tileRow : gameScreen.getMap().getDefenseTiles()) {
            for (Tile tile : tileRow) {
                if (tile.getContent() != null && tile.getContent() instanceof Turret) {
                    tile.getContent().update(delta);
                    for (Enemy e : enemies) {
                        if (Util.getDistance(e.getCenter(), tile.getContent().getCenter()) < Constants.TURRET_RANGE) {
                            Turret turret = (Turret) tile.getContent();
                            float angle = Util.getAngle(tile.getContent().getCenter(), e.getCenter());
                            turret.setCannonRotation(angle);
                            if (turret.getCoolDown() == 0) {
                                bullets.add(turret.shoot(e));
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private void updateBullets(float delta) {
        bullets.begin();
        for (Bullet b : bullets) {
            b.update(delta);
            if (b.hasExploded()) {
                bullets.removeValue(b, true);
            }
        }
        bullets.end();
    }

    private void spawnEnemy() {
        boolean randSide = MathUtils.randomBoolean();
        Tourist t;
        if (randSide) { // Left
            float randY = MathUtils.random(0, WORLD_HEIGHT - ENEMY_HEIGHT - RIVER_TILE_SIZE);
            t = new Tourist(-ENEMY_WIDTH, randY);
        } else { // Bottom
            float randX = MathUtils.random(0, WORLD_WIDTH - ENEMY_WIDTH);
            t = new Tourist(randX, -ENEMY_HEIGHT);
        }
        float angle = Util.getAngle(t.getCenter(), gameScreen.getMap().getFarmingArea().getCenter());
        t.setAngle(angle);
        enemies.add(t);
    }

    public void touchDown(Vector3 usefulVector) {
        for (Tile[] tileRow : gameScreen.getMap().getDefenseTiles()) {
            for (Tile tile : tileRow) {
                if (tile.contains(usefulVector.x, usefulVector.y) && tile.getType() == DEFENSIVE_TILE) {
                    // TODO place stuff according to player's selection
                    Turret newTurret = new Turret(tile);
                    turrets.add(newTurret);
                    tile.setContent(newTurret);
                    return;
                }
            }
        }
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }

    public DelayedRemovalArray<Bullet> getBullets() {
        return bullets;
    }

    public DelayedRemovalArray<Turret> getTurrets() {
        return turrets;
    }
}