package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.components.NinePatcherButton;
import com.frontanilla.farminghappyness.game.entities.GameEntity;
import com.frontanilla.farminghappyness.utils.Enums;

public class NinePatcherTile extends NinePatcherButton {

    private Enums.TileType type;
    private GameEntity gameEntity;

    public NinePatcherTile(Enums.TileType type, TextureRegion textureRegion, float borderSize, int borderPixels, float x, float y, float w, float h) {
        super(textureRegion, borderSize, borderPixels, x, y, w, h);
        this.type = type;
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
