package com.frontanilla.farminghappyness.game.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.defenses.Trap;
import com.frontanilla.farminghappyness.game.defenses.Wall;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Assets.touristAnimation;
import static com.frontanilla.farminghappyness.utils.Constants.TOURIST_INITIAL_LIFE;
import static com.frontanilla.farminghappyness.utils.Constants.TOURIST_SPEED;

public class Tourist extends Enemy {

    private float time;

    public Tourist(float x, float y) {
        super(Assets.tourist, x, y, TOURIST_SPEED, TOURIST_INITIAL_LIFE);
        time = 0;
    }

    @Override
    public void update(float delta, DelayedRemovalArray<Defense> defenses) {
        super.update(delta, defenses);
        boolean stuck = false;
        for (Defense d : defenses) {
            if (d.getBounds().overlaps(bounds)) {
                if (d instanceof Wall) {
                    stuck = true;
                } else if (d instanceof Trap && !((Trap) d).isActivated()) {
                    life -= 5;
                    ((Trap) d).activate();
                }
            }
        }
        if (stuck) {
            move(delta, 0.25f);
        } else {
            move(delta, 1);
        }
        time += delta;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                touristAnimation.getKeyFrame(time),
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
        lifeBar.render(batch);
    }

    @Override
    public void takeDamage(int damage) {

    }
}
