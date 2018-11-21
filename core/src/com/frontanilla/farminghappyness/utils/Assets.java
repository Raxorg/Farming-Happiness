package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.frontanilla.farminghappyness.utils.Constants.FARM_BASE_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.FARM_BASE_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.LABORATORY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.LABORATORY_WIDTH;

public class Assets {
    //-------------------
    //     MAIN MENU
    //-------------------
    public static TextureRegion playButton, creditsButton, mainMenuBackground;
    //-------------------
    //     BUILDINGS
    //-------------------
    public static TextureRegion farmBase, laboratory;
    //-------------------
    //      PLANTS
    //-------------------
    public static TextureRegion elsker, gra, kocham, szerelem, elska, ayarn, seviyorum, milestiba, rakkaus, kaerlighed;
    //------------------
    //     TERRAIN
    //------------------
    public static TextureRegion pathTile, grassTile, farmlandTile;
    //------------------
    //      RIVER
    //------------------
    public static Animation<TextureRegion> riverAnimation;
    //------------------
    //     ENEMIES
    //------------------
    // Tourist
    public static Animation<TextureRegion> enemyAnimation;
    public static TextureRegion touristHat, policeHat, militaryHat, alienHat;
    //------------------
    //     DEFENSES
    //------------------
    public static TextureRegion turret, turretBullet, wall, mine;
    public static Animation<TextureRegion> mineAnimation;
    //-------------------
    //     GAME MENU
    //-------------------
    public static TextureRegion toggleMenuPanel;
    //--------------------
    //     DECORATION
    //--------------------
    public static TextureRegion tree, appleTree, flowerTree, trunk, treeShadow;
    // Misc
    public static TextureRegion pixel;
    // TODO test
    public static TextureRegion nptest;
    public static TextureRegion testFrame, testToggleButtonRight, testToggleButtonLeft;
    //-------------------
    //       DEBUG
    //-------------------
    public static TextureRegion rangeCircle, barSides, centerLifeBar, dollar, centerEmptyLifeBar, triangle, resourceFrame;

    public static void init() {
        //-------------------
        //     MAIN MENU
        //-------------------
        Texture mainMenuButtons = new Texture("images/mainMenuButtons.png");
        playButton = new TextureRegion(mainMenuButtons, 0, 0, 99, 36);
        creditsButton = new TextureRegion(mainMenuButtons, 0, 36, 100, 38);
        mainMenuBackground = new TextureRegion(new Texture("images/mainMenuBG.png"));
        //-------------------
        //     BUILDINGS
        //-------------------
        Texture buildings = new Texture("images/buildings.png");
        farmBase = new TextureRegion(buildings, 0, 0, FARM_BASE_WIDTH, FARM_BASE_HEIGHT);
        laboratory = new TextureRegion(buildings, 0, FARM_BASE_HEIGHT, LABORATORY_WIDTH, LABORATORY_HEIGHT);
        //-------------------
        //      PLANTS
        //-------------------
        Texture plants = new Texture("images/plants.png");
        elsker = new TextureRegion(plants, 0, 0, 35, 35);
        gra = new TextureRegion(plants, 35, 0, 35, 35);
        kocham = new TextureRegion(plants, 70, 0, 35, 35);
        szerelem = new TextureRegion(plants, 105, 0, 35, 35);
        elska = new TextureRegion(plants, 140, 0, 35, 38);
        ayarn = new TextureRegion(plants, 0, 35, 35, 42);
        seviyorum = new TextureRegion(plants, 35, 35, 35, 42);
        milestiba = new TextureRegion(plants, 70, 35, 35, 49);
        rakkaus = new TextureRegion(plants, 105, 35, 35, 49);
        kaerlighed = new TextureRegion(plants, 140, 35, 35, 54);
        //------------------
        //     TERRAIN
        //------------------
        Texture terrain = new Texture("images/terrain.png");
        // Path
        pathTile = new TextureRegion(terrain, 100, 35, 20, 20);
        // Grass
        grassTile = new TextureRegion(terrain, 0, 0, 100, 100);
        // Farmland
        farmlandTile = new TextureRegion(terrain, 100, 0, 35, 35);
        //------------------
        //     DEFENSES
        //------------------
        Texture defenses = new Texture("images/defenses.png");
        // Turret
        turret = new TextureRegion(defenses, 0, 0, 40, 57);
        turretBullet = new TextureRegion(defenses, 0, 0, 10, 10);
        // Wall
        wall = new TextureRegion(defenses, 40, 0, 42, 50);
        // Mine
        mine = new TextureRegion(defenses, 82, 0, 40, 40);
        Texture mines = new Texture("animations/mineStates.png");
        mineAnimation = new Animation<>(0.5f, Util.regions(mines, 40, 40, 0));
        mineAnimation.setPlayMode(Animation.PlayMode.LOOP);
        // Debug
        Texture debug = new Texture("images/rangeCircle.png");
        rangeCircle = new TextureRegion(debug, 0, 0, 500, 500);
        //------------------
        //    DECORATION
        //------------------
        Texture decoration = new Texture("images/decoration2.png");
        tree = new TextureRegion(decoration, 0, 0, 35, 52);
        appleTree = new TextureRegion(decoration, 35, 0, 35, 52);
        flowerTree = new TextureRegion(decoration, 70, 0, 35, 52);
        trunk = new TextureRegion(decoration, 0, 52, 35, 52);
        treeShadow = new TextureRegion(decoration, 35, 52, 35, 52);
        //------------------
        //     ENEMIES
        //------------------
        Texture enemies = new Texture("animations/stickFigureRunning.png");
        enemyAnimation = new Animation<>(1f / 60f, Util.regions(enemies, 180, 367, 2));
        enemyAnimation.setPlayMode(Animation.PlayMode.LOOP);
        // Tourist
        touristHat = new TextureRegion(new Texture("images/hats.png"), 0, 0, 180, 367);
        // Police
        policeHat = new TextureRegion(new Texture("images/hats.png"), 180, 0, 180, 367);
        // Military
        militaryHat = new TextureRegion(new Texture("images/hats.png"), 360, 0, 180, 367);
        // Alien
        alienHat = new TextureRegion(new Texture("images/hats.png"), 540, 0, 180, 367);
        //------------------
        //      RIVER
        //------------------
        Texture river = new Texture("animations/river.png");
        riverAnimation = new Animation<>(1f / 3f, Util.regions(river, 100, 100, 0));
        riverAnimation.setPlayMode(Animation.PlayMode.LOOP);
        //------------------
        //       HUD
        //------------------
        Texture hud = new Texture("images/hud.png");
        // Life bar
        barSides = new TextureRegion(hud, 0, 0, 20, 100);
        centerLifeBar = new TextureRegion(hud, 55, 0, 35, 100);
        centerEmptyLifeBar = new TextureRegion(hud, 20, 0, 35, 100);
        // Construction menu
        triangle = new TextureRegion(hud, 90, 0, 200, 100);
        // Resource frame
        resourceFrame = new TextureRegion(hud, 290, 0, 300, 100);
        // Dollar
        dollar = new TextureRegion(hud, 591, 0, 54, 41); // TODO WHY +1
        //------------------
        //       MISC
        //------------------
        pixel = new TextureRegion(new Texture("images/pixel.png"), 0, 0, 1, 1);
        // TODO TEST
        nptest = new TextureRegion(new Texture("images/nptest.png"), 0, 0, 10, 10);
        testFrame = new TextureRegion(new Texture("images/testFrame.png"), 0, 0, 100, 100);
        toggleMenuPanel = new TextureRegion(new Texture("images/testPanel.png"), 0, 0, 3, 3);
        // Toggle buttons
        Texture toggleButtons = new Texture("images/testToggleButtons.png");
        testToggleButtonRight = new TextureRegion(toggleButtons, 0, 0, 50, 50);
        testToggleButtonLeft = new TextureRegion(toggleButtons, 50, 0, 50, 50);
    }

}