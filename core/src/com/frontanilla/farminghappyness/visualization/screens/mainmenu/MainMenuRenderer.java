package com.frontanilla.farminghappyness.visualization.screens.mainmenu;

import com.frontanilla.farminghappyness.visualization.screens.Renderer;

public class MainMenuRenderer extends Renderer {

    public MainMenuRenderer(MainMenuConnector connector) {
        super(connector);
    }

    @Override
    public void init() {
        // TODO
    }

    @Override
    public void updateCamera() {
        connector.getScreen().getCamera().update();
        batch.setProjectionMatrix(connector.getScreen().getCamera().combined);
    }

    @Override
    public void onScreenShow() {
        // TODO something?
    }

    @Override
    public void render() {
        // TODO something?
    }
}
