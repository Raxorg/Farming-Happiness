package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private GameMap map;

    public GameScreen() {
        batch = new SpriteBatch();
        map = new GameMap();
    }

    public void update(float delta) {

    }

    public void render(float delta) {
        update(delta);

        batch.begin();
        map.render(batch);
        batch.end();
    }
}
