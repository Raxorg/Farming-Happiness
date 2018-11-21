package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

import static com.frontanilla.farminghappyness.utils.Constants.MIN_ZOOM;
import static com.frontanilla.farminghappyness.utils.Constants.SCREEN_HEIGHT_VISIBILITY_PERCENTAGE;
import static com.frontanilla.farminghappyness.utils.Constants.SCREEN_WIDTH_VISIBILITY_PERCENTAGE;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class MyCamera extends OrthographicCamera {

    public MyCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
        position.add(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        zoom = MathUtils.clamp(zoom,
                (WORLD_WIDTH / viewportWidth) * SCREEN_WIDTH_VISIBILITY_PERCENTAGE,
                (WORLD_WIDTH / viewportWidth) * SCREEN_WIDTH_VISIBILITY_PERCENTAGE);
        zoom = MathUtils.clamp(zoom,
                (WORLD_HEIGHT / viewportHeight) * SCREEN_HEIGHT_VISIBILITY_PERCENTAGE,
                (WORLD_HEIGHT / viewportHeight) * SCREEN_HEIGHT_VISIBILITY_PERCENTAGE);
        update();
    }

    @Override
    public void update() {
        super.update();
        zoom = MathUtils.clamp(zoom, MIN_ZOOM, (WORLD_WIDTH / viewportWidth) * SCREEN_WIDTH_VISIBILITY_PERCENTAGE);
        zoom = MathUtils.clamp(zoom, MIN_ZOOM, (WORLD_HEIGHT / viewportHeight) * SCREEN_HEIGHT_VISIBILITY_PERCENTAGE);

        float effectiveViewportWidth = viewportWidth * zoom;
        float effectiveViewportHeight = viewportHeight * zoom;

        position.x = MathUtils.clamp(position.x, effectiveViewportWidth / 2f, WORLD_WIDTH - effectiveViewportWidth / 2f);
        position.y = MathUtils.clamp(position.y, effectiveViewportHeight / 2f, WORLD_HEIGHT - effectiveViewportHeight / 2f);
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            zoom -= 0.02;
        }
        update();
    }

    public void resize(int width, int height) {
        viewportWidth = WORLD_WIDTH * 0.3f;
        viewportHeight = WORLD_WIDTH * 0.3f * height / width;
        update();
    }
}