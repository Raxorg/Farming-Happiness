package com.frontanilla.farminghappyness.visualization.screens.mainmenu;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.visualization.DynamicCamera;
import com.frontanilla.farminghappyness.visualization.screens.MyAdvancedScreen;

public class MainMenuScreen extends MyAdvancedScreen {

    public MainMenuScreen(MainMenuConnector connector, FarmingGame game) {
        super(connector, game);
    }

    public void joinBattle() {
        // new GameConnector(game);
    }

    public void onBattleDataFetched(String[] gridRows, int turn, DelayedRemovalArray<Player> players) {
        // fade(new GameConnector(game).getScreen(), false);
    }

    @Override
    public void init() {
        camera = new DynamicCamera();
    }
}
