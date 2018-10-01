package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.frontanilla.farminghappyness.utils.Constants;

public class DynamicCamera extends OrthographicCamera {

    // TODO: think about letting users configure panning and zooming speed
    private ShapeRenderer renderer;
    private float worldWidth, worldHeight;
    private float initialZoom;
    private boolean zoomEnabled;
    private Vector2 startingPosition;

    public DynamicCamera(float worldWidth, float worldHeight, float initialZoom,
                         boolean zoomEnabled, Vector2 startingPosition) {
        renderer = new ShapeRenderer();
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.initialZoom = initialZoom;
        this.zoomEnabled = zoomEnabled;
        this.startingPosition = startingPosition;
        update();
    }

    public DynamicCamera() {
        renderer = new ShapeRenderer();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float ratio = screenWidth / screenHeight;
        worldWidth = Constants.CAMERA_HEIGHT * ratio;
        worldHeight = Constants.CAMERA_HEIGHT;
        initialZoom = 1;
        zoomEnabled = false;
        startingPosition = new Vector2();
        update();
    }

    public Vector2 getWorldSize() {
        return new Vector2(worldWidth, worldHeight);
    }

    public float getInitialZoom() {
        return initialZoom;
    }

    public Vector2 getStartingPosition() {
        return startingPosition;
    }

    @Override
    public void update() {
        if (zoomEnabled) {
            zoom = MathUtils.clamp(
                    zoom,
                    initialZoom / 2,
                    worldWidth / viewportWidth
            );
            zoom = MathUtils.clamp(
                    zoom,
                    initialZoom / 2,
                    worldHeight / viewportHeight
            );
        } else {
            zoom = MathUtils.clamp(
                    zoom,
                    initialZoom,
                    worldWidth / viewportWidth
            );
            zoom = MathUtils.clamp(
                    zoom,
                    initialZoom,
                    worldHeight / viewportHeight
            );
        }

        float effectiveViewportWidth = viewportWidth * zoom;
        float effectiveViewportHeight = viewportHeight * zoom;

        position.x = MathUtils.clamp(
                position.x,
                effectiveViewportWidth / 2f,
                worldWidth - effectiveViewportWidth / 2f
        );
        position.y = MathUtils.clamp(
                position.y,
                effectiveViewportHeight / 2f,
                worldHeight - effectiveViewportHeight / 2f
        );
        super.update();
    }

    public void renderBounds() {
        Vector2 bottomLeft = myUnproject(0, viewportHeight);
        Vector2 bottomRight = myUnproject(viewportWidth, viewportHeight);
        Vector2 topRight = myUnproject(viewportWidth, 0);
        Vector2 topLeft = myUnproject(0, 0);

        // Draw a rectangle showing the closeup camera's field of view
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLUE);
        float[] poly = {bottomLeft.x, bottomLeft.y,
                bottomRight.x, bottomRight.y,
                topRight.x, topRight.y,
                topLeft.x, topLeft.y
        };
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.polygon(poly);
        renderer.end();
    }

    /**
     * Helper function to deal with the fact that unproject expects coordinates with positive y
     * pointing down.
     */
    private Vector2 myUnproject(float x, float y) {
        Vector3 raw = unproject(new Vector3(x, y + Gdx.graphics.getHeight() - viewportHeight, 0), 0, 0, viewportWidth, viewportHeight);
        return new Vector2(raw.x, raw.y);
    }

    public void dispose() {
        renderer.dispose();
    }
}