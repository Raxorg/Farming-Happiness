package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.LifeBar;
import com.frontanilla.farminghappyness.game.GameEntity;

public abstract class Damageable extends GameEntity {

    protected LifeBar lifeBar;
    protected int initialLife, life;

    public Damageable(Rectangle bounds, int initialLife) {
        super(bounds);
        lifeBar = new LifeBar(this);
        this.initialLife = initialLife;
        life = initialLife;
    }

    public abstract void takeDamage(int damage);

    public int getInitialLife() {
        return initialLife;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
