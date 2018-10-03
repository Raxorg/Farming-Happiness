package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.tests.MyCamera;

import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class GameScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private GameMap map;
    private MyCamera camera;

    public GameScreen() {
        batch = new SpriteBatch();
        map = new GameMap();

        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new MyCamera(WORLD_WIDTH * 0.3f, WORLD_HEIGHT * 0.3f * (h / w));
    }

    public void update(float delta) {
        camera.handleInput();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void render(float delta) {
        update(delta);

        batch.begin();
        map.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.resize(width, height);
    }
}
