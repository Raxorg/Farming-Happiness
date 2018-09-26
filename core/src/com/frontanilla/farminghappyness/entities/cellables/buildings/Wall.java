package com.frontanilla.farminghappyness.entities.cellables.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.grid.Cell;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.CELL_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_REPRESENTATION;

public class Wall extends Building {

    public Wall(Player player, Cell cell, int health, float rotation) {
        super(player, cell, health, CELL_SIZE, CELL_SIZE, rotation, WALL_REPRESENTATION);
    }

    @Override
    public TextureRegion textureOfHealth() {
        switch (health) {
            case 1:
                return Assets.instance.gameAssets.wall1;
            case 2:
                return Assets.instance.gameAssets.wall2;
            case 3:
                return Assets.instance.gameAssets.wall3;
            default:
                return null;
        }
    }

    @Override
    public void update(float delta) {
        // Nothing happens by default
    }
}
