package com.frontanilla.farminghappyness.visualization.screens.mainmenu;

import com.frontanilla.farminghappyness.visualization.components.Button;
import com.frontanilla.farminghappyness.visualization.screens.Input;

public class MainMenuInput extends Input {

    public MainMenuInput(MainMenuConnector connector) {
        super(connector);
    }

    @Override
    public void init() {

    }

    @Override
    public void configure() {
        setInputSubscriber(new Input.InputSubscriber() {
            @Override
            public void onTouchUp(float x, float y) {
                for (Button b : ((MainMenuStuff) connector.getStuff()).getButtons()) {
                    b.setHover(false);
                    if (b.contains(x, y)) {
                        b.onTap();
                    }
                }
            }

            @Override
            public void onTouchDragged(float x, float y) {
                for (Button b : ((MainMenuStuff) connector.getStuff()).getButtons()) {
                    b.setHover(b.contains(x, y));
                }
            }

            @Override
            public void onTap(float x, float y, int count) {
                // Nothing happens
            }

            @Override
            public void onTouchDown(float x, float y) {
                for (Button b : ((MainMenuStuff) connector.getStuff()).getButtons()) {
                    b.setHover(b.contains(x, y));
                }
            }
        });
    }
}
