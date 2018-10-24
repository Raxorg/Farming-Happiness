package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static TextureRegion tree, river1, river2, river3, river4, grass, farmingCornerBottomLeft,
            farmingCornerTopLeft, farmingBottom, farmingTop, defenseTile, turret, turretCannon, wall,
            tourist, rangeCircle, turretBullet, empty, sidesLifeBar, centerLifeBar, dollar,
            centerEmptyLifeBar, triangle, trap, farmingCenter, farmingLeft, farmingRight,
            farmingCornerBottomRight, farmingCornerTopRight, resourceFrame;

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
        // Defense tile
        defenseTile = new TextureRegion(tiles, 0, 225, 100, 100);
        Texture defenses = new Texture("images/defenses.png");
        turret = new TextureRegion(defenses, 0, 0, 60, 88);
        turretCannon = new TextureRegion(defenses, 60, 0, 18, 15);
        turretBullet = new TextureRegion(defenses, 0, 0, 25, 25);
        wall = new TextureRegion(defenses, 0, 400, 120, 70);
        trap = new TextureRegion(defenses, 120, 400, 93, 62);
        Texture enemies = new Texture("images/enemies.png");
        tourist = new TextureRegion(enemies, 100, 200, 100, 200);
        Texture debug = new Texture("images/rangeCircle.png");
        rangeCircle = new TextureRegion(debug, 0, 0, 500, 500);
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
    }

}