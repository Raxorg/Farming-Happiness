package com.frontanilla.farminghappyness.visualization.screens.loading;

import com.frontanilla.farminghappyness.visualization.screens.Input;

public class LoadingInput extends Input {

    public LoadingInput(LoadingConnector connector) {
        super(connector);
    }

    @Override
    public void init() {
        // Nothing to do here
    }

    @Override
    protected void configure() {
        // No input on the Loading screen yet, maybe a mini game?
    }
}
