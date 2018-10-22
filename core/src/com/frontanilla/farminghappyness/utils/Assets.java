package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static TextureRegion tree, river1, river2, river3, river4, farmingTile, grass,
            defenseTile, turret, turretCannon, wall, tourist, rangeCircle, turretBullet,
            sidesLifeBar, centerLifeBar, centerEmptyLifeBar, triangle;

    public static void init() {
        Texture decoration = new Texture("images/decoration.png");
        tree = new TextureRegion(decoration, 0, 0, 170, 180);
        river1 = new TextureRegion(decoration, 0, 200, 141, 130);
        river2 = new TextureRegion(decoration, 141, 200, 141, 130);
        river3 = new TextureRegion(decoration, 282, 200, 141, 130);
        river4 = new TextureRegion(decoration, 423, 200, 141, 130);
        Texture tiles = new Texture("images/tiles.png");
        farmingTile = new TextureRegion(tiles, 225, 0, 150, 150);
        grass = new TextureRegion(tiles, 0, 0, 225, 225);
        defenseTile = new TextureRegion(tiles, 0, 225, 100, 100);
        Texture defenses = new Texture("images/defenses.png");
        turret = new TextureRegion(defenses, 0, 0, 400, 400);
        turretCannon = new TextureRegion(defenses, 400, 0, 400, 400);
        turretBullet = new TextureRegion(defenses, 0, 0, 25, 25);
        wall = new TextureRegion(defenses, 0, 400, 400, 400);
        Texture enemies = new Texture("images/enemies.png");
        tourist = new TextureRegion(enemies, 100, 200, 100, 200);
        Texture debug = new Texture("images/rangeCircle.png");
        rangeCircle = new TextureRegion(debug, 0, 0, 500, 500);
        Texture hud = new Texture("images/hud.png");
        sidesLifeBar = new TextureRegion(hud, 0, 0, 10, 100);
        centerLifeBar = new TextureRegion(hud, 50, 0, 40, 100);
        centerEmptyLifeBar = new TextureRegion(hud, 10, 0, 40, 100);
        triangle = new TextureRegion(hud, 90, 0, 200, 100);
    }

}