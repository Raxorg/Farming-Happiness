package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.game.entities.GameEntity;
import com.frontanilla.farminghappyness.utils.Enums.TileType;

public class ButtonTile extends Button {

    private TileType type;
    private GameEntity gameEntity;

    public ButtonTile(TileType type, TextureRegion textureRegion, float x, float y, float w, float h) {
        super(textureRegion, x, y, w, h);
        this.type = type;
    }

    public GameEntity getGameEntity() {
        return gameEntity;
    }

    public void setGameEntity(GameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

    public TileType getType() {
        return type;
    }
}
