package com.frontanilla.farminghappyness.game.entities.units;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.defenses.Mine;
import com.frontanilla.farminghappyness.game.defenses.Wall;
import com.frontanilla.farminghappyness.utils.Enums;

public class Police extends Enemy {

    public Police(TextureRegion texture, float x, float y, float speed, int life) {
        super(texture, x, y, speed, life);
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
        } else if (state == Enums.EnemyState.SHOOTING) {
            move(delta, 0);
        } else {
            move(delta, 1);
        }
    }

    @Override
    public void takeDamage(int damage) {

    }
}
