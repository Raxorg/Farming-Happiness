package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.frontanilla.farminghappyness.components.ButtonTile;
import com.frontanilla.farminghappyness.components.NinePatcherTile;
import com.frontanilla.farminghappyness.components.ToggleMenuButton;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.defenses.Trap;
import com.frontanilla.farminghappyness.game.defenses.Turret;
import com.frontanilla.farminghappyness.game.defenses.Wall;
import com.frontanilla.farminghappyness.game.entities.plants.Plant;
import com.frontanilla.farminghappyness.game.entities.units.Enemy;
import com.frontanilla.farminghappyness.game.entities.units.Tourist;
import com.frontanilla.farminghappyness.game.other.Bullet;
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
            connector.getGameRenderer().getDynamicBatch().setProjectionMatrix(connector.getCamera().combined);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            connector.getGameState().getRiverArea().update(delta);
            updatePlants(delta);
            updateEnemies(delta);
            updateTurrets(delta);
            updateBullets(delta);

            time += delta;
            if (time >= Constants.SPAWN_RATE + SPAWN_TIME) {
                spawnEnemy();
                time -= Constants.SPAWN_RATE;
            }

            connector.getGameState().getDisplayArea().update(delta, connector.getGameState().getMoney(), connector.getGameState().getWorkers());
        }
    }

    private void updatePlants(float delta) {
        for (Plant p : connector.getGameState().getPlants()) {
            p.update(delta);
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
            if (Util.getDistance(e.getCenter(), connector.getGameState().getFarmingArea().getCenter()) < ENEMY_WIDTH / 2f) {
                lost = true;
            }
        }
        connector.getGameState().getEnemies().end();
    }

    private void updateTurrets(float delta) {
        for (NinePatcherTile ninePatcherTile : connector.getGameState().getDefenseArea().getTiles()) {
            if (ninePatcherTile.getGameEntity() != null) {
                if (ninePatcherTile.getGameEntity() instanceof Turret) {
                    Turret turret = (Turret) ninePatcherTile.getGameEntity();
                    turret.update(delta);
                    for (Enemy e : connector.getGameState().getEnemies()) {
                        if (Util.getDistance(e.getCenter(), turret.getCenter()) < Constants.TURRET_RANGE) {
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
        float angle = Util.getAngle(tourist.getCenter(), connector.getGameState().getFarmingArea().getCenter());
        tourist.setAngle(angle);
        connector.getGameState().getEnemies().add(tourist);
    }

    public void touchDown(Vector3 usefulVector) {
        if (!lost) {
            // Check if this happened on a defense tile
            for (NinePatcherTile ninePatcherTile : connector.getGameState().getDefenseArea().getTiles()) {
                if (ninePatcherTile.contains(usefulVector.x, usefulVector.y)
                        && connector.getGameState().getMoney() >= 10
                        && ninePatcherTile.getType() == DEFENSIVE_TILE // TODO Check if wall, trap or turret ninePatcherTile
                        && ninePatcherTile.getGameEntity() == null) {
                    switch (constructionState) {
                        case BUILDING_TURRET:
                            Defense newDefense = new Turret(ninePatcherTile);
                            connector.getGameState().getDefenses().add(newDefense);
                            ninePatcherTile.setGameEntity(newDefense);
                            connector.getGameState().setMoney(connector.getGameState().getMoney() - 10);
                            break;
                        case BUILDING_WALL:
                            newDefense = new Wall(ninePatcherTile);
                            connector.getGameState().getDefenses().add(newDefense);
                            ninePatcherTile.setGameEntity(newDefense);
                            connector.getGameState().setMoney(connector.getGameState().getMoney() - 10);
                            break;
                        case BUILDING_TRAP:
                            newDefense = new Trap(ninePatcherTile);
                            connector.getGameState().getDefenses().add(newDefense);
                            ninePatcherTile.setGameEntity(newDefense);
                            connector.getGameState().setMoney(connector.getGameState().getMoney() - 10);
                            break;
                    }
                    return;
                }
            }
            // Check if this happened on a farming tile
            for (ButtonTile buttonTile : connector.getGameState().getFarmingArea().getTiles()) {
                if (buttonTile.contains(usefulVector.x, usefulVector.y)) {
                    Plant newPlant = new Plant(Plant.AYARN, buttonTile);
                    connector.getGameState().getPlants().add(newPlant);
                    buttonTile.setGameEntity(newPlant);
                }
            }
        } else {
            restart();
        }
    }

    private void restart() {
        for (NinePatcherTile ninePatcherTile : connector.getGameState().getDefenseArea().getTiles()) {
            ninePatcherTile.setGameEntity(null);
        }
        connector.getGameState().restart();
        lost = false;
        time = 0;
    }

    public Enums.ConstructionState getConstructionState() {
        return constructionState;
    }

    //--------------------
    //   INPUT HANDLING
    //--------------------
    public boolean rawTap(float x, float y) {
        // Check if this happened on a menu activation button
        if (connector.getGameState().getDisplayArea().getToggleMenu().getDefensesButton().contains(x, y)) {
            if (connector.getGameState().getDisplayArea().getToggleMenu().getMenuState() == Enums.MenuState.ACTIVATED) {
                connector.getGameState().getDisplayArea().getToggleMenu().deactivate();
            }
            if (connector.getGameState().getDisplayArea().getToggleMenu().getMenuState() == Enums.MenuState.DEACTIVATED) {
                connector.getGameState().getDisplayArea().getToggleMenu().activate();
            }
            return true;
        }
        // Check if this happened on a menu button
        if (connector.getGameState().getDisplayArea().getToggleMenu().getMenuState() == Enums.MenuState.ACTIVATED) {
            for (ToggleMenuButton button : connector.getGameState().getDisplayArea().getToggleMenu().getActiveButtons()) {
                if (button.contains(x, y)) {
                    restart(); // TODO highlight this button
                    return true;
                }
            }
        }
        return false;
    }

    public void unprojectedTap(float x, float y) {
        for (ButtonTile tile : connector.getGameState().getFarmingArea().getTiles()) {
            if (tile.contains(x, y)) {
                Plant newPlant = new Plant(Plant.AYARN, tile);
                tile.setGameEntity(newPlant); // TODO place according to selection
                connector.getGameState().getPlants().add(newPlant);
            }
        }
    }
}
