package com.frontanilla.farminghappyness.visualization.screens.game;

import com.badlogic.gdx.graphics.Color;
import com.frontanilla.farminghappyness.visualization.components.Component;
import com.frontanilla.farminghappyness.visualization.screens.Renderer;

public class GameRenderer extends Renderer {

    public GameRenderer(GameConnector connector) {
        super(connector);
    }

    @Override
    public void init() {

    }

    @Override
    public void updateCamera() {
        connector.getScreen().getCamera().update();
        batch.setProjectionMatrix(connector.getScreen().getCamera().combined);
    }

    @Override
    public void onScreenShow() {

    }

    @Override
    public void render() {
        batch.begin();
        // render background
        // TODO
        // render river
        // TODO
        // render trees
        // TODO

        for (Component comp : connector.getStuff().getComponents()) {
            if (comp.isVisible()) {
                Color color = comp.getChildren().get(0).getColor();
                comp.getChildren().get(0).setColor(Color.RED);
                comp.render(batch);
                comp.getChildren().get(0).setColor(color);
            }
        }

        batch.end();
    }
}
