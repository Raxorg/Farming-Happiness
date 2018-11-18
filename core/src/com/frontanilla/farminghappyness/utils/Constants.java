package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.Gdx;

public class Constants {

    // World
    public static final int WORLD_WIDTH = 1000;
    public static final int WORLD_HEIGHT = 1100;
    //------------------
    //      TILES
    //------------------
    public static final float BACKGROUND_TILE_SIZE = 50f;
    // Grids
    public static final float TILE_SIZE = 25f;
    public static final float TILE_SPACING = 5f;
    // Global Area
    public static final int GRASS_COLUMNS = WORLD_WIDTH / 100;
    public static final int GRASS_ROWS = WORLD_HEIGHT / 100;
    public static final float GRASS_WIDTH = WORLD_WIDTH / GRASS_COLUMNS;
    public static final float GRASS_HEIGHT = WORLD_HEIGHT / GRASS_ROWS;
    //------------------
    //      RIVER
    //------------------
    public static final float RIVER_TILE_SIZE = 100;
    public static final int RIVER_TILES = (int) (WORLD_WIDTH / RIVER_TILE_SIZE + 1);
    //------------------
    //   DEFENSE AREA
    //------------------
    public static final float DEFENSE_TILE_SIZE = 40f;

    public static final int LEFT_DEFENSE_ROWS = (int) (500 / DEFENSE_TILE_SIZE);
    public static final int LEFT_DEFENSE_COLUMNS = 3;
    public static final int BOTTOM_DEFENSE_ROWS = 3;
    public static final int BOTTOM_DEFENSE_COLUMNS = (int) (500 / DEFENSE_TILE_SIZE);

    private static final float DEFENSE_X_SPACE = 500 - DEFENSE_TILE_SIZE * BOTTOM_DEFENSE_COLUMNS;
    public static final float DEFENSE_X_SPACING = DEFENSE_X_SPACE / (BOTTOM_DEFENSE_COLUMNS - 1);
    private static final float DEFENSE_Y_SPACE = 500 - DEFENSE_TILE_SIZE * LEFT_DEFENSE_ROWS;
    public static final float DEFENSE_Y_SPACING = DEFENSE_Y_SPACE / (LEFT_DEFENSE_ROWS - 1);

    public static final float LEFT_DEFENSE_X = 500 - DEFENSE_TILE_SIZE * 3 - DEFENSE_X_SPACING * 3;
    public static final float LEFT_DEFENSE_Y = 500;
    public static final float BOTTOM_DEFENSE_X = 500;
    public static final float BOTTOM_DEFENSE_Y = 500 - DEFENSE_TILE_SIZE * 3 - DEFENSE_Y_SPACING * 3;
    //------------------
    //   FARMING AREA
    //------------------
    public static final float FARMING_TILE_SIZE = 35f;
    public static final float FARMING_AREA_SIZE = 500f;

    public static final int FARMING_AREA_COLUMNS = (int) (FARMING_AREA_SIZE / FARMING_TILE_SIZE);
    public static final float FARMING_X_SPACE = 5;
    public static final float FARMING_AREA_X = 500f;

    public static final int FARMING_AREA_ROWS = (int) (FARMING_AREA_SIZE / FARMING_TILE_SIZE);
    public static final float FARMING_Y_SPACE = 5;
    public static final float FARMING_AREA_Y = 500f;
    //------------------
    //      CAMERA
    //------------------
    public static final float PANNING_SPEED = 10f;
    public static final float OTHER_PANNING_SPEED = 1f;
    public static final float MIN_ZOOM = 1f;
    public static final float SCREEN_WIDTH_VISIBILITY_PERCENTAGE_ = 1f;
    public static final float SCREEN_HEIGHT_VISIBILITY_PERCENTAGE_ = 1f;
    //------------------
    //      TURRET
    //------------------
    public static final float TURRET_WIDTH = 40f;
    public static final float TURRET_HEIGHT = 60f;
    public static final float TURRET_RANGE = 100f;
    public static final float TURRET_COOL_DOWN = 0.2f;
    public static final int TURRET_INITIAL_HEALTH = 10;

    public static final float TURRET_CANNON_WIDTH = TURRET_WIDTH / 3f;
    public static final float TURRET_CANNON_HEIGHT = TURRET_HEIGHT / 5f;
    public static final float TURRET_CANNON_X_OFFSET = TURRET_WIDTH / 2f - TURRET_CANNON_WIDTH / 2;
    public static final float TURRET_CANNON_Y_OFFSET = TURRET_HEIGHT * 0.7f;

    public static final float TURRET_BULLET_SIZE = TILE_SIZE / 8f;
    public static final float TURRET_BULLET_SPEED = 350f;
    //------------------
    //       WALL
    //------------------
    public static final float WALL_SIZE = TILE_SIZE;
    public static final int WALL_INITIAL_HEALTH = 25;
    //------------------
    //       TRAP
    //------------------
    public static final float TRAP_SIZE = TILE_SIZE;
    public static final int TRAP_INITIAL_HEALTH = 5;
    // Enemies
    public static final float ENEMY_WIDTH = 15f;
    public static final float ENEMY_HEIGHT = ENEMY_WIDTH * 1.8f;
    public static final float SPAWN_RATE = 0.4f;
    public static final float SPAWN_TIME = 2f;
    // Tourists
    public static final int TOURIST_INITIAL_LIFE = 7;
    public static final float TOURIST_SPEED = 50f;
    // Life bars
    public static final float LIFE_BAR_HEIGHT = 3f;
    public static final float LIFE_BAR_SIDES_WIDTH = (LIFE_BAR_HEIGHT / 3f);
    // Progress bars
    public static final float PROGRESS_BAR_HEIGHT = 3f;
    public static final float PROGRESS_BAR_SIDES_WIDTH = (PROGRESS_BAR_HEIGHT / 3f);
    // Resource frames
    public static final float RESOURCE_FRAME_WIDTH = Gdx.graphics.getWidth() / 6f;
    public static final float RESOURCE_FRAME_HEIGHT = RESOURCE_FRAME_WIDTH / 3f;
    public static final float RESOURCE_FRAME_BORDER = RESOURCE_FRAME_HEIGHT / 20f;
    // Resource frame image
    public static final float RESOURCE_FRAME_IMAGE_WIDTH = RESOURCE_FRAME_WIDTH * 0.25f;
    public static final float RESOURCE_FRAME_IMAGE_HEIGHT = RESOURCE_FRAME_HEIGHT / 2f;
    // Resource frame quantity
    public static final float RESOURCE_FRAME_QUANTITY_X_OFFSET = RESOURCE_FRAME_BORDER * 4f;
    public static final float RESOURCE_FRAME_QUANTITY_Y_OFFSET = RESOURCE_FRAME_HEIGHT / 2f;
    // Defense buttons
    public static final float DEFENSE_BUTTON_SIZE = 50f;
    //------------------
    //      PLANTS
    //------------------
    public static final float PLANT_SIZE = 25f;
    public static final float PLANT_TILE_SPACING = (FARMING_TILE_SIZE - PLANT_SIZE) / 2f;
    public static final float PLANT_PRODUCTION_TIME = 5f;
    //------------------
    //    DECORATION
    //------------------
    public static final int GRASS_QUANTITY = 200;
    public static final float GRASS_SIZE = 15f;
    //------------------
    //       TEST
    //------------------
    public static final int NPTEST_BORDER_PIXELS = 2;
}