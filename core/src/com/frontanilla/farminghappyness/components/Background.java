package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.BACKGROUND_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.DOT_QUANTITY;
import static com.frontanilla.farminghappyness.utils.Constants.DOT_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class Background {

    private NeonDot[] neonDots;

    public Background() {
        neonDots = new NeonDot[DOT_QUANTITY];
        Color[] dotColors = new Color[3];
        dotColors[0] = Color.PURPLE;
        dotColors[1] = Color.BLUE;
        dotColors[2] = Color.CYAN;
        for (int i = 0; i < DOT_QUANTITY; i++) {
            float x = MathUtils.random(0, WORLD_WIDTH - DOT_SIZE);
            float y = MathUtils.random(0, WORLD_HEIGHT - DOT_SIZE);
            neonDots[i] = new NeonDot(x, y, dotColors[MathUtils.random(0, dotColors.length - 1)]);
        }
    }

    private void update() {
        float delta = Gdx.graphics.getDeltaTime();
        for (NeonDot dot : neonDots) {
            dot.update(delta);
        }
    }

    public void render(SpriteBatch batch) {
        update();
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
        for (NeonDot dot : neonDots) {
            dot.render(batch);
        }
    }

    private class NeonDot {
        private float x, y, alpha;
        private Color color;
        private float time, delay, deltaMultiplier;
        private boolean reverseAnimation;

        public NeonDot(float x, float y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
            time = 0;
            delay = MathUtils.random(0f, 2f);
            deltaMultiplier = MathUtils.random(0.25f, 1f);
            alpha = 0;
            reverseAnimation = false;
        }

        public void update(float delta) {
            delay -= delta;
            if (delay <= 0) {
                if (reverseAnimation) {
                    time -= delta * deltaMultiplier;
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
                    DOT_SIZE,
                    DOT_SIZE);
        }
    }
}
