package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static TextureRegion tree, river1, river2, river3, river4, grass, farmingCornerBottomLeft, farmingCornerTopLeft, farmingBottom, farmingTop,
            defenseTile, wall, tourist, rangeCircle, empty, sidesLifeBar, centerLifeBar, dollar, centerEmptyLifeBar, triangle, trap, farmingCenter,
            farmingLeft, farmingRight, farmingCornerBottomRight, farmingCornerTopRight, resourceFrame, trapLeaves;
    // Borders
    public static TextureRegion bordersTopLeft, bordersTopRight, bordersBottomLeft, bordersBottomRight, bordersLeft, bordersTop, bordersRight,
            bordersBottom;
    // Borders Glow
    public static TextureRegion bordersTopLeftGlow, bordersTopRightGlow, bordersBottomLeftGlow, bordersBottomRightGlow, bordersLeftGlow,
            bordersTopGlow, bordersRightGlow, bordersBottomGlow;
    // Turret
    public static TextureRegion turret, turretGlow, turretCannon, turretCannonGlow, turretBullet, turretBulletGlow;
    // Decoration
    public static TextureRegion grass1, grass1Glow;
    // Misc
    public static TextureRegion pixel;

    public static void init() {
        Texture decoration = new Texture("images/decoration.png");
        tree = new TextureRegion(decoration, 0, 0, 170, 180);
        river1 = new TextureRegion(decoration, 0, 200, 141, 130);
        river2 = new TextureRegion(decoration, 141, 200, 141, 130);
        river3 = new TextureRegion(decoration, 282, 200, 141, 130);
        river4 = new TextureRegion(decoration, 423, 200, 141, 130);
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
        Texture turretTex = new Texture("images/turret.png");
        turret = new TextureRegion(turretTex, 0, 0, 400, 560);
        turretGlow = new TextureRegion(turretTex, 400, 0, 400, 560);
        turretCannon = new TextureRegion(turretTex, 0, 560, 228, 228);
        turretCannonGlow = new TextureRegion(turretTex, 228, 0, 228, 228);
        turretBullet = new TextureRegion(turretTex, 456, 0, 115, 60);
        turretBulletGlow = new TextureRegion(turretTex, 571, 0, 115, 60);
        //------------------
        //     DEFENSES
        //------------------
        Texture defenses = new Texture("images/defenses.png");
        defenseTile = new TextureRegion(tiles, 0, 225, 100, 100);
        wall = new TextureRegion(defenses, 0, 400, 120, 70);
        trap = new TextureRegion(defenses, 120, 400, 100, 83);
        trapLeaves = new TextureRegion(defenses, 220, 400, 100, 83);
        Texture enemies = new Texture("images/enemies.png");
        tourist = new TextureRegion(enemies, 0, 0, 594, 1135);
        Texture debug = new Texture("images/rangeCircle.png");
        rangeCircle = new TextureRegion(debug, 0, 0, 500, 500);
        //------------------
        //    DECORATION
        //------------------
        Texture decor = new Texture("images/decor.png");
        grass1 = new TextureRegion(decor, 0, 0, 100, 100);
        grass1Glow = new TextureRegion(decor, 100, 0, 100, 100);
        //------------------
        //       HUD
        //------------------
        Texture hud = new Texture("images/hud.png");
        // Life bar
        sidesLifeBar = new TextureRegion(hud, 0, 0, 10, 100);
        centerLifeBar = new TextureRegion(hud, 50, 0, 40, 100);
        centerEmptyLifeBar = new TextureRegion(hud, 10, 0, 40, 100);
        // Construction menu
        triangle = new TextureRegion(hud, 90, 0, 200, 100);
        // Resource frame
        resourceFrame = new TextureRegion(hud, 290, 0, 300, 100);
        // Dollar
        dollar = new TextureRegion(hud, 591, 0, 54, 41); // TODO WHY +1

        empty = new TextureRegion(tiles, 100, 225, 1, 1);
        //------------------
        //       MISC
        //------------------
        pixel = new TextureRegion(new Texture("images/pixel.png"), 0, 0, 1, 1);
    }

}