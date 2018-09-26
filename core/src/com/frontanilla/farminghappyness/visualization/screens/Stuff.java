package com.frontanilla.farminghappyness.visualization.screens;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.visualization.components.Component;
import com.frontanilla.farminghappyness.visualization.components.Fader;

public abstract class Stuff {

    // Structure
    protected Connector connector;
    // Common to all stuff instances
    protected DelayedRemovalArray<Component> components;
    protected Fader fader;

    public Stuff(Connector connector) {
        this.connector = connector;
        components = new DelayedRemovalArray<>();
    }

    public void beforeInit() {
        fader = new Fader(
                0,
                0,
                connector.getScreen().getCamera().getWorldSize().x,
                connector.getScreen().getCamera().getWorldSize().y
        );
    }

    public abstract void init();

    public DelayedRemovalArray<Component> getComponents() {
        return components;
    }

    public Fader getFader() {
        return fader;
    }
}
