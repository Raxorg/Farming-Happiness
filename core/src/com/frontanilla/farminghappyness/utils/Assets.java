package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static TextureRegion tourist, rangeCircle, barSides, centerLifeBar, dollar, centerEmptyLifeBar, triangle, resourceFrame;
    //-------------------
    //      PLANTS
    //-------------------
    public static TextureRegion elsker, gra, kocham, szerelem, elska, ayarn, seviyorum, milestiba, rakkaus, kaerlighed;
    //------------------
    //     TERRAIN
    //------------------
    public static TextureRegion grassTile, farmlandTile;
    //------------------
    //      RIVER
    //------------------
    public static Animation<TextureRegion> riverAnimation;
    //------------------
    //     ENEMIES
    //------------------
    public static Animation<TextureRegion> touristAnimation;
    // Borders
    public static TextureRegion bordersTopLeft, bordersTopRight, bordersBottomLeft, bordersBottomRight, bordersLeft, bordersTop, bordersRight,
            bordersBottom;
    //------------------
    //     DEFENSES
    //------------------
    public static TextureRegion turret, turretBullet, wall, trap;
    //-------------------
    //     GAME MENU
    //-------------------
    public static TextureRegion testPanel;
    // Decoration
    public static TextureRegion grass1, grass1Glow;
    // Misc
    public static TextureRegion pixel;
    // TODO test
    public static TextureRegion nptest;
    public static TextureRegion testFrame, testToggleButtonRight, testToggleButtonLeft;

    public static void init() {
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
        // Grass
        grassTile = new TextureRegion(terrain, 0, 0, 100, 100);
        // Farmland
        farmlandTile = new TextureRegion(terrain, 100, 0, 35, 35);
        //------------------
        //      TILES
        //------------------
        // Borders
        Texture borders = new Texture("images/borders.png");
        bordersTopLeft = new TextureRegion(borders, 0, 0, 500, 500);
        bordersTopRight = new TextureRegion(borders, 1000, 0, 500, 500);
        bordersBottomLeft = new TextureRegion(borders, 0, 1000, 500, 500);
        bordersBottomRight = new TextureRegion(borders, 1000, 1000, 500, 500);
        bordersLeft = new TextureRegion(borders, 0, 500, 500, 500);
        bordersTop = new TextureRegion(borders, 500, 0, 500, 500);
        bordersRight = new TextureRegion(borders, 1000, 500, 500, 500);
        bordersBottom = new TextureRegion(borders, 500, 1000, 500, 500);
        //------------------
        //     DEFENSES
        //------------------
        Texture defenses = new Texture("images/defenses.png");
        // Turret
        turret = new TextureRegion(defenses, 0, 0, 40, 57);
        turretBullet = new TextureRegion(defenses, 0, 0, 10, 10);
        // Wall
        wall = new TextureRegion(defenses, 40, 0, 42, 50);
        // Trap
        trap = new TextureRegion(defenses, 82, 0, 42, 50);
        Texture debug = new Texture("images/rangeCircle.png");
        rangeCircle = new TextureRegion(debug, 0, 0, 500, 500);
        //------------------
        //    DECORATION
        //------------------
        Texture decor = new Texture("images/decor.png");
        grass1 = new TextureRegion(decor, 0, 0, 100, 100);
        grass1Glow = new TextureRegion(decor, 100, 0, 100, 100);
        //------------------
        //     ENEMIES
        //------------------
        Texture enemies = new Texture("animations/stickFigureRunning.png");
        touristAnimation = new Animation<>(1f / 60f, Util.regions(enemies, 180, 340, 2));
        touristAnimation.setPlayMode(Animation.PlayMode.LOOP);
        tourist = new TextureRegion(enemies, 0, 0, 594, 1135);
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
        testPanel = new TextureRegion(new Texture("images/testPanel.png"), 0, 0, 3, 3);
        // Toggle buttons
        Texture toggleButtons = new Texture("images/testToggleButtons.png");
        testToggleButtonRight = new TextureRegion(toggleButtons, 0, 0, 50, 50);
        testToggleButtonLeft = new TextureRegion(toggleButtons, 50, 0, 50, 50);
    }

}