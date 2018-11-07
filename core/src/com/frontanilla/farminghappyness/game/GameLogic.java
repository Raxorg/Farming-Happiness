package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
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
import static com.frontanilla.farminghappyness.utils.Enums.ConstructionState;
import static com.frontanilla.farminghappyness.utils.Enums.ConstructionState.BUILDING_TURRET;
import static com.frontanilla.farminghappyness.utils.Enums.TileType.DEFENSIVE_TILE;

public class GameLogic {

    private GameConnector connector;
    private float time;
    private boolean lost;
    private ConstructionState constructionState;

    public GameLogic(GameConnector connector) {
        this.connector = connector;
        time = 0;
        lost = false;
        constructionState = Enums.ConstructionState.NONE;
    }

    public void update(float delta) {
        if (!lost) {
            connector.getCamera().handleInput();
            connector.getCamera().update();
            connector.getBatch().setProjectionMatrix(connector.getCamera().combined);
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
        connector.getGameState().getEnemies().begin();
        for (Enemy e : connector.getGameState().getEnemies()) {
            e.update(delta, connector.getGameState().getDefenses());
            if (!e.isAlive()) {
                connector.getGameState().getEnemies().removeValue(e, true);
                connector.getGameState().setMoney(connector.getGameState().getMoney() + 1);
            }
            if (Util.getDistance(e.getCenter(), connector.getRenderer().getFarmingArea().getCenter()) < ENEMY_WIDTH / 2f) {
                lost = true;
            }
        }
        connector.getGameState().getEnemies().end();
    }

    private void updateTurrets(float delta) {
        for (Tile[] tileRow : connector.getRenderer().getDefenseTiles()) {
            for (Tile tile : tileRow) {
                if (tile.getDefense() != null) {
                    tile.getDefense().update(delta);
                    if (tile.getDefense() instanceof Turret) {
                        for (Enemy e : connector.getGameState().getEnemies()) {
                            if (Util.getDistance(e.getCenter(), tile.getDefense().getCenter()) < Constants.TURRET_RANGE) {
                                Turret turret = (Turret) tile.getDefense();
                                float angle = Util.getAngle(tile.getDefense().getCenter(), e.getCenter()) + 155;
                                turret.setCannonRotation(angle);
                                if (turret.getCoolDown() == 0) {
                                    connector.getGameState().getBullets().add(turret.shoot(e));
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
        connector.getGameState().getBullets().begin();
        for (Bullet b : connector.getGameState().getBullets()) {
            b.update(delta);
            if (b.hasExploded()) {
                connector.getGameState().getBullets().removeValue(b, true);
            }
        }
        connector.getGameState().getBullets().end();
    }

    private void spawnEnemy() {
        boolean randSide = MathUtils.randomBoolean();
        Tourist tourist;
        if (randSide) { // Left
            float randY = MathUtils.random(0, WORLD_HEIGHT - ENEMY_HEIGHT - RIVER_TILE_SIZE);
            tourist = new Tourist(-ENEMY_WIDTH, randY);
        } else { // Bottom
            float randX = MathUtils.random(0, WORLD_WIDTH - ENEMY_WIDTH);
            tourist = new Tourist(randX, -ENEMY_HEIGHT);
        }
        float angle = Util.getAngle(tourist.getCenter(), connector.getRenderer().getFarmingArea().getCenter());
        tourist.setAngle(angle);
        connector.getGameState().getEnemies().add(tourist);
    }

    public void touchDown(Vector3 usefulVector, int button) {
        if (!lost) {
            // Check if this happened on a defense construction button
            if (connector.getTurretButton().contains(usefulVector.x, usefulVector.y)) {
                connector.getTurretButton().setColor(Color.GREEN);
                connector.getWallButton().setColor(Color.WHITE);
                connector.getTrapButton().setColor(Color.WHITE);
                constructionState = BUILDING_TURRET;
                return;
            }
            if (connector.getWallButton().contains(usefulVector.x, usefulVector.y)) {
                connector.getWallButton().setColor(Color.GREEN);
                connector.getTurretButton().setColor(Color.WHITE);
                connector.getTrapButton().setColor(Color.WHITE);
                constructionState = Enums.ConstructionState.BUILDING_WALL;
                return;
            }
            if (connector.getTrapButton().contains(usefulVector.x, usefulVector.y)) {
                connector.getTrapButton().setColor(Color.GREEN);
                connector.getTurretButton().setColor(Color.WHITE);
                connector.getWallButton().setColor(Color.WHITE);
                constructionState = Enums.ConstructionState.BUILDING_TRAP;
                return;
            }
            // Check if this happened on a defense tile
            for (Tile[] tileRow : connector.getRenderer().getDefenseTiles()) {
                for (Tile tile : tileRow) {
                    if (tile.contains(usefulVector.x, usefulVector.y)
                            && connector.getGameState().getMoney() >= 10
                            && tile.getType() == DEFENSIVE_TILE
                            && tile.getDefense() == null) {
                        switch (constructionState) {
                            case BUILDING_TURRET:
                                Defense newDefense = new Turret(tile);
                                connector.getGameState().getDefenses().add(newDefense);
                                tile.setDefense(newDefense);
                                connector.getGameState().setMoney(connector.getGameState().getMoney() - 10);
                                break;
                            case BUILDING_WALL:
                                newDefense = new Wall(tile);
                                connector.getGameState().getDefenses().add(newDefense);
                                tile.setDefense(newDefense);
                                connector.getGameState().setMoney(connector.getGameState().getMoney() - 10);
                                break;
                            case BUILDING_TRAP:
                                newDefense = new Trap(tile);
                                connector.getGameState().getDefenses().add(newDefense);
                                tile.setDefense(newDefense);
                                connector.getGameState().setMoney(connector.getGameState().getMoney() - 10);
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
        for (Tile[] tileRow : connector.getRenderer().getDefenseTiles()) {
            for (Tile tile : tileRow) {
                tile.setDefense(null);
            }
        }
        connector.getGameState().restart();
        lost = false;
        time = 0;
    }

    public Enums.ConstructionState getConstructionState() {
        return constructionState;
    }

    public void touchUp(float x, float y) {
        // TODO
    }

    public void touchDragged(float x, float y) {
        // TODO
    }

    public void tap(float x, float y, int count) {
        // TODO
    }
}
