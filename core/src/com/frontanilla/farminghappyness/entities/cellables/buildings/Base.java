package com.frontanilla.farminghappyness.entities.cellables.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.grid.Cell;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Constants;

import static com.frontanilla.farminghappyness.utils.Constants.CELL_SIZE;

public class Base extends Building {

    public Base(Player player, Cell cell, int health, float rotation) {
        super(player, cell, health, CELL_SIZE, CELL_SIZE, rotation, Constants.BASE_REPRESENTATION);
    }

    @Override
    public TextureRegion textureOfHealth() {
        switch (health) {
            case 1:
                return Assets.instance.gameAssets.base1;
            case 2:
                return Assets.instance.gameAssets.base2;
            case 3:
                return Assets.instance.gameAssets.base3;
            default:
                return null;
        }
    }

    @Override
    public void update(float delta) {
        // Nothing happens by default
    }
}