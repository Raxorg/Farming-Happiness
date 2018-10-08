package com.frontanilla.farminghappyness.utils;

public class Constants {

    // World
    public static final int WORLD_WIDTH = 1000;
    public static final int WORLD_HEIGHT = 500;
    // Grids
    public static final float TILE_SIZE = 25f;
    // Global Area
    public static final int GRASS_COLUMNS = WORLD_WIDTH / 100;
    public static final int GRASS_ROWS = WORLD_HEIGHT / 100;
    public static final float GRASS_WIDTH = WORLD_WIDTH / GRASS_COLUMNS;
    public static final float GRASS_HEIGHT = WORLD_HEIGHT / GRASS_ROWS;
    // Defense Area
    public static final float DEFENSE_AREA_WIDTH = WORLD_WIDTH * (2f / 3f);
    public static final int DEFENSE_AREA_COLUMNS = (int) (DEFENSE_AREA_WIDTH / TILE_SIZE);
    public static final float X_SPACE = DEFENSE_AREA_WIDTH - DEFENSE_AREA_COLUMNS * TILE_SIZE;
    public static final float DEFENSE_AREA_X = WORLD_WIDTH - DEFENSE_AREA_WIDTH + X_SPACE;

    public static final float DEFENSE_AREA_HEIGHT = WORLD_HEIGHT / 2f;
    public static final int DEFENSE_AREA_ROWS = (int) (DEFENSE_AREA_HEIGHT / TILE_SIZE);
    public static final float DEFENSE_AREA_Y = WORLD_HEIGHT - DEFENSE_AREA_HEIGHT;

    public static final int DEFENSE_AREA_DEFENSE_LINES = 4;
    public static final float DEFENSE_AREA_THICKNESS = DEFENSE_AREA_DEFENSE_LINES * TILE_SIZE;
    // Farming Area
    public static final float FARMING_AREA_WIDTH = (DEFENSE_AREA_WIDTH - DEFENSE_AREA_THICKNESS) / 2f;
    public static final int FARMING_AREA_COLUMNS = (int) (FARMING_AREA_WIDTH / TILE_SIZE);
    public static final float FARMING_X_SPACE = FARMING_AREA_WIDTH - FARMING_AREA_COLUMNS * TILE_SIZE;
    public static final float FARMING_AREA_X = WORLD_WIDTH - FARMING_AREA_WIDTH + FARMING_X_SPACE;
    public static final float FARMING_AREA_HEIGHT = DEFENSE_AREA_HEIGHT - DEFENSE_AREA_THICKNESS;
    public static final int FARMING_AREA_ROWS = (int) (FARMING_AREA_HEIGHT / TILE_SIZE);
    public static final float FARMING_AREA_Y = WORLD_HEIGHT - FARMING_AREA_HEIGHT;
    // Camera
    public static final float PANNING_SPEED = 10f;
    // Turret
    public static final float TURRET_SIZE = 20f;
    public static final float TURRET_RANGE = 200f;
    // Enemies
    public static final float ENEMY_WIDTH = 15f;
    public static final float ENEMY_HEIGHT = 30f;
    // Tourists
    public static final float TOURIST_SPEED = 20f;
}