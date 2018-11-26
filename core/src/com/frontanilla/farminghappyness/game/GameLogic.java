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
import com.frontanilla.farminghappyness.game.entities.units.Alien;
import com.frontanilla.farminghappyness.game.entities.units.Enemy;
import com.frontanilla.farminghappyness.game.entities.units.Military;
import com.frontanilla.farminghappyness.game.entities.units.Police;
import com.frontanilla.farminghappyness.game.entities.units.Tourist;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.game.other.Level;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.Enums.EnemyType;
import com.frontanilla.farminghappyness.utils.Util;

import static com.frontanilla.farminghappyness.utils.Constants.AYARN_COST;
import static com.frontanilla.farminghappyness.utils.Constants.AYARN_ID;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKA_COST;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKA_ID;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKER_COST;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKER_ID;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_HEIGHT;
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
    private int kills;

    public GameLogic(GameConnector connector) {
        this.connector = connector;
        time = 0;
        lost = false;
        playerReady = false;
        kills = 0;
    }

    //--------------------
    //       UPDATE
    //--------------------
    public void update(float delta) {
        if (!lost) {
            time += delta;
            connector.getCamera().handleInput();
            connector.getCamera().update();
            connector.getGameRenderer().getDynamicBatch().setProjectionMatrix(connector.getCamera().combined);

            connector.getGameState().getAmbientArea().update(delta);
            connector.getGameState().getRiverArea().update(delta);
            if (playerReady) {
                updatePlants(delta);
            }
            updateEnemies(delta);
            updateTurrets(delta);
            updateWalls();
            updateMines(delta);
            updateBullets(delta);

            if (playerReady) {
                connector.getGameState().getLevel().update(delta, this);
            }

            connector.getGameState().getDisplayArea().update(delta, connector.getGameState().getMoney(), connector.getGameState().getAvailableWorkers());
        }
    }

    private void updatePlants(float delta) {
        connector.getGameState().getPlants().begin();

        for (ButtonTile tile : connector.getGameState().getFarmingArea().getTiles()) {
            if (tile.getGameEntity() != null) {
                Plant plant = (Plant) tile.getGameEntity();
                plant.update(delta);
                if (plant.isReady()) {
                    connector.getGameState().setMoney(connector.getGameState().getMoney() + plant.getSellPrice());
                    connector.getGameState().setAvailableWorkers(connector.getGameState().getAvailableWorkers() + 1);
                    connector.getGameState().getPlants().removeValue(plant, true);
                    tile.setGameEntity(null);
                }
            }
        }

        connector.getGameState().getPlants().end();
    }

    private void updateEnemies(float delta) {
        boolean willRestart = false; // TODO testing restart, delete
        connector.getGameState().getEnemies().begin();
        for (Enemy enemy : connector.getGameState().getEnemies()) {
            enemy.update(delta, connector.getGameState().getDefenses());
            if (enemy.isLifeless()) {
                connector.getGameState().getEnemies().removeValue(enemy, true);
                kills++;
                if (connector.getGameState().getLevel().isCompleted(kills)) {
                    switch (connector.getGameState().getLevel().getLevelNumber()) {
                        case 1:
                            connector.getGameState().setLevel(Level.level2);
                            connector.getGameState().getDisplayArea().getLevelLabel().startLevel(2);
                            break;
                        case 2:
                            connector.getGameState().setLevel(Level.level3);
                            connector.getGameState().getDisplayArea().getLevelLabel().startLevel(3);
                            break;
                        case 3:
                            connector.getGameState().setLevel(Level.level4);
                            connector.getGameState().getDisplayArea().getLevelLabel().startLevel(4);
                            break;
                        case 4:
                            restart();
                            break;
                    }
                    kills = 0;
                }
                //connector.getGameState().setMoney(connector.getGameState().getMoney() + 1); TODO money comes from plants
            }
            if (Util.getDistance(enemy.getCenter(), connector.getGameState().getFarmingArea().getCenter()) < ENEMY_WIDTH / 2f) {
                willRestart = true;
                //lost = true;
            }
        }
        connector.getGameState().getEnemies().end();
        if (willRestart) {
            restart();
        }
    }

    private void updateWalls() {
        connector.getGameState().getDefenses().begin();

        for (NinePatcherTile ninePatcherTile : connector.getGameState().getDefenseArea().getTiles()) {
            if (ninePatcherTile.getGameEntity() != null) {
                if (ninePatcherTile.getGameEntity() instanceof Wall) {
                    Wall wall = (Wall) ninePatcherTile.getGameEntity();
                    if (wall.isLifeless()) {
                        connector.getGameState().getDefenses().removeValue(wall, true);
                        ninePatcherTile.setGameEntity(null);
                    }
                }
            }
        }

        connector.getGameState().getDefenses().end();
    }

    private void updateTurrets(float delta) {
        connector.getGameState().getDefenses().begin();

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
                    if (turret.isLifeless()) {
                        connector.getGameState().getDefenses().removeValue(turret, true);
                        ninePatcherTile.setGameEntity(null);
                    }
                }
            }
        }

        connector.getGameState().getDefenses().end();
    }

    private void updateMines(float delta) {
        connector.getGameState().getDefenses().begin();
        for (NinePatcherTile ninePatcherTile : connector.getGameState().getDefenseArea().getTiles()) {
            if (ninePatcherTile.getGameEntity() != null) {
                if (ninePatcherTile.getGameEntity() instanceof Mine) {
                    Mine mine = (Mine) ninePatcherTile.getGameEntity();
                    mine.update(delta);
                    if (mine.isActivated()) {
                        connector.getGameState().getDefenses().removeValue(mine, true);
                        ninePatcherTile.setGameEntity(null);
                    }
                }
            }
        }
        connector.getGameState().getDefenses().end();
    }

    private void updateBullets(float delta) {
        connector.getGameState().getBullets().begin();
        for (Bullet bullet : connector.getGameState().getBullets()) {
            bullet.update(delta);
            if (bullet.hasExploded()) {
                connector.getGameState().getBullets().removeValue(bullet, true);
            }
        }
        connector.getGameState().getBullets().end();
    }

    //--------------------
    //       ACTIONS
    //--------------------
    public void spawnEnemy(EnemyType enemyType) {
        boolean randSide = MathUtils.randomBoolean();
        Enemy newEnemy = null;
        if (randSide) { // Left
            float randY = MathUtils.random(0, WORLD_HEIGHT - ENEMY_HEIGHT - RIVER_TILE_SIZE);
            switch (enemyType) {
                case TOURIST:
                    newEnemy = new Tourist(-ENEMY_WIDTH, randY);
                    break;
                case POLICE:
                    newEnemy = new Police(-ENEMY_WIDTH, randY);
                    break;
                case MILITARY:
                    newEnemy = new Military(-ENEMY_WIDTH, randY);
                    break;
                case ALIEN:
                    newEnemy = new Alien(-ENEMY_WIDTH, randY);
                    break;
            }
        } else { // Bottom
            float randX = MathUtils.random(0, WORLD_WIDTH - ENEMY_WIDTH);
            switch (enemyType) {
                case TOURIST:
                    newEnemy = new Tourist(randX, -ENEMY_HEIGHT);
                    break;
                case POLICE:
                    newEnemy = new Police(randX, -ENEMY_HEIGHT);
                    break;
                case MILITARY:
                    newEnemy = new Military(randX, -ENEMY_HEIGHT);
                    break;
                case ALIEN:
                    newEnemy = new Alien(randX, -ENEMY_HEIGHT);
                    break;
            }
        }
        float angle = Util.getAngle(newEnemy.getCenter(), connector.getGameState().getFarmingArea().getCenter());
        newEnemy.setAngle(angle);
        connector.getGameState().getEnemies().add(newEnemy);
    }

    private void placeDefense(NinePatcherTile tile) {
        switch (connector.getGameState().getDisplayArea().getToggleMenu().getSelectedDefenseButtonID()) {
            case TURRET_ID:
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

    private void placePlant(ButtonTile tile) {
        switch (connector.getGameState().getDisplayArea().getToggleMenu().getSelectedPlantButtonID()) {
            case ELSKER_ID:
                Plant newPlant = new Plant(Plant.ELSKER, tile);
                tile.setGameEntity(newPlant);
                connector.getGameState().getPlants().add(newPlant);
                connector.getGameState().setMoney(connector.getGameState().getMoney() - ELSKER_COST);
                connector.getGameState().setAvailableWorkers(connector.getGameState().getAvailableWorkers() - 1);
                break;
            case GRA_ID:
                newPlant = new Plant(Plant.GRA, tile);
                tile.setGameEntity(newPlant);
                connector.getGameState().getPlants().add(newPlant);
                connector.getGameState().setMoney(connector.getGameState().getMoney() - GRA_COST);
                connector.getGameState().setAvailableWorkers(connector.getGameState().getAvailableWorkers() - 1);
                break;
            case KOCHAM_ID:
                newPlant = new Plant(Plant.KOCHAM, tile);
                tile.setGameEntity(newPlant);
                connector.getGameState().getPlants().add(newPlant);
                connector.getGameState().setMoney(connector.getGameState().getMoney() - KOCHAM_COST);
                connector.getGameState().setAvailableWorkers(connector.getGameState().getAvailableWorkers() - 1);
                break;
            case SZERELEM_ID:
                newPlant = new Plant(Plant.SZERELEM, tile);
                tile.setGameEntity(newPlant);
                connector.getGameState().getPlants().add(newPlant);
                connector.getGameState().setMoney(connector.getGameState().getMoney() - SZERELEM_COST);
                connector.getGameState().setAvailableWorkers(connector.getGameState().getAvailableWorkers() - 1);
                break;
            case ELSKA_ID:
                newPlant = new Plant(Plant.ELSKA, tile);
                tile.setGameEntity(newPlant);
                connector.getGameState().getPlants().add(newPlant);
                connector.getGameState().setMoney(connector.getGameState().getMoney() - ELSKA_COST);
                connector.getGameState().setAvailableWorkers(connector.getGameState().getAvailableWorkers() - 1);
                break;
            case AYARN_ID:
                newPlant = new Plant(Plant.AYARN, tile);
                tile.setGameEntity(newPlant);
                connector.getGameState().getPlants().add(newPlant);
                connector.getGameState().setMoney(connector.getGameState().getMoney() - AYARN_COST);
                connector.getGameState().setAvailableWorkers(connector.getGameState().getAvailableWorkers() - 1);
                break;
            case SEVIYORUM_ID:
                newPlant = new Plant(Plant.SEVIYORUM, tile);
                tile.setGameEntity(newPlant);
                connector.getGameState().getPlants().add(newPlant);
                connector.getGameState().setMoney(connector.getGameState().getMoney() - SEVIYORUM_COST);
                connector.getGameState().setAvailableWorkers(connector.getGameState().getAvailableWorkers() - 1);
                break;
            case MILESTIBA_ID:
                newPlant = new Plant(Plant.MILESTIBA, tile);
                tile.setGameEntity(newPlant);
                connector.getGameState().getPlants().add(newPlant);
                connector.getGameState().setMoney(connector.getGameState().getMoney() - MILESTIBA_COST);
                connector.getGameState().setAvailableWorkers(connector.getGameState().getAvailableWorkers() - 1);
                break;
            case RAKKAUS_ID:
                newPlant = new Plant(Plant.RAKKAUS, tile);
                tile.setGameEntity(newPlant);
                connector.getGameState().getPlants().add(newPlant);
                connector.getGameState().setMoney(connector.getGameState().getMoney() - RAKKAUS_COST);
                connector.getGameState().setAvailableWorkers(connector.getGameState().getAvailableWorkers() - 1);
                break;
            case KAERLIGHED_ID:
                newPlant = new Plant(Plant.KAERLIGHED, tile);
                tile.setGameEntity(newPlant);
                connector.getGameState().getPlants().add(newPlant);
                connector.getGameState().setMoney(connector.getGameState().getMoney() - KAERLIGHED_COST);
                connector.getGameState().setAvailableWorkers(connector.getGameState().getAvailableWorkers() - 1);
                break;
        }
    }

    private void restart() {
        connector.getGameState().restart();
        lost = false;
        time = 0;
        playerReady = false;
        kills = 0;
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
        // Check if this happened on the ready button
        if (connector.getGameState().getDisplayArea().getReadyButton().contains(x, y)
                && connector.getGameState().getDisplayArea().getReadyButton().isVisible()) {
            playerReady = true;
            connector.getGameState().getDisplayArea().getReadyButton().setVisible(false);
            return true;
        }
        return false;
    }

    public void unprojectedTap(float x, float y) {
        // Check a tap in a defense tile
        for (NinePatcherTile tile : connector.getGameState().getDefenseArea().getTiles()) {
            if (tile.contains(x, y)) {
                if (tile.getGameEntity() == null) {
                    placeDefense(tile);
                } else if (tile.getGameEntity() instanceof Turret) {
                    connector.getGameState().getDefenseArea().toggleShowingTurretRanges();
                }
            }
        }
        // Check a tap in a farming tile
        for (ButtonTile tile : connector.getGameState().getFarmingArea().getTiles()) {
            if (tile.contains(x, y) && tile.getGameEntity() == null) {
                placePlant(tile);
            }
        }
        // Check a tap in the laboratory
        if (connector.getGameState().getFarmingArea().getLaboratory().contains(x, y)) {
            connector.getGameState().getFarmingArea().getLaboratory().toggleShowingTechTree();
        }
    }
}
