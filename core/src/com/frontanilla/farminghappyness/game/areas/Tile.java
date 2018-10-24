package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.utils.Enums;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SIZE;

public class Tile {

    private Enums.TileType type;
    private float x, y;
    private Rectangle bounds;
    private Defense defense;
    private TextureRegion texture;

    public Tile(Enums.TileType type, TextureRegion texture, float x, float y) {
        this.type = type;
        this.texture = texture;
        this.x = x;
        this.y = y;
        bounds = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                texture,
                x,
                y,
                TILE_SIZE,
                TILE_SIZE);
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
        return x;
    }

    public float getY() {
        return y;
    }

    public Enums.TileType getType() {
        return type;
    }
}
