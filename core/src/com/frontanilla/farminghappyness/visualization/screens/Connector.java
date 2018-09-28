package com.frontanilla.farminghappyness.visualization.screens;

public abstract class Connector {

    protected FirestoreConnection firestore;
    protected MyScreen screen;
    protected Stuff stuff;
    protected Logic logic;
    protected Renderer renderer;
    protected Input input;

    public FirestoreConnection getFirestore() { return firestore; }

    public MyScreen getScreen() {
        return screen;
    }

    public Stuff getStuff() {
        return stuff;
    }

    public Logic getLogic() {
        return logic;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Input getInput() {
        return input;
    }
}
