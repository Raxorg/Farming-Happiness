package com.frontanilla.farminghappyness.utils;

public class Constants {

    // World
    public static final int WORLD_WIDTH = 1000;
    public static final int WORLD_HEIGHT = 500;
    // Grids
    public static final float TILE_SIZE = 25f;
    // Defense Area
    public static final float DEFENSE_AREA_WIDTH = WORLD_WIDTH * (2f / 3f);
    public static final float DEFENSE_AREA_THICKNESS = 4 * TILE_SIZE;
    public static final float DEFENSE_AREA_HEIGHT = WORLD_HEIGHT / 2f;
    // Farming Area
    public static final float FARMING_AREA_WIDTH = (DEFENSE_AREA_WIDTH - DEFENSE_AREA_THICKNESS) / 2f;
    public static final float FARMING_AREA_HEIGHT = WORLD_HEIGHT - DEFENSE_AREA_HEIGHT;
    // Camera
    public static final float PANNING_SPEED = 10f;
    // Turret
    public static final float TURRET_SIZE = 20f;
}