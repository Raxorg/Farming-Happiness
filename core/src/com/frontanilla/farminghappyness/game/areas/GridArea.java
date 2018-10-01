package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Assets;

public class GridArea {

    private Tile[][] tiles;

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                Assets.farmland,
                100,
                100);
    }
}
