package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.areas.GridArea;
import com.frontanilla.farminghappyness.game.areas.NormalArea;

public class GameMap {

    private GridArea plantsArea;
    private GridArea defenseArea;
    private NormalArea normalArea;

    public GameMap() {
        plantsArea = new GridArea();
        defenseArea = new GridArea();
        normalArea = new NormalArea();
    }

    public void render(SpriteBatch batch) {
        plantsArea.render(batch);
        defenseArea.render(batch);
        normalArea.render(batch);
    }
}
