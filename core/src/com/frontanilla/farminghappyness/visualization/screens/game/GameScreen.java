package com.frontanilla.farminghappyness.visualization.screens.game;

import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.visualization.DynamicCamera;
import com.frontanilla.farminghappyness.visualization.screens.Connector;
import com.frontanilla.farminghappyness.visualization.screens.MyScreen;

public class GameScreen extends MyScreen {

    public GameScreen(GameConnector connector, FarmingGame game) {
        super(connector, game);
    }

    @Override
    public void init() {
        camera = new DynamicCamera();
    }
}
