package com.frontanilla.farminghappyness.visualization.screens;

public abstract class Connector {

    protected FirestoreConnection firestore;
    protected MyAdvancedScreen screen;
    protected Stuff stuff;
    protected Observer observer;
    protected Renderer renderer;
    protected Input input;

    public FirestoreConnection getFirestore() { return firestore; }

    public MyAdvancedScreen getScreen() {
        return screen;
    }

    public Stuff getStuff() {
        return stuff;
    }

    public Observer getObserver() {
        return observer;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Input getInput() {
        return input;
    }
}
