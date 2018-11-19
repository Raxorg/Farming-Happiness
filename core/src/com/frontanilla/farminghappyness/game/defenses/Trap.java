package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.NinePatcherTile;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.TILE_SPACING;
import static com.frontanilla.farminghappyness.utils.Constants.TRAP_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TRAP_INITIAL_HEALTH;
import static com.frontanilla.farminghappyness.utils.Constants.TRAP_WIDTH;

public class Trap extends Defense {

    private boolean activated;
    private float time;

    public Trap(NinePatcherTile ninePatcherTile) {
        super(new Rectangle(ninePatcherTile.getX(), ninePatcherTile.getY(), TRAP_WIDTH, TRAP_HEIGHT), TRAP_INITIAL_HEALTH);
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
                TRAP_WIDTH + TILE_SPACING * 2,
                TRAP_HEIGHT);
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
