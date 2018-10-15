package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.game.structures.Content;
import com.frontanilla.farminghappyness.game.structures.Turret;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SIZE;

public class Tile {

    private Enums.TileType type;
    private float x, y;
    private Rectangle bounds;
    private Content content;

    public Tile(Enums.TileType type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
        bounds = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        renderTile(batch);
    }

    private void renderTile(SpriteBatch batch) {
        switch (type) {
            case DEFENSIVE_TILE:
                batch.draw(
                        Assets.defenseTile,
                        x,
                        y,
                        TILE_SIZE,
                        TILE_SIZE);
                break;
            case FARMING_TILE:
                batch.draw(
                        Assets.farmingTile,
                        x,
                        y,
                        TILE_SIZE,
                        TILE_SIZE);
                break;
            case HIDDEN_TILE:
                break;
        }
    }

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
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
