package com.frontanilla.farminghappyness.visualization.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.visualization.components.Component;

public abstract class Renderer {

    protected Connector connector;
    protected SpriteBatch batch;

    public Renderer(Connector connector) {
        this.connector = connector;
    }

    public abstract void init();

    public void beforeScreenShow() {
        batch = new SpriteBatch();
        onScreenShow();
    }

    // Screens use different renderers
    public abstract void updateCamera();

    public abstract void onScreenShow();

    public void beforeRender() {
        batch.begin();
        for (Component comp : connector.stuff.getComponents()) {
            if (comp.isVisible()) {
                comp.render(batch);
            }
        }
        batch.end();
    }

    public abstract void render();

    public void afterRender() {
        batch.begin();
        connector.getStuff().getFader().render(connector.renderer.batch);
        batch.end();
    }

    public void dispose() {
        batch.dispose();
    }
}
