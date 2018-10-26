package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.defenses.Trap;
import com.frontanilla.farminghappyness.game.defenses.Turret;
import com.frontanilla.farminghappyness.game.defenses.Wall;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.game.units.Enemy;
import com.frontanilla.farminghappyness.game.units.Tourist;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.Enums;
import com.frontanilla.farminghappyness.utils.Util;

import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.SPAWN_TIME;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;
import static com.frontanilla.farminghappyness.utils.Enums.ConstructionState.BUILDING_TURRET;
import static com.frontanilla.farminghappyness.utils.Enums.TileType.DEFENSIVE_TILE;

public class GameLogic {

    private GameScreen gameScreen;
    private DelayedRemovalArray<Enemy> enemies;
    private DelayedRemovalArray<Defense> defenses;
    private DelayedRemovalArray<Bullet> bullets;
    private float time;
    private boolean lost;
    private Enums.ConstructionState constructionState;

    public GameLogic(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        enemies = new DelayedRemovalArray<>();
        defenses = new DelayedRemovalArray<>();
        bullets = new DelayedRemovalArray<>();
        time = 0;
        lost = false;
        constructionState = Enums.ConstructionState.NONE;
    }

    public void update(float delta) {
        if (!lost) {
            gameScreen.getCamera().handleInput();
            gameScreen.getCamera().update();
            gameScreen.getBatch().setProjectionMatrix(gameScreen.getCamera().combined);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            updateEnemies(delta);
            updateTurrets(delta);
            updateBullets(delta);

            time += delta;
            if (time >= Constants.SPAWN_RATE + SPAWN_TIME) {
                spawnEnemy();
                time -= Constants.SPAWN_RATE;
            }
        }
    }

    private void updateEnemies(float delta) {
        enemies.begin();
        for (Enemy e : enemies) {
            e.update(delta, defenses);
            if (!e.isAlive()) {
                enemies.removeValue(e, true);
                gameScreen.getGameStuff().setMoney(gameScreen.getGameStuff().getMoney() + 1);
            }
            if (Util.getDistance(e.getCenter(), gameScreen.getMap().getFarmingArea().getCenter()) < ENEMY_WIDTH / 2f) {
                lost = true;
            }
        }
        enemies.end();
    }

    private void updateTurrets(float delta) {
        for (Tile[] tileRow : gameScreen.getMap().getDefenseTiles()) {
            for (Tile tile : tileRow) {
                if (tile.getDefense() != null) {
                    tile.getDefense().update(delta);
                    if (tile.getDefense() instanceof Turret) {
                        for (Enemy e : enemies) {
                            if (Util.getDistance(e.getCenter(), tile.getDefense().getCenter()) < Constants.TURRET_RANGE) {
                                Turret turret = (Turret) tile.getDefense();
                                float angle = Util.getAngle(tile.getDefense().getCenter(), e.getCenter()) + 155;
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

    public void touchDown(Vector3 usefulVector, int button) {
        if (!lost) {
            // Check if this happened on a defense construction button
            if (gameScreen.getTurretButton().contains(usefulVector.x, usefulVector.y)) {
                gameScreen.getTurretButton().setColor(Color.GREEN);
                gameScreen.getWallButton().setColor(Color.WHITE);
                gameScreen.getTrapButton().setColor(Color.WHITE);
                constructionState = BUILDING_TURRET;
                return;
            }
            if (gameScreen.getWallButton().contains(usefulVector.x, usefulVector.y)) {
                gameScreen.getWallButton().setColor(Color.GREEN);
                gameScreen.getTurretButton().setColor(Color.WHITE);
                gameScreen.getTrapButton().setColor(Color.WHITE);
                constructionState = Enums.ConstructionState.BUILDING_WALL;
                return;
            }
            if (gameScreen.getTrapButton().contains(usefulVector.x, usefulVector.y)) {
                gameScreen.getTrapButton().setColor(Color.GREEN);
                gameScreen.getTurretButton().setColor(Color.WHITE);
                gameScreen.getWallButton().setColor(Color.WHITE);
                constructionState = Enums.ConstructionState.BUILDING_TRAP;
                return;
            }
            // Check if this happened on a defense tile
            for (Tile[] tileRow : gameScreen.getMap().getDefenseTiles()) {
                for (Tile tile : tileRow) {
                    if (tile.contains(usefulVector.x, usefulVector.y)
                            && gameScreen.getGameStuff().getMoney() >= 10
                            && tile.getType() == DEFENSIVE_TILE
                            && tile.getDefense() == null) {
                        switch (constructionState) {
                            case BUILDING_TURRET:
                                Defense newDefense = new Turret(tile);
                                defenses.add(newDefense);
                                tile.setDefense(newDefense);
                                gameScreen.getGameStuff().setMoney(gameScreen.getGameStuff().getMoney() - 10);
                                break;
                            case BUILDING_WALL:
                                newDefense = new Wall(tile);
                                defenses.add(newDefense);
                                tile.setDefense(newDefense);
                                gameScreen.getGameStuff().setMoney(gameScreen.getGameStuff().getMoney() - 10);
                                break;
                            case BUILDING_TRAP:
                                newDefense = new Trap(tile);
                                defenses.add(newDefense);
                                tile.setDefense(newDefense);
                                gameScreen.getGameStuff().setMoney(gameScreen.getGameStuff().getMoney() - 10);
                                break;
                        }
                        return;
                    }
                }
            }
        } else {
            restart();
        }
    }

    private void restart() {
        gameScreen.getGameStuff().setMoney(100);
        gameScreen.getGameStuff().setWorkers(0);
        for (Tile[] tileRow : gameScreen.getMap().getDefenseTiles()) {
            for (Tile tile : tileRow) {
                tile.setDefense(null);
            }
        }
        enemies = new DelayedRemovalArray<>();
        defenses = new DelayedRemovalArray<>();
        bullets = new DelayedRemovalArray<>();
        lost = false;
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }

    public DelayedRemovalArray<Bullet> getBullets() {
        return bullets;
    }

    public DelayedRemovalArray<Defense> getDefenses() {
        return defenses;
    }

    public Enums.ConstructionState getConstructionState() {
        return constructionState;
    }
}
