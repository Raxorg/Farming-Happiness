package com.frontanilla.farminghappyness.visualization.screens;

import com.frontanilla.farminghappyness.visualization.components.Component;

public abstract class Observer {

    // Structure
    protected Connector connector;

    public Observer(Connector connector) {
        this.connector = connector;
    }

    public abstract void init();

    public void beforeUpdate(float delta) {
        // Update components
        for (Component c : connector.stuff.getComponents()) {
            c.update(delta);
        }
    }

    public abstract void update(float delta);

    public void afterUpdate(float delta) {
        connector.getStuff().getFader().update(delta);
    }

    public void beforeScreenShow() {
        connector.input.beforeConfigure();
        onScreenShow();
    }

    public abstract void onScreenShow();
}