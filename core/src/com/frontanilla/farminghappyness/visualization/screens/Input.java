package com.frontanilla.farminghappyness.visualization.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.frontanilla.farminghappyness.utils.Constants;

public abstract class Input extends InputAdapter implements GestureDetector.GestureListener {

    // TODO WORKING ON INPUT CLASS, NEEDS A BUNCH OF WORK, MAY RESTART FROM ZERO

    // Structure
    protected Connector connector;
    // Input related
    private boolean enabled;
    private Vector3 unprojected;
    private InputMultiplexer multiplexer;
    private InputSubscriber inputSubscriber;
    // Camera related
    private float lastZoomDistance;
    private float lastPinchX, lastPinchY;

    public Input(Connector connector) {
        this.connector = connector;
        unprojected = new Vector3();
        multiplexer = new InputMultiplexer();
    }

    public abstract void init();

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void beforeConfigure() {
        multiplexer = new InputMultiplexer();
        // Config the input machine once the camera is initialized
        float halfSquareSize = Constants.CAMERA_HEIGHT * 0.05f;
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
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(gestureDetector);
        Gdx.input.setInputProcessor(multiplexer);

        configure();
    }

    protected abstract void configure();

    public void setInputSubscriber(InputSubscriber inputSubscriber) {
        this.inputSubscriber = inputSubscriber;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if (!enabled || pointer != 0) {
            return false;
        }
        unprojected.set(connector.screen.getCamera().unproject(new Vector3(x, y, 0)));
        if (inputSubscriber != null) {
            inputSubscriber.onTouchDown(unprojected.x, unprojected.y);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!enabled || pointer != 0) {
            return false;
        }
        unprojected.set(connector.screen.getCamera().unproject(new Vector3(screenX, screenY, 0)));
        if (inputSubscriber != null) {
            inputSubscriber.onTouchUp(unprojected.x, unprojected.y);
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (!enabled || pointer != 0) {
            return false;
        }
        unprojected.set(connector.screen.getCamera().unproject(new Vector3(screenX, screenY, 0)));
        if (inputSubscriber != null) {
            inputSubscriber.onTouchDragged(unprojected.x, unprojected.y);
        }
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (!enabled) {
            return false;
        }
        unprojected.set(connector.screen.getCamera().unproject(new Vector3(x, y, 0)));
        if (inputSubscriber != null) {
            inputSubscriber.onTap(unprojected.x, unprojected.y, count);
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
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
        float pinchX = (pointer1.x + pointer2.x) / 2;
        float pinchY = (pointer1.y + pointer2.y) / 2;
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
        connector.screen.getCamera().zoom -= distanceDifference / 300;
        // Pan
        float deltaX = (pinchX - lastPinchX) * connector.screen.getCamera().zoom;
        float deltaY = (pinchY - lastPinchY) * connector.screen.getCamera().zoom;
        connector.screen.getCamera().translate(-deltaX, deltaY);
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

    public static abstract class InputSubscriber {
        public abstract void onTouchDown(float x, float y);

        public abstract void onTouchUp(float x, float y);

        public abstract void onTouchDragged(float x, float y);

        public abstract void onTap(float x, float y, int count);
    }
}
