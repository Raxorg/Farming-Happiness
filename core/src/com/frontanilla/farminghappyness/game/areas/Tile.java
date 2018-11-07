package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.other.Button;
import com.frontanilla.farminghappyness.game.other.NinePatchButton;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SIZE;

public class Tile extends NinePatchButton {

    private Enums.TileType type;
    private Defense defense;

    public Tile(Enums.TileType type, NinePatch ninePatch, float x, float y) {
        super(Assets.defenseTilePatch, x, y, TILE_SIZE, TILE_SIZE);
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

    public float getX() {
        return bounds.x;
    }

    public void setX(float x) {
        bounds.x = x;
    }

    public float getY() {
        return bounds.y;
    }

    public void setY(float y) {
        bounds.y = y;
    }

    public Enums.TileType getType() {
        return type;
    }
}
