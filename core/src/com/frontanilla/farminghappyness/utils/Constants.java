package com.frontanilla.farminghappyness.utils;

public class Constants {

    // Loading
    public static final String LOADING_TEXT = "Loading";
    public static final float LOADING_LABEL_TEXT_SCALE = 2;
    // Main menu
    public static final String MAIN_MENU_TITLE = "Main menu";
    public static final String INITIAL_MENU_MESSAGE = "Checking internet...";
    // Costs
    public static final int WALL_COST = 2;
    public static final int TANK_COST = 3;
    // Healths
    public static final int WALL_INITIAL_HEALTH = 3;
    public static final int TANK_INITIAL_HEALTH = 1;
    public static final int TANK_BULLET_INITIAL_HEALTH = 1;
    public static final int BASE_INITIAL_HEALTH = 3;
    // Representations
    public static final int WALL_REPRESENTATION = 1;
    public static final int TANK_REPRESENTATION = 2;
    public static final int BASE_REPRESENTATION = 3;
    // Cells
    public static final float CELL_SIZE = 50;
    public static final float CELL_SPACING = 5;
    // Grid
    public static final int GRID_ROWS = 20;
    public static final int GRID_COLUMNS = 40;
    public static final float GRID_WIDTH = CELL_SIZE * GRID_COLUMNS + CELL_SPACING * (GRID_COLUMNS - 1);
    public static final float GRID_HEIGHT = CELL_SIZE * GRID_ROWS + CELL_SPACING * (GRID_ROWS - 1);
    public static final float GRID_BORDER = 50;
    // Game camera
    public static final float INITIAL_ZOOM = 0.5f;
    public static final float CAMERA_HEIGHT = 1000;
    // Bullets
    public static final float BULLET_SPEED = 150;
    public static final float TANK_BULLET_WIDTH = 10;
    public static final float TANK_BULLET_HEIGHT = 10;
    // Size
    public static final float FONT_SIZE = 70;
    public static final float PIVOT_WIDTH = 2560;
    public static final float PIVOT_HEIGHT = 1600;
    // Fader
    public static final float FADING_SPEED = 1.5f;
    // FancyBG
    public static final float FANCY_BG_SPEED = 50;
    // Logic
    public static final int FETCHING_INTERVAL = 5;
    // Game screen
    public static final float GAME_SCREEN_WIDTH = GRID_WIDTH + GRID_BORDER * 2;
    public static final float GAME_SCREEN_HEIGHT = GRID_HEIGHT + GRID_BORDER * 2;
}