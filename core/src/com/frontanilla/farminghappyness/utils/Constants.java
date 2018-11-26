package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.Gdx;

public class Constants {

    //------------------
    //      WORLD
    //------------------
    public static final int WORLD_WIDTH = 1000;
    public static final int WORLD_HEIGHT = 1100;
    //------------------
    //     TERRAIN
    //------------------
    public static final int BACKGROUND_GRASS_TILE_SIZE = 100;
    public static final int BACKGROUND_GRASS_TILE_ROWS = WORLD_HEIGHT / BACKGROUND_GRASS_TILE_SIZE;
    public static final int BACKGROUND_GRASS_TILE_COLUMNS = WORLD_WIDTH / BACKGROUND_GRASS_TILE_SIZE;
    //------------------
    //      RIVER
    //------------------
    public static final int RIVER_TILE_SIZE = 100;
    public static final int RIVER_TILES = (int) (WORLD_WIDTH / RIVER_TILE_SIZE + 1);
    //------------------
    //   DEFENSE AREA
    //------------------
    public static final float DEFENSE_TILE_SIZE = 40f;
    // Rows & Columns
    public static final int LEFT_DEFENSE_ROWS = (int) (500 / DEFENSE_TILE_SIZE);
    public static final int LEFT_DEFENSE_COLUMNS = 3;
    public static final int BOTTOM_DEFENSE_ROWS = 3;
    public static final int BOTTOM_DEFENSE_COLUMNS = (int) (500 / DEFENSE_TILE_SIZE);
    // Spacing
    private static final float DEFENSE_X_SPACE = 500 - DEFENSE_TILE_SIZE * BOTTOM_DEFENSE_COLUMNS;
    public static final float DEFENSE_X_SPACING = DEFENSE_X_SPACE / (BOTTOM_DEFENSE_COLUMNS - 1);
    private static final float DEFENSE_Y_SPACE = 500 - DEFENSE_TILE_SIZE * LEFT_DEFENSE_ROWS;
    public static final float DEFENSE_Y_SPACING = DEFENSE_Y_SPACE / (LEFT_DEFENSE_ROWS - 1);
    // Positioning
    public static final float LEFT_DEFENSE_X = 500 - DEFENSE_TILE_SIZE * 3 - DEFENSE_X_SPACING * 3;
    public static final float LEFT_DEFENSE_Y = 500;
    public static final float BOTTOM_DEFENSE_X = 500;
    public static final float BOTTOM_DEFENSE_Y = 500 - DEFENSE_TILE_SIZE * 3 - DEFENSE_Y_SPACING * 3;
    //------------------
    //   FARMING AREA
    //------------------
    public static final int FARMING_TILE_SIZE = 35;
    public static final int FARMING_AREA_SIZE = 500;

    public static final int FARMING_AREA_COLUMNS = FARMING_AREA_SIZE / FARMING_TILE_SIZE;
    public static final int FARMING_AREA_X = 500;
    public static final int FARMING_AREA_X_OFFSET = (FARMING_AREA_SIZE - FARMING_AREA_COLUMNS * FARMING_TILE_SIZE) / 2;

