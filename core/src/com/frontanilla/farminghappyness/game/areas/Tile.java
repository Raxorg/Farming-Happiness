package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.components.NinePatcherButton;
import com.frontanilla.farminghappyness.utils.Enums;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SIZE;

public class Tile extends NinePatcherButton {

    private Enums.TileType type;
    private Defense defense;

    public Tile(Enums.TileType type, TextureRegion textureRegion, float borderSize, int borderPixels, float x, float y) {
        super(textureRegion, borderSize, borderPixels, x, y, TILE_SIZE, TILE_SIZE);
        this.type = type;
    }

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }

    public Defense getDefense() {
        return defense;
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }

    public Enums.TileType getType() {
        return type;
    }
}
