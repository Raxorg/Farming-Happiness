package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.frontanilla.farminghappyness.components.MyCamera;

import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class GameConnector extends ScreenAdapter {

    private GameState gameState;
    private GameLogic gameLogic;
    private GameRenderer gameRenderer;
    private MyCamera camera;

    public GameConnector() {
        gameState = new GameState();
        gameLogic = new GameLogic(this);
        gameRenderer = new GameRenderer(this);
        // Constructs a new OrthographicCamera, using the given viewport width and height
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        // Height is multiplied by aspect ratio.
        camera = new MyCamera(WORLD_WIDTH * 0.3f, WORLD_HEIGHT * 0.3f * (h / w));
        // Detect and process user input
        GameInput gameInput = new GameInput(this);
        Gdx.input.setInputProcessor(gameInput.getInputMultiplexer());
    }

    public void render(float delta) {
        gameLogic.update(delta);
        gameRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.resize(width, height);
    }

    public MyCamera getCamera() {
        return camera;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }

    public GameState getGameState() {
        return gameState;
    }
}
