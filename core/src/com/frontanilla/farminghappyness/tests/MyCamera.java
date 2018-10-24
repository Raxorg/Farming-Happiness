package com.frontanilla.farminghappyness.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.frontanilla.farminghappyness.utils.Constants;

import static com.frontanilla.farminghappyness.utils.Constants.MIN_ZOOM;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class MyCamera extends OrthographicCamera {

    public MyCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);

        //position.set(viewportWidth / 2f, viewportHeight / 2f, 0);
        position.add(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        update();
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            translate(-Constants.PANNING_SPEED, 0, 0); // TODO MULTIPLY BY A DYNAMIC VARIABLE ACCORDING TO ZOOM
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            translate(Constants.PANNING_SPEED, 0, 0); // TODO MULTIPLY BY A DYNAMIC VARIABLE ACCORDING TO ZOOM
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            translate(0, -Constants.PANNING_SPEED, 0); // TODO MULTIPLY BY A DYNAMIC VARIABLE ACCORDING TO ZOOM
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            translate(0, Constants.PANNING_SPEED, 0); // TODO MULTIPLY BY A DYNAMIC VARIABLE ACCORDING TO ZOOM
        }

        zoom = MathUtils.clamp(zoom, MIN_ZOOM, (WORLD_WIDTH / viewportWidth) * 0.8f);
        zoom = MathUtils.clamp(zoom, MIN_ZOOM, (WORLD_HEIGHT / viewportHeight) * 0.8f);

        float effectiveViewportWidth = viewportWidth * zoom;
        float effectiveViewportHeight = viewportHeight * zoom;

        position.x = MathUtils.clamp(position.x, effectiveViewportWidth / 2f, WORLD_WIDTH - effectiveViewportWidth / 2f);
        position.y = MathUtils.clamp(position.y, effectiveViewportHeight / 2f, WORLD_HEIGHT - effectiveViewportHeight / 2f);
    }

    public void resize(int width, int height) {
        viewportWidth = WORLD_WIDTH * 0.3f;
        viewportHeight = WORLD_WIDTH * 0.3f * height / width;
        update();
    }
}