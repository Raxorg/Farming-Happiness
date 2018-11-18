package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static TextureRegion tree, grass, farmingCornerBottomLeft, farmingCornerTopLeft, farmingBottom, farmingTop,
            defenseTile, wall, tourist, rangeCircle, empty, barSides, centerLifeBar, dollar, centerEmptyLifeBar, triangle, trap, farmingCenter,
            farmingLeft, farmingRight, farmingCornerBottomRight, farmingCornerTopRight, resourceFrame, trapLeaves;
    // River
    public static Animation<TextureRegion> riverAnimation;
    // Enemies
    public static Animation<TextureRegion> touristAnimation;
    // Borders
    public static TextureRegion bordersTopLeft, bordersTopRight, bordersBottomLeft, bordersBottomRight, bordersLeft, bordersTop, bordersRight,
            bordersBottom;
    // Borders Glow
    public static TextureRegion bordersTopLeftGlow, bordersTopRightGlow, bordersBottomLeftGlow, bordersBottomRightGlow, bordersLeftGlow,
            bordersTopGlow, bordersRightGlow, bordersBottomGlow;
    // Tiles
    public static NinePatch defenseTilePatch;
    // Turret
    public static TextureRegion turret, turretBullet;
    // Decoration
    public static TextureRegion grass1, grass1Glow;
    // Misc
    public static TextureRegion pixel, plantTest;
    // TODO test
    public static TextureRegion nptest;
    public static Texture grassx, person, exm;

    public static void init() {
        Texture decoration = new Texture("images/decoration.png");
        tree = new TextureRegion(decoration, 0, 0, 170, 180);
        Texture tiles = new Texture("images/tiles.png");
        // Grass tile
        grass = new TextureRegion(tiles, 0, 0, 225, 225);
        // Farming tiles
        farmingCenter = new TextureRegion(tiles, 225, 300, 150, 150);
        farmingCornerBottomLeft = new TextureRegion(tiles, 225, 0, 150, 150);
        farmingCornerTopLeft = new TextureRegion(tiles, 375, 0, 150, 150);
        farmingCornerBottomRight = new TextureRegion(tiles, 375, 0, -150, 150);
        farmingCornerTopRight = new TextureRegion(tiles, 525, 0, -150, 150);
        farmingTop = new TextureRegion(tiles, 375, 150, 150, 150);
        farmingBottom = new TextureRegion(tiles, 375, 300, 150, -150);
        farmingLeft = new TextureRegion(tiles, 225, 150, 150, 150);
        farmingRight = new TextureRegion(tiles, 375, 150, -150, 150);
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
        // Borders Glow
        Texture bordersGlow = new Texture("images/bordersGlow.png");
        bordersTopLeftGlow = new TextureRegion(bordersGlow, 0, 0, 500, 500);
        bordersTopRightGlow = new TextureRegion(bordersGlow, 1000, 0, 500, 500);
        bordersBottomLeftGlow = new TextureRegion(bordersGlow, 0, 1000, 500, 500);
        bordersBottomRightGlow = new TextureRegion(bordersGlow, 1000, 1000, 500, 500);
        bordersLeftGlow = new TextureRegion(bordersGlow, 0, 500, 500, 500);
        bordersTopGlow = new TextureRegion(bordersGlow, 500, 0, 500, 500);
        bordersRightGlow = new TextureRegion(bordersGlow, 1000, 500, 500, 500);
        bordersBottomGlow = new TextureRegion(bordersGlow, 500, 1000, 500, 500);
        //------------------
        //      TURRET
        //------------------
        Texture defenses = new Texture("images/defenses.png");
        turret = new TextureRegion(defenses, 0, 0, 40, 60);
        turretBullet = new TextureRegion(defenses, 0, 0, 10, 10);
        //------------------
        //     DEFENSES
        //------------------
        defenseTile = new TextureRegion(tiles, 0, 225, 100, 100);
        defenseTilePatch = new NinePatch(borders, 54, 54, 54, 54);
        wall = new TextureRegion(defenses, 0, 400, 120, 70);
        trap = new TextureRegion(defenses, 120, 400, 100, 83);
        trapLeaves = new TextureRegion(defenses, 220, 400, 100, 83);
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

        empty = new TextureRegion(tiles, 100, 225, 1, 1);
        //------------------
        //      PLANTS
        //------------------
        plantTest = new TextureRegion(new Texture("images/plant.png"), 0, 0, 100, 100);
        //------------------
        //       MISC
        //------------------
        pixel = new TextureRegion(new Texture("images/pixel.png"), 0, 0, 1, 1);
        // TODO TEST
        nptest = new TextureRegion(new Texture("images/nptest.png"), 0, 0, 10, 10);
        grassx = new Texture("images/grass.png");
        person = new Texture("images/person.png");
        exm = new Texture("images/exm.png");
    }

}