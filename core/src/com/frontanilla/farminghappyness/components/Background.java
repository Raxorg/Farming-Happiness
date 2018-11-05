package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.BACKGROUND_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.GRASS_QUANTITY;
import static com.frontanilla.farminghappyness.utils.Constants.GRASS_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class Background {

    private GrassDot[] grassDots;

    public Background() {
        grassDots = new GrassDot[GRASS_QUANTITY];
        Color[] grassColors = new Color[4];
        grassColors[0] = Color.GREEN;
        grassColors[1] = Color.PURPLE;
        grassColors[2] = Color.BLUE;
        grassColors[3] = Color.CYAN;
        for (int i = 0; i < GRASS_QUANTITY; i++) {
            float x = MathUtils.random(BACKGROUND_TILE_SIZE, WORLD_WIDTH - BACKGROUND_TILE_SIZE * 2);
            float y = MathUtils.random(BACKGROUND_TILE_SIZE, WORLD_HEIGHT - BACKGROUND_TILE_SIZE * 2);
            grassDots[i] = new GrassDot(x, y, grassColors[MathUtils.random(0, 3)]);
        }
    }

    public void update(float delta) {
        for (GrassDot dot : grassDots) {
            dot.update(delta);
        }
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        renderBase(batch);
        renderBorders(batch);
        renderBordersGlow(batch);
        renderDecoration(batch);
    }

    private void renderBase(SpriteBatch batch) {
        batch.setColor(Color.BLACK);
        batch.draw(
                Assets.pixel,
                0,
                0,
                WORLD_WIDTH,
                WORLD_HEIGHT);
    }

    private void renderBorders(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        // LEFT
        batch.draw(
                Assets.bordersLeft,
                0,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE,
                WORLD_HEIGHT - BACKGROUND_TILE_SIZE * 2);
        // TOP
        batch.draw(
                Assets.bordersTop,
                BACKGROUND_TILE_SIZE,
                WORLD_HEIGHT - BACKGROUND_TILE_SIZE,
                WORLD_WIDTH - BACKGROUND_TILE_SIZE * 2,
                BACKGROUND_TILE_SIZE);
        // RIGHT
        batch.draw(
                Assets.bordersRight,
                WORLD_WIDTH - BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE,
                WORLD_HEIGHT - BACKGROUND_TILE_SIZE * 2);
        // BOTTOM
        batch.draw(
                Assets.bordersBottom,
                BACKGROUND_TILE_SIZE,
                0,
                WORLD_WIDTH - BACKGROUND_TILE_SIZE * 2,
                BACKGROUND_TILE_SIZE);
        // TOP LEFT
        batch.draw(
                Assets.bordersTopLeft,
                0,
                WORLD_HEIGHT - BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE);
        // TOP RIGHT
        batch.draw(
                Assets.bordersTopRight,
                WORLD_WIDTH - BACKGROUND_TILE_SIZE,
                WORLD_HEIGHT - BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE);
        // BOTTOM LEFT
        batch.draw(
                Assets.bordersBottomLeft,
                0,
                0,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE);
        // BOTTOM RIGHT
        batch.draw(
                Assets.bordersBottomRight,
                WORLD_WIDTH - BACKGROUND_TILE_SIZE,
                0,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE);
    }

    private void renderBordersGlow(SpriteBatch batch) {
        batch.setColor(Color.GREEN);
        // LEFT
        batch.draw(
                Assets.bordersLeftGlow,
                0,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE,
                WORLD_HEIGHT - BACKGROUND_TILE_SIZE * 2);
        // TOP
        batch.draw(
                Assets.bordersTopGlow,
                BACKGROUND_TILE_SIZE,
                WORLD_HEIGHT - BACKGROUND_TILE_SIZE,
                WORLD_WIDTH - BACKGROUND_TILE_SIZE * 2,
                BACKGROUND_TILE_SIZE);
        // RIGHT
        batch.draw(
                Assets.bordersRightGlow,
                WORLD_WIDTH - BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE,
                WORLD_HEIGHT - BACKGROUND_TILE_SIZE * 2);
        // BOTTOM
        batch.draw(
                Assets.bordersBottomGlow,
                BACKGROUND_TILE_SIZE,
                0,
                WORLD_WIDTH - BACKGROUND_TILE_SIZE * 2,
                BACKGROUND_TILE_SIZE);
        // TOP LEFT
        batch.draw(
                Assets.bordersTopLeftGlow,
                0,
                WORLD_HEIGHT - BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE);
        // TOP RIGHT
        batch.draw(
                Assets.bordersTopRightGlow,
                WORLD_WIDTH - BACKGROUND_TILE_SIZE,
                WORLD_HEIGHT - BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE);
        // BOTTOM LEFT
        batch.draw(
                Assets.bordersBottomLeftGlow,
                0,
                0,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE);
        // BOTTOM RIGHT
        batch.draw(
                Assets.bordersBottomRightGlow,
                WORLD_WIDTH - BACKGROUND_TILE_SIZE,
                0,
                BACKGROUND_TILE_SIZE,
                BACKGROUND_TILE_SIZE);
    }

    private void renderDecoration(SpriteBatch batch) {
        for (GrassDot dot : grassDots) {
            dot.render(batch);
        }
    }

    private class GrassDot {
        private float x, y, alpha;
        private Color color;
        private float time, delay;
        private boolean reverseAnimation;

        public GrassDot(float x, float y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
            delay = MathUtils.random() * 2;
            time = MathUtils.random();
            alpha = 0;
            reverseAnimation = false;
        }

        public void update(float delta) {
            delay -= delta;
            if (delay <= 0) {
                if (reverseAnimation) {
                    time -= delta;
                    if (time <= 0) {
                        time = 0;
                        reverseAnimation = false;
                    }
                } else {
                    time += delta;
                    if (time >= 1) {
                        time = 1;
                        reverseAnimation = true;
                    }
                }
                alpha = time;
            }
        }

        public void render(SpriteBatch batch) {
            batch.setColor(new Color(color.r, color.g, color.b, alpha));
            batch.draw(
                    Assets.grass1Glow,
                    x,
                    y,
                    GRASS_SIZE,
                    GRASS_SIZE);
        }
    }
}
