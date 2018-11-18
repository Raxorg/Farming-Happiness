package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.components.NinePatcherButton;
import com.frontanilla.farminghappyness.game.GameEntity;
import com.frontanilla.farminghappyness.utils.Enums;

public class Tile extends NinePatcherButton {

    private Enums.TileType type;
    private GameEntity gameEntity;

    public Tile(Enums.TileType type, TextureRegion textureRegion, float borderSize, int borderPixels, float x, float y, float width, float height) {
        super(textureRegion, borderSize, borderPixels, x, y, width, height);
        this.type = type;
    }

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }

    public GameEntity getGameEntity() {
        return gameEntity;
    }

    public void setGameEntity(GameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

    public Enums.TileType getType() {
        return type;
    }
}
