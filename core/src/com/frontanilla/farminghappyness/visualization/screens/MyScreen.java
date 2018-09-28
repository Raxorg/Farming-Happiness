package com.frontanilla.farminghappyness.visualization.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.OnResultListener;
import com.frontanilla.farminghappyness.visualization.DynamicCamera;

public abstract class MyScreen extends ScreenAdapter {

    // Structure
    protected Connector connector;
    protected FarmingGame game;
    // Screen related
    protected DynamicCamera camera;

    public MyScreen(Connector connector, FarmingGame game) {
        this.connector = connector;
        this.game = game;
    }

    public abstract void init();

    @Override
    public void render(float delta) {
        prepareViewport();

        connector.logic.beforeUpdate(delta);
        connector.renderer.beforeRender();

        connector.logic.update(delta);
        connector.renderer.render();

        connector.logic.afterUpdate(delta);
        connector.renderer.afterRender();
    }

    private void prepareViewport() {
        // Clear previous screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Update camera and set the batch's projection matrix
        connector.getRenderer().updateCamera();
    }

    protected void fade(final MyScreen nextScreen, boolean fadeIn) {
        connector.getStuff().getFader().start(fadeIn, new OnResultListener() {
            @Override
            public void onResult(boolean fadeIn) {
                if (!fadeIn) {
                    game.setScreen(nextScreen);
                }
            }
        });
    }

    @Override
    public void show() {
        // Delegate
        connector.renderer.beforeScreenShow();
        connector.logic.beforeScreenShow();
        // Setup the fader
        fade(null, true);
    }

    @Override
    public void resize(int width, int height) {
        float ratio = (float) width / (float) height;
        camera.setToOrtho(
                false,
                Constants.CAMERA_HEIGHT * ratio * camera.getInitialZoom(),
                Constants.CAMERA_HEIGHT * camera.getInitialZoom()
        );
        camera.position.set(camera.getStartingPosition(), 0);
        camera.update();
    }

    @Override
    public void dispose() {
        connector.renderer.dispose();
        camera.dispose();
    }

    public DynamicCamera getCamera() {
        return camera;
    }
}
