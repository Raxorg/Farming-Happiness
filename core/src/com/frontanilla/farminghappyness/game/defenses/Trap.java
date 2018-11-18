package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SPACING;
import static com.frontanilla.farminghappyness.utils.Constants.TRAP_INITIAL_HEALTH;
import static com.frontanilla.farminghappyness.utils.Constants.TRAP_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_SIZE;

public class Trap extends Defense {

    private boolean activated;
    private float time;

    public Trap(Tile tile) {
        super(new Rectangle(tile.getX(), tile.getY(), WALL_SIZE, WALL_SIZE), TRAP_INITIAL_HEALTH);
        activated = false;
        time = 0;
    }

    @Override
    public void update(float delta) {
        if (activated) {
            time += delta;
            if (time >= 7) {
                activated = false;
                time = 0;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(
                Assets.trap,
                bounds.x - TILE_SPACING,
                bounds.y,
                TRAP_SIZE + TILE_SPACING * 2,
                TRAP_SIZE);
        if (!activated) {
            batch.draw(
                    Assets.trapLeaves,
                    bounds.x - TILE_SPACING,
                    bounds.y,
                    TRAP_SIZE + TILE_SPACING * 2,
                    TRAP_SIZE);
        }
    }

    public void activate() {
        activated = true;
    }

    public boolean isActivated() {
        return activated;
    }

    @Override
    public void takeDamage(int damage) {

    }
}
