package com.frontanilla.farminghappyness.visualization.screens.game;

import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.visualization.screens.Connector;

public class GameConnector extends Connector {

    public GameConnector(FarmingGame game) {
        //firestore = new GameFirestore(this);
        screen = new GameScreen(this, game);
        stuff = new GameStuff(this);
        logic = new GameLogic(this);
        renderer = new GameRenderer(this);
        input = new GameInput(this);

        // Init screen camera
        screen.init();
        // Init stuff based on camera dimensions
        stuff.beforeInit();
        stuff.init();
        // Init input based on stuff buttons and components
        input.init();
        // Init logic based on input, stuff and screen data
        logic.init();
        // Init renderer batches and renderers
        renderer.init();
        // Show screen once everything is initialized, with a fader todo
        game.setScreen(screen);
    }
}
