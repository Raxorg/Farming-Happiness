package com.frontanilla.farminghappyness.game.entities.ambient;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.game.entities.GameEntity;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.CLOUD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class Cloud extends GameEntity {

    private float speed;

    public Cloud(Rectangle bounds) {
        super(bounds);
        speed = MathUtils.random(2f, 6f);
    }

    public void update(float delta) {
        bounds.x += delta * speed;
        if (bounds.x >= WORLD_WIDTH) {
            bounds.x = -bounds.width;
            bounds.y = MathUtils.random(WORLD_HEIGHT - CLOUD_HEIGHT);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(Assets.cloud, bounds.x, bounds.y);
        batch.setColor(new Color(0, 0, 0, 0.25f));
        batch.draw(Assets.cloud, bounds.x, bounds.y - 100);
    }
}
