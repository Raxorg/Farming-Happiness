package com.frontanilla.farminghappyness.visualization.screens.loading;

import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.visualization.DynamicCamera;
import com.frontanilla.farminghappyness.visualization.screens.MyAdvancedScreen;
import com.frontanilla.farminghappyness.visualization.screens.mainmenu.MainMenuConnector;

public class LoadingScreen extends MyAdvancedScreen {

    public LoadingScreen(LoadingConnector connector, FarmingGame game) {
        super(connector, game);
    }

    public void enterMainMenu() {
        new MainMenuConnector(game);
    }

    @Override
    public void init() {
        camera = new DynamicCamera();
    }
}