    public static final int FARMING_AREA_ROWS = (FARMING_AREA_SIZE / FARMING_TILE_SIZE) / 2;
    public static final int FARMING_AREA_Y = 500;
    //-------------------
    //     FARM BASE
    //-------------------
    public static final int FARM_BASE_WIDTH = 135;
    public static final int FARM_BASE_HEIGHT = 69;
    public static final int FARM_BASE_X = WORLD_WIDTH - FARM_BASE_WIDTH;
    public static final int FARM_BASE_Y = WORLD_HEIGHT - RIVER_TILE_SIZE - FARM_BASE_HEIGHT;
    //------------------
    //    LABORATORY
    //------------------
    public static final int LABORATORY_WIDTH = 182;
    public static final int LABORATORY_HEIGHT = 103;
    public static final int LABORATORY_X = WORLD_WIDTH - LABORATORY_WIDTH * 2;
    public static final int LABORATORY_Y = WORLD_HEIGHT - RIVER_TILE_SIZE - LABORATORY_HEIGHT;
    // Tech tree panel
    public static final float TECH_TREE_PANEL_WIDTH = Gdx.graphics.getWidth() * 0.5f;
    public static final float TECH_TREE_PANEL_HEIGHT = Gdx.graphics.getHeight() * 0.8f;
    public static final float TECH_TREE_PANEL_X = Gdx.graphics.getWidth() - Gdx.graphics.getHeight() * 0.05f - TECH_TREE_PANEL_WIDTH;
    public static final float TECH_TREE_PANEL_Y = Gdx.graphics.getHeight() * 0.1f;
    //------------------
    //      CAMERA
    //------------------
    public static final float MIN_ZOOM = 1.5f;
    public static final float SCREEN_WIDTH_VISIBILITY_PERCENTAGE = 0.8f;
    public static final float SCREEN_HEIGHT_VISIBILITY_PERCENTAGE = 1f;
    //------------------
    //      TURRET
    //------------------
    public static final int TURRET_WIDTH = 40;
    public static final int TURRET_HEIGHT = 57;
    // Stats
    public static final float TURRET_RANGE = 100f;
    public static final float TURRET_COOL_DOWN = 0.2f;
    public static final int TURRET_INITIAL_HEALTH = 10;
    public static final int TURRET_COST = 1;
    // Turret cannon
    public static final int TURRET_CANNON_HEIGHT = 43;
    // Turret bullet
    public static final int TURRET_BULLET_SIZE = TURRET_WIDTH / 10;
    public static final float TURRET_BULLET_SPEED = 350f;
    //------------------
    //       WALL
    //------------------
    public static final int WALL_WIDTH = 42;
    public static final int WALL_HEIGHT = 50;
    public static final int WALL_X_OFFSET = 1;
    // Stats
    public static final int WALL_INITIAL_HEALTH = 25;
    public static final int WALL_COST = 2;
    //------------------
    //       MINE
    //------------------
    public static final int MINE_WIDTH = 40;
    public static final int MINE_HEIGHT = 40;
    // Stats
    public static final int MINE_INITIAL_HEALTH = 5;
    public static final int MINE_COST = 2;
    //------------------
    //      ENEMIES
    //------------------
    public static final float ENEMY_WIDTH = 15f;
    public static final float ENEMY_HEIGHT = ENEMY_WIDTH * 1.8f;
    public static final float ENEMY_SPAWN_RATE = 0.25f;
    //------------------
    //     TOURISTS
    //------------------
    public static final int TOURIST_INITIAL_LIFE = 7;
    public static final float TOURIST_SPEED = 50f;
    // Life bars
    public static final float LIFE_BAR_HEIGHT = 3f;
    public static final float LIFE_BAR_SIDES_WIDTH = (LIFE_BAR_HEIGHT / 3f);
    // Progress bars
    public static final float PROGRESS_BAR_HEIGHT = 3f;
    public static final float PROGRESS_BAR_SIDES_WIDTH = (PROGRESS_BAR_HEIGHT / 3f);
    //-------------------
    //  RESOURCE FRAMES
    //-------------------
    public static final float RESOURCE_FRAME_WIDTH = Gdx.graphics.getWidth() / 8f;
    public static final float RESOURCE_FRAME_HEIGHT = RESOURCE_FRAME_WIDTH / 3f;
    public static final float RESOURCE_FRAME_X = Gdx.graphics.getWidth() - RESOURCE_FRAME_WIDTH * 2;
    public static final float RESOURCE_FRAME_Y = Gdx.graphics.getHeight() - RESOURCE_FRAME_HEIGHT;
    public static final float RESOURCE_FRAME_BORDER = RESOURCE_FRAME_HEIGHT / 20f;
    // Resource frame image
    public static final float RESOURCE_FRAME_IMAGE_WIDTH = RESOURCE_FRAME_WIDTH * 0.25f;
    public static final float RESOURCE_FRAME_IMAGE_HEIGHT = RESOURCE_FRAME_HEIGHT / 2f;
    // Resource frame quantity
    public static final float RESOURCE_FRAME_QUANTITY_X_OFFSET = RESOURCE_FRAME_BORDER * 4f;
    public static final float RESOURCE_FRAME_QUANTITY_Y_OFFSET = RESOURCE_FRAME_HEIGHT / 2f;
    //-----------------
    //   TOGGLE MENU
    //-----------------
    // Menu size
    public static final float TOGGLE_MENU_WIDTH = Gdx.graphics.getWidth() / 8f;
    public static final float TOGGLE_MENU_HEIGHT = Gdx.graphics.getHeight();
    // Menu activators
    public static final float MENU_ACTIVATION_BUTTON_SIZE = TOGGLE_MENU_WIDTH * 0.5f;
    public static final float MENU_ACTIVATION_BUTTON_Y = TOGGLE_MENU_HEIGHT - MENU_ACTIVATION_BUTTON_SIZE;
    // Properties
    public static final float MENU_ACTIVATION_TIME = 0.1f;
    // Menu buttons
    public static final float MENU_BUTTON_SIZE = TOGGLE_MENU_WIDTH * 0.65f;
    public static final float MENU_BUTTON_Y_SPACING = MENU_BUTTON_SIZE * 1.25f;
    public static final float MENU_BUTTON_IMAGE_WIDTH = MENU_BUTTON_SIZE * 0.7f;
    public static final float MENU_BUTTON_X_OFFSET = (TOGGLE_MENU_WIDTH - MENU_BUTTON_SIZE) / 2f;
    //public static final float MENU_BUTTON_Y_OFFSET = Gdx.graphics.getHeight() / 10f;
    public static final float MENU_BUTTON_Y_OFFSET = 0;
    public static final float MENU_BUTTON_IMAGE_X_OFFSET = (MENU_BUTTON_SIZE - MENU_BUTTON_IMAGE_WIDTH) / 2f;
    //------------------
    //      PLANTS
    //------------------
    public static final float PLANT_SIZE = 25f;
    public static final float PLANT_TILE_SPACING = (FARMING_TILE_SIZE - PLANT_SIZE) / 2f;
    public static final float PLANT_PRODUCTION_TIME = 5f;
    // Costs
    public static final int ELSKER_COST = 1;
    public static final int ELSKER_SELL_PRICE = 2;
    public static final int GRA_COST = 1;
    public static final int GRA_SELL_PRICE = 2;
    public static final int KOCHAM_COST = 1;
    public static final int KOCHAM_SELL_PRICE = 2;
    public static final int SZERELEM_COST = 1;
    public static final int SZERELEM_SELL_PRICE = 2;
    public static final int ELSKA_COST = 2;
    public static final int ELSKA_SELL_PRICE = 4;
    public static final int AYARN_COST = 2;
    public static final int AYARN_SELL_PRICE = 4;
    public static final int SEVIYORUM_COST = 2;
    public static final int SEVIYORUM_SELL_PRICE = 4;
    public static final int MILESTIBA_COST = 3;
    public static final int MILESTIBA_SELL_PRICE = 6;
    public static final int RAKKAUS_COST = 3;
    public static final int RAKKAUS_SELL_PRICE = 6;
    public static final int KAERLIGHED_COST = 4;
    public static final int KAERLIGHED_SELL_PRICE = 8;
    //-------------------
    //      AMBIENT
    //-------------------
    // Trees
    public static final int TREE_WIDTH = 35;
    public static final int TREE_HEIGHT = 52;
    public static final int SIDE_TREES_QUANTITY = 100;
    // Clouds
    public static final int CLOUD_WIDTH = 50;
    public static final int CLOUD_HEIGHT = 22;
    public static final int CLOUD_QUANTITY = 50;
    //------------------
    //       TEST
    //------------------
    public static final int NPTEST_BORDER_PIXELS = 2;
    //------------------
    //       IDs
    //------------------
    public static final int NULL_ID = 0;
    // Defenses
    public static final int TURRET_ID = 1;
    public static final int WALL_ID = 2;
    public static final int TRAP_ID = 3;
    // Plants
    public static final int ELSKER_ID = 4;
    public static final int GRA_ID = 5;
    public static final int KOCHAM_ID = 6;
    public static final int SZERELEM_ID = 7;
    public static final int ELSKA_ID = 8;
    public static final int AYARN_ID = 9;
    public static final int SEVIYORUM_ID = 10;
    public static final int MILESTIBA_ID = 11;
    public static final int RAKKAUS_ID = 12;
    public static final int KAERLIGHED_ID = 13;
}