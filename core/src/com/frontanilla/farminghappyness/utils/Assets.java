package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static TextureRegion tree, river1, river2, river3, river4, farmland,
            grass;

    public static void init() {
        Texture decoration = new Texture("images/decoration.png");
        tree = new TextureRegion(decoration, 0, 0, 170, 180);
        river1 = new TextureRegion(decoration, 0, 180, 141, 170);
        river2 = new TextureRegion(decoration, 141, 180, 141, 170);
        river3 = new TextureRegion(decoration, 282, 180, 141, 170);
        river4 = new TextureRegion(decoration, 423, 180, 141, 170);
        Texture tiles = new Texture("images/tiles.png");
        farmland = new TextureRegion(tiles, 200, 0, 150, 150);
        grass = new TextureRegion(tiles, 0, 0, 200, 200);
    }

}