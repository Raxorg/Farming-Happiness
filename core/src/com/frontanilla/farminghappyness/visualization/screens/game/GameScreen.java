package com.frontanilla.farminghappyness.visualization.screens.game;

import com.badlogic.gdx.math.Vector2;
import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.visualization.DynamicCamera;
import com.frontanilla.farminghappyness.visualization.screens.MyScreen;

public class GameScreen extends MyScreen {

    public GameScreen(GameConnector connector, FarmingGame game) {
        super(connector, game);
    }

    @Override
    public void init() {
        camera = new DynamicCamera(
                Constants.GAME_SCREEN_WIDTH,
                Constants.GAME_SCREEN_HEIGHT,
                0.5f,
                true,
                new Vector2()
        );
    }
}
