package com.frontanilla.farminghappyness.game.entities.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.defenses.Mine;
import com.frontanilla.farminghappyness.game.defenses.Wall;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.MINE_DAMAGE;
import static com.frontanilla.farminghappyness.utils.Constants.POLICE_ATTACK_COOLDOWN;
import static com.frontanilla.farminghappyness.utils.Constants.POLICE_DAMAGE;
import static com.frontanilla.farminghappyness.utils.Constants.POLICE_INITIAL_LIFE;
import static com.frontanilla.farminghappyness.utils.Constants.POLICE_SPEED;
import static com.frontanilla.farminghappyness.utils.Enums.EnemyState.MOVING;
import static com.frontanilla.farminghappyness.utils.Enums.EnemyState.STUCK;

public class Police extends Enemy {

    public Police(float x, float y) {
        super(Assets.enemyAnimation, x, y, POLICE_SPEED, POLICE_INITIAL_LIFE, POLICE_DAMAGE, POLICE_ATTACK_COOLDOWN);
    }

    @Override
    public void update(float delta, DelayedRemovalArray<Defense> defenses) {
        super.update(delta, defenses);
        time += delta;
        switch (state) {
            case MOVING:
                move(delta);
                for (Defense defense : defenses) {
                    if (bounds.overlaps(defense.getBounds())) {
                        if (defense instanceof Mine) {
                            takeDamage(MINE_DAMAGE);
                            ((Mine) defense).activate();
                        } else {
                            state = STUCK;
                            attackTime = 0;
                        }
                    }
                }
                break;
            case STUCK:
                attackTime += delta;
                state = MOVING;
                for (Defense defense : defenses) {
                    if (bounds.overlaps(defense.getBounds())) {
                        state = STUCK;
                        if (attackTime >= attackCooldown) {
                            defense.takeDamage(damage);
                            attackTime -= attackCooldown;
                        }
                    }
                }
                break;
            case SHOOTING:

                break;
            case SHOOTING_AND_MOVING:

                break;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        batch.setColor(Color.WHITE);
        batch.draw(Assets.policeHat, bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
