package com.frontanilla.farminghappyness.visualization.screens.loading;

import com.frontanilla.farminghappyness.visualization.screens.Renderer;

public class LoadingRenderer extends Renderer {

    public LoadingRenderer(LoadingConnector connector) {
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
        // TODO think what to put here, maybe static content or something like that
    }
}
