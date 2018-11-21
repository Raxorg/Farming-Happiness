package com.frontanilla.farminghappyness.game.entities.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.defenses.Mine;
import com.frontanilla.farminghappyness.game.defenses.Wall;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.TOURIST_INITIAL_LIFE;
import static com.frontanilla.farminghappyness.utils.Constants.TOURIST_SPEED;

public class Tourist extends Enemy {

    public Tourist(float x, float y) {
        super(Assets.enemyAnimation, x, y, TOURIST_SPEED, TOURIST_INITIAL_LIFE);
    }

    @Override
    public void update(float delta, DelayedRemovalArray<Defense> defenses) {
        super.update(delta, defenses);
        boolean stuck = false;
        for (Defense d : defenses) {
            if (d.getBounds().overlaps(bounds)) {
                if (d instanceof Wall) {
                    stuck = true;
                } else if (d instanceof Mine && !((Mine) d).isActivated()) {
                    life -= 5;
                    ((Mine) d).activate();
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
        super.render(batch);
        batch.setColor(Color.WHITE);
        batch.draw(Assets.touristHat, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    @Override
    public void takeDamage(int damage) {
        // TODO use this
    }
}
