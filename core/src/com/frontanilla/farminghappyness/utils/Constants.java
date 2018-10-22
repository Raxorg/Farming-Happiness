package com.frontanilla.farminghappyness.utils;

public class Constants {

    // World
    public static final int WORLD_WIDTH = 1000;
    public static final int WORLD_HEIGHT = 500;
    // Grids
    public static final float TILE_SIZE = 25f;
    public static final float TILE_SPACING = 5f;
    // Global Area
    public static final int GRASS_COLUMNS = WORLD_WIDTH / 100;
    public static final int GRASS_ROWS = WORLD_HEIGHT / 100;
    public static final float GRASS_WIDTH = WORLD_WIDTH / GRASS_COLUMNS;
    public static final float GRASS_HEIGHT = WORLD_HEIGHT / GRASS_ROWS;
    // River
    public static final float RIVER_TILE_SIZE = WORLD_HEIGHT / 5;
    public static final int RIVER_TILES = (int) (WORLD_WIDTH / RIVER_TILE_SIZE + 1);
    // Defense Area
    public static final float DEFENSE_AREA_WIDTH = WORLD_WIDTH * (2f / 3f);
    public static final int DEFENSE_AREA_COLUMNS = (int) (DEFENSE_AREA_WIDTH / (TILE_SIZE + TILE_SPACING));
    public static final float DEFENSE_X_SPACE = DEFENSE_AREA_WIDTH - DEFENSE_AREA_COLUMNS * (TILE_SIZE + TILE_SPACING);
    public static final float DEFENSE_AREA_X = WORLD_WIDTH - DEFENSE_AREA_WIDTH + DEFENSE_X_SPACE;

    public static final float DEFENSE_AREA_HEIGHT = WORLD_HEIGHT / 2f;
    public static final int DEFENSE_AREA_ROWS = (int) (DEFENSE_AREA_HEIGHT / (TILE_SIZE + TILE_SPACING));
    public static final float DEFENSE_Y_SPACE = DEFENSE_AREA_HEIGHT - DEFENSE_AREA_ROWS * (TILE_SIZE + TILE_SPACING);
    public static final float DEFENSE_AREA_Y = WORLD_HEIGHT - DEFENSE_AREA_HEIGHT + DEFENSE_Y_SPACE - RIVER_TILE_SIZE;

    public static final int DEFENSE_AREA_DEFENSE_LINES = 4;
    public static final float DEFENSE_AREA_THICKNESS = DEFENSE_AREA_DEFENSE_LINES * (TILE_SIZE + TILE_SPACING);
    // Farming Area
    public static final float FARMING_AREA_WIDTH = (DEFENSE_AREA_WIDTH - DEFENSE_AREA_THICKNESS) / 2f;
    public static final int FARMING_AREA_COLUMNS = (int) (FARMING_AREA_WIDTH / TILE_SIZE);
    public static final float FARMING_X_SPACE = FARMING_AREA_WIDTH - FARMING_AREA_COLUMNS * TILE_SIZE;
    public static final float FARMING_AREA_X = WORLD_WIDTH - FARMING_AREA_WIDTH + FARMING_X_SPACE;

    public static final float FARMING_AREA_HEIGHT = DEFENSE_AREA_HEIGHT - DEFENSE_AREA_THICKNESS;
    public static final int FARMING_AREA_ROWS = (int) (FARMING_AREA_HEIGHT / TILE_SIZE);
    public static final float FARMING_Y_SPACE = FARMING_AREA_HEIGHT - FARMING_AREA_ROWS * TILE_SIZE;
    public static final float FARMING_AREA_Y = WORLD_HEIGHT - FARMING_AREA_HEIGHT + FARMING_Y_SPACE - RIVER_TILE_SIZE;
    // Camera
    public static final float PANNING_SPEED = 10f;
    // Turret
    public static final float TURRET_WIDTH = 40f;
    public static final float TURRET_HEIGHT = 50f;
    public static final float TURRET_RANGE = 100f;
    public static final float TURRET_COOL_DOWN = 0.5f;
    // Turret Bullet
    public static final float TURRET_BULLET_SIZE = TILE_SIZE / 10;
    public static final float TURRET_BULLET_SPEED = 250f;
    // WALL
    public static final float WALL_SIZE = TILE_SIZE;
    // Enemies
    public static final float ENEMY_WIDTH = 10f;
    public static final float ENEMY_HEIGHT = ENEMY_WIDTH * 2f;
    public static final float SPAWN_RATE = 0.15f;
    // Tourists
    public static final int TOURIST_INITIAL_LIFE = 5;
    public static final float TOURIST_SPEED = 20f;
    // Life bars
    public static final float ENEMY_LIFE_BAR_HEIGHT = 3f;
    public static final float ENEMY_LIFE_BAR_SIDES_WIDTH = (ENEMY_LIFE_BAR_HEIGHT / 10f);
}