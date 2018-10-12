package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.units.Enemy;
import com.frontanilla.farminghappyness.tests.MyCamera;

import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class GameScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private GameMap map;
    private MyCamera camera;
    private GameInput gameInput;
    private GameLogic gameLogic;

    public GameScreen() {
        batch = new SpriteBatch();
        map = new GameMap();

        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new MyCamera(WORLD_WIDTH * 0.3f, WORLD_HEIGHT * 0.3f * (h / w));

        // Detect and process user input
        gameInput = new GameInput(this);
        Gdx.input.setInputProcessor(gameInput);

        // The logic of the game goes here
        gameLogic = new GameLogic(this);
    }

    public void render(float delta) {
        gameLogic.update(delta);

        batch.begin();
        map.render(batch);
        for (Enemy e : gameLogic.getEnemies()) {
            e.render(batch);
        }
        for (Bullet b : gameLogic.getBullets()) {
            b.render(batch);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.resize(width, height);
    }

    public MyCamera getCamera() {
        return camera;
    }

    public Batch getBatch() {
        return batch;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public GameMap getMap() {
        return map;
    }
}
