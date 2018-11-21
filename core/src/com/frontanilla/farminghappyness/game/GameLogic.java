package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.frontanilla.farminghappyness.components.ButtonTile;
import com.frontanilla.farminghappyness.components.NinePatcherTile;
import com.frontanilla.farminghappyness.components.ToggleMenuButton;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.defenses.Mine;
import com.frontanilla.farminghappyness.game.defenses.Turret;
import com.frontanilla.farminghappyness.game.defenses.Wall;
import com.frontanilla.farminghappyness.game.entities.plants.Plant;
import com.frontanilla.farminghappyness.game.entities.units.Enemy;
import com.frontanilla.farminghappyness.game.entities.units.Tourist;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.Util;

import static com.frontanilla.farminghappyness.utils.Constants.AYARN_COST;
import static com.frontanilla.farminghappyness.utils.Constants.AYARN_ID;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKA_COST;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKA_ID;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKER_COST;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKER_ID;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_SPAWN_RATE;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.GRA_COST;
import static com.frontanilla.farminghappyness.utils.Constants.GRA_ID;
import static com.frontanilla.farminghappyness.utils.Constants.KAERLIGHED_COST;
import static com.frontanilla.farminghappyness.utils.Constants.KAERLIGHED_ID;
import static com.frontanilla.farminghappyness.utils.Constants.KOCHAM_COST;
import static com.frontanilla.farminghappyness.utils.Constants.KOCHAM_ID;
import static com.frontanilla.farminghappyness.utils.Constants.MILESTIBA_COST;
import static com.frontanilla.farminghappyness.utils.Constants.MILESTIBA_ID;
import static com.frontanilla.farminghappyness.utils.Constants.MINE_COST;
import static com.frontanilla.farminghappyness.utils.Constants.RAKKAUS_COST;
import static com.frontanilla.farminghappyness.utils.Constants.RAKKAUS_ID;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.SEVIYORUM_COST;
import static com.frontanilla.farminghappyness.utils.Constants.SEVIYORUM_ID;
import static com.frontanilla.farminghappyness.utils.Constants.SZERELEM_COST;
import static com.frontanilla.farminghappyness.utils.Constants.SZERELEM_ID;
import static com.frontanilla.farminghappyness.utils.Constants.TRAP_ID;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_COST;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_ID;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_COST;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_ID;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;
import static com.frontanilla.farminghappyness.utils.Enums.MenuState;

public class GameLogic {

    private GameConnector connector;
    private float time;
    private boolean lost, playerReady;

    public GameLogic(GameConnector connector) {
        this.connector = connector;
        time = 0;
        lost = false;
        playerReady = false;
    }

    public void update(float delta) {
        if (!lost) {
            connector.getCamera().handleInput();
            connector.getCamera().update();
            connector.getGameRenderer().getDynamicBatch().setProjectionMatrix(connector.getCamera().combined);

            connector.getGameState().getRiverArea().update(delta);
            updatePlants(delta);
            updateEnemies(delta);
            updateTurrets(delta);
            updateMines(delta);
            updateBullets(delta);

            time += delta;
            if (time >= ENEMY_SPAWN_RATE && playerReady) {
                spawnEnemy();
                while (time >= ENEMY_SPAWN_RATE) {
                    time -= ENEMY_SPAWN_RATE;
                }
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
        boolean willRestart = false; // TODO improve this logic
        connector.getGameState().getEnemies().begin();
        for (Enemy e : connector.getGameState().getEnemies()) {
            e.update(delta, connector.getGameState().getDefenses());
            if (!e.isAlive()) {
                connector.getGameState().getEnemies().removeValue(e, true);
                connector.getGameState().setMoney(connector.getGameState().getMoney() + 1);
            }
            if (Util.getDistance(e.getCenter(), connector.getGameState().getFarmingArea().getCenter()) < ENEMY_WIDTH / 2f) {
                willRestart = true;
                //lost = true;
            }
        }
        connector.getGameState().getEnemies().end();
        if (willRestart) {
            restart();
        }
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

    private void updateMines(float delta) {
        for (NinePatcherTile ninePatcherTile : connector.getGameState().getDefenseArea().getTiles()) {
            if (ninePatcherTile.getGameEntity() != null) {
                if (ninePatcherTile.getGameEntity() instanceof Mine) {
                    ((Mine) ninePatcherTile.getGameEntity()).update(delta);
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
        Tourist newTourist;
        if (randSide) { // Left
            float randY = MathUtils.random(0, WORLD_HEIGHT - ENEMY_HEIGHT - RIVER_TILE_SIZE);
            newTourist = new Tourist(-ENEMY_WIDTH, randY);
        } else { // Bottom
            float randX = MathUtils.random(0, WORLD_WIDTH - ENEMY_WIDTH);
            newTourist = new Tourist(randX, -ENEMY_HEIGHT);
        }
        float angle = Util.getAngle(newTourist.getCenter(), connector.getGameState().getFarmingArea().getCenter());
        newTourist.setAngle(angle);
        connector.getGameState().getEnemies().add(newTourist);
    }

    private void restart() {
        connector.getGameState().restart();
        lost = false;
        time = 0;
    }

    //--------------------
    //   INPUT HANDLING
    //--------------------
    public boolean rawTap(float x, float y) {
        // Check if this happened on a menu activation button
        if (connector.getGameState().getDisplayArea().getToggleMenu().getDefensesToggleButton().contains(x, y)) {
            if (connector.getGameState().getDisplayArea().getToggleMenu().getMenuState() == MenuState.SHOWING_DEFENSES_MENU) {
                connector.getGameState().getDisplayArea().getToggleMenu().deactivate();
            } else if (connector.getGameState().getDisplayArea().getToggleMenu().getMenuState() == MenuState.SHOWING_PLANTS_MENU) {
                connector.getGameState().getDisplayArea().getToggleMenu().setMenuState(MenuState.SHOWING_DEFENSES_MENU);
            } else if (connector.getGameState().getDisplayArea().getToggleMenu().getMenuState() == MenuState.DEACTIVATED) {
                connector.getGameState().getDisplayArea().getToggleMenu().activate(true);
            }
            return true;
        }
        if (connector.getGameState().getDisplayArea().getToggleMenu().getPlantsToggleButton().contains(x, y)) {
            if (connector.getGameState().getDisplayArea().getToggleMenu().getMenuState() == MenuState.SHOWING_PLANTS_MENU) {
                connector.getGameState().getDisplayArea().getToggleMenu().deactivate();
            } else if (connector.getGameState().getDisplayArea().getToggleMenu().getMenuState() == MenuState.SHOWING_DEFENSES_MENU) {
                connector.getGameState().getDisplayArea().getToggleMenu().setMenuState(MenuState.SHOWING_PLANTS_MENU);
            } else if (connector.getGameState().getDisplayArea().getToggleMenu().getMenuState() == MenuState.DEACTIVATED) {
                connector.getGameState().getDisplayArea().getToggleMenu().activate(false);
            }
            return true;
        }
        // Check if this happened on a menu button
        if (connector.getGameState().getDisplayArea().getToggleMenu().getMenuState() == MenuState.SHOWING_DEFENSES_MENU
                || connector.getGameState().getDisplayArea().getToggleMenu().getMenuState() == MenuState.SHOWING_PLANTS_MENU) {
            for (ToggleMenuButton button : connector.getGameState().getDisplayArea().getToggleMenu().getActiveButtons()) {
                if (button.contains(x, y)) {
                    for (ToggleMenuButton buttons : connector.getGameState().getDisplayArea().getToggleMenu().getActiveButtons()) {
                        buttons.setColor(Color.SKY);
                    }
                    button.setColor(Color.YELLOW);
                    return true;
                }
            }
        }
        return false;
    }

    public void unprojectedTap(float x, float y) {
        // Check a tap in a defense tile
        for (NinePatcherTile tile : connector.getGameState().getDefenseArea().getTiles()) {
            if (tile.contains(x, y) && tile.getGameEntity() == null) {
                switch (connector.getGameState().getDisplayArea().getToggleMenu().getSelectedDefenseButtonID()) {
                    case TURRET_ID:
                        //TODO test
                        playerReady = true;
                        Defense newDefense = new Turret(tile);
                        tile.setGameEntity(newDefense);
                        connector.getGameState().getDefenses().add(newDefense);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - TURRET_COST);
                        break;
                    case WALL_ID:
                        newDefense = new Wall(tile);
                        tile.setGameEntity(newDefense);
                        connector.getGameState().getDefenses().add(newDefense);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - WALL_COST);
                        break;
                    case TRAP_ID:
                        newDefense = new Mine(tile);
                        tile.setGameEntity(newDefense);
                        connector.getGameState().getDefenses().add(newDefense);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - MINE_COST);
                        break;
                }
            }
        }
        // Check a tap in a farming tile
        for (ButtonTile tile : connector.getGameState().getFarmingArea().getTiles()) {
            if (tile.contains(x, y)) {
                switch (connector.getGameState().getDisplayArea().getToggleMenu().getSelectedPlantButtonID()) {
                    case ELSKER_ID:
                        Plant newPlant = new Plant(Plant.ELSKER, tile);
                        tile.setGameEntity(newPlant);
                        connector.getGameState().getPlants().add(newPlant);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - ELSKER_COST);
                        break;
                    case GRA_ID:
                        newPlant = new Plant(Plant.GRA, tile);
                        tile.setGameEntity(newPlant);
                        connector.getGameState().getPlants().add(newPlant);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - GRA_COST);
                        break;
                    case KOCHAM_ID:
                        newPlant = new Plant(Plant.KOCHAM, tile);
                        tile.setGameEntity(newPlant);
                        connector.getGameState().getPlants().add(newPlant);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - KOCHAM_COST);
                        break;
                    case SZERELEM_ID:
                        newPlant = new Plant(Plant.SZERELEM, tile);
                        tile.setGameEntity(newPlant);
                        connector.getGameState().getPlants().add(newPlant);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - SZERELEM_COST);
                        break;
                    case ELSKA_ID:
                        newPlant = new Plant(Plant.ELSKA, tile);
                        tile.setGameEntity(newPlant);
                        connector.getGameState().getPlants().add(newPlant);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - ELSKA_COST);
                        break;
                    case AYARN_ID:
                        newPlant = new Plant(Plant.AYARN, tile);
                        tile.setGameEntity(newPlant);
                        connector.getGameState().getPlants().add(newPlant);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - AYARN_COST);
                        break;
                    case SEVIYORUM_ID:
                        newPlant = new Plant(Plant.SEVIYORUM, tile);
                        tile.setGameEntity(newPlant);
                        connector.getGameState().getPlants().add(newPlant);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - SEVIYORUM_COST);
                        break;
                    case MILESTIBA_ID:
                        newPlant = new Plant(Plant.MILESTIBA, tile);
                        tile.setGameEntity(newPlant);
                        connector.getGameState().getPlants().add(newPlant);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - MILESTIBA_COST);
                        break;
                    case RAKKAUS_ID:
                        newPlant = new Plant(Plant.RAKKAUS, tile);
                        tile.setGameEntity(newPlant);
                        connector.getGameState().getPlants().add(newPlant);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - RAKKAUS_COST);
                        break;
                    case KAERLIGHED_ID:
                        newPlant = new Plant(Plant.KAERLIGHED, tile);
                        tile.setGameEntity(newPlant);
                        connector.getGameState().getPlants().add(newPlant);
                        connector.getGameState().setMoney(connector.getGameState().getMoney() - KAERLIGHED_COST);
                        break;
                }
            }
        }
    }
}
