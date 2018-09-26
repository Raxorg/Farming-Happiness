package com.frontanilla.farminghappyness.visualization.screens.loading;

import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.visualization.screens.Connector;

public class LoadingConnector extends Connector {

    public LoadingConnector(FarmingGame game) {
        //firestore = new LoadingFirestore(this);
        screen = new LoadingScreen(this, game);
        stuff = new LoadingStuff(this);
        observer = new LoadingObserver(this);
        renderer = new LoadingRenderer(this);
        input = new LoadingInput(this);

        // Init screen camera
        screen.init();
        // Init stuff based on camera dimensions
        stuff.beforeInit();
        stuff.init();
        // Init input based on stuff buttons and components
        input.init();
        // Init logic based on input, stuff and screen data
        observer.init();
        // Init renderer batches and renderers
        renderer.init();
        // Show screen once everything is initialized, with a fader todo
        game.setScreen(screen);
    }
}
