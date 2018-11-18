package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.frontanilla.farminghappyness.game.GameConnector;

public class GameInput extends InputAdapter implements GestureDetector.GestureListener {

    private GameConnector connector;
    private InputMultiplexer inputMultiplexer;
    // Camera related
    private float lastZoomDistance;
    private float lastPinchX, lastPinchY;

    public GameInput(GameConnector connector) {
        this.connector = connector;
        inputMultiplexer = new InputMultiplexer();
        float halfSquareSize = 10; // TODO test
        float tapSecondsInterval = 0.55f;
        float longPressDuration = 1.25f;
        float maxFlingDelay = 0.15f;
        GestureDetector gestureDetector = new GestureDetector(
                halfSquareSize,
                tapSecondsInterval,
                longPressDuration,
                maxFlingDelay,
                this
        );
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(gestureDetector);
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        // TODO use this in conjunction with fling to open side menus
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        // TODO use this for buying selling
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        // TODO use this to show the menus
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        connector.getCamera().translate(-deltaX, deltaY);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        // Calculate distances
        float initialDistance = initialPointer1.dst(initialPointer2);
        float distance = pointer1.dst(pointer2);
        // Calculate pinch coordinates
        float initialPinchX = (initialPointer1.x + initialPointer2.x) / 2;
        float initialPinchY = (initialPointer1.y + initialPointer2.y) / 2;
        // This to avoid first time zooming or panning horrible behavior
        if (lastZoomDistance == 0) {
            lastZoomDistance = initialDistance;
        }
        if (lastPinchX == lastPinchY && lastPinchX == 0) {
            lastPinchX = initialPinchX;
            lastPinchY = initialPinchY;
        }
        // Zoom
        float distanceDifference = distance - lastZoomDistance;
        connector.getCamera().zoom -= distanceDifference / 300;
        // We need to update these for future calculations
        lastZoomDistance = distance;
        lastPinchX = (pointer1.x + pointer2.x) / 2;
        lastPinchY = (pointer1.y + pointer2.y) / 2;
        return false;
    }

    @Override
    public void pinchStop() {
        lastZoomDistance = 0;
        lastPinchX = 0;
        lastPinchY = 0;
    }
}
