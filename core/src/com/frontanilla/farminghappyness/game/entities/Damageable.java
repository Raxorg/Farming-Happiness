package com.frontanilla.farminghappyness.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.LifeBar;

public abstract class Damageable extends GameEntity {

    protected boolean lifeless;
    protected int initialLife, life;
    protected LifeBar lifeBar;

    public Damageable(Rectangle bounds, int initialLife) {
        super(bounds);
        this.initialLife = initialLife;
        life = initialLife;
        lifeBar = new LifeBar(this);
    }

    public void takeDamage(int damage) {
        life -= damage;
        if (life <= 0) {
            lifeless = true;
        }
    }

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
