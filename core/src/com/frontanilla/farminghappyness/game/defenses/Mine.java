package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.NinePatcherTile;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.MINE_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.MINE_INITIAL_HEALTH;
import static com.frontanilla.farminghappyness.utils.Constants.MINE_WIDTH;

public class Mine extends Defense {

    private boolean activated;
    private float activationTime, animationTime;

    public Mine(NinePatcherTile ninePatcherTile) {
        super(new Rectangle(ninePatcherTile.getX(), ninePatcherTile.getY(), MINE_WIDTH, MINE_HEIGHT), MINE_INITIAL_HEALTH);
        activated = false;
        activationTime = 0;
        animationTime = 0;
    }

    @Override
    public void update(float delta) {
        animationTime += delta;
        if (activated) {
            activationTime += delta;
            if (activationTime >= 7) {
                activated = false;
                activationTime = 0;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                Assets.mineAnimation.getKeyFrame(animationTime),
                bounds.x,
                bounds.y);
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
