package com.frontanilla.farminghappyness.game.entities.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.defenses.Defense;
import com.frontanilla.farminghappyness.game.entities.Damageable;
import com.frontanilla.farminghappyness.utils.Enums;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.ENEMY_WIDTH;

public abstract class Enemy extends Damageable {

    protected Animation<TextureRegion> animation;
    protected int damage;
    protected float speed, angle, time, attackTime;
    protected Enums.EnemyState state;

    public Enemy(Animation<TextureRegion> animation, float x, float y, float speed, int life, int damage) {
        super(new Rectangle(x, y, ENEMY_WIDTH, ENEMY_HEIGHT), life);
        this.animation = animation;
        this.damage = damage;
        this.speed = speed;
        time = 0;
        state = Enums.EnemyState.MOVING;
    }

    public void update(float delta, DelayedRemovalArray<Defense> defenses) {
        // TODO use this or delete this
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                animation.getKeyFrame(time),
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
        lifeBar.render(batch);
    }

    protected void move(float delta, float speedModifier) {
        float cos = MathUtils.cosDeg(angle);
        float sin = MathUtils.sinDeg(angle);
        float x = delta * cos;
        float y = delta * sin;
        bounds.setX(bounds.getX() + x * speed * speedModifier);
        bounds.setY(bounds.getY() + y * speed * speedModifier);
    }

    public Point getCenter() {
        return new Point(bounds.getX() + ENEMY_WIDTH / 2, bounds.getY() + ENEMY_HEIGHT / 2);
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
