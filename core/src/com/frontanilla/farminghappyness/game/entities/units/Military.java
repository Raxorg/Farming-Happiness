package com.frontanilla.farminghappyness.game.entities.units;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.defenses.Mine;
import com.frontanilla.farminghappyness.game.defenses.Wall;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.MILITARY_DAMAGE;
import static com.frontanilla.farminghappyness.utils.Constants.MILITARY_INITIAL_LIFE;
import static com.frontanilla.farminghappyness.utils.Constants.MILITARY_SPEED;
import static com.frontanilla.farminghappyness.utils.Constants.MINE_DAMAGE;
import static com.frontanilla.farminghappyness.utils.Constants.TOURIST_ATTACK_COOLDOWN;
import static com.frontanilla.farminghappyness.utils.Enums.EnemyState.MOVING;
import static com.frontanilla.farminghappyness.utils.Enums.EnemyState.STUCK;

public class Military extends Enemy {
    public Military(float x, float y) {
        super(Assets.enemyAnimation, x, y, MILITARY_SPEED, MILITARY_INITIAL_LIFE, MILITARY_DAMAGE);
    }

    @Override
    public void update(float delta, DelayedRemovalArray<Defense> defenses) {
        super.update(delta, defenses);
        time += delta;
        for (Defense defense : defenses) {
            if (defense.getBounds().overlaps(bounds)) {
                if (defense instanceof Wall) {
                    if (state != STUCK) {
                        attackTime = 0;
                        state = STUCK;
                        defense.takeDamage(damage);
                        if (defense.isLifeless()) {
                            state = MOVING;
                        }
                    } else {
                        attackTime += delta;
                        if (attackTime >= TOURIST_ATTACK_COOLDOWN) {
                            attackTime -= TOURIST_ATTACK_COOLDOWN;
                            defense.takeDamage(damage);
                        }
                    }
                } else if (defense instanceof Mine && !((Mine) defense).isActivated()) {
                    takeDamage(MINE_DAMAGE);
                    ((Mine) defense).activate();
                }
            }
        }
        if (state != STUCK) {
            move(delta, 1);
        }
    }
}
