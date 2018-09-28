package com.frontanilla.farminghappyness.visualization.screens.mainmenu;

import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.visualization.screens.Connector;

public class MainMenuConnector extends Connector {

    public MainMenuConnector(FarmingGame game) {
        //firestore = new MainMenuFirestore(this);
        screen = new MainMenuScreen(this, game);
        stuff = new MainMenuStuff(this);
        logic = new MainMenuLogic(this);
        renderer = new MainMenuRenderer(this);
        input = new MainMenuInput(this);

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
