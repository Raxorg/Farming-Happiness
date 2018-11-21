package com.frontanilla.farminghappyness.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.LifeBar;

public abstract class Damageable extends GameEntity {

    protected int initialLife, life;
    protected LifeBar lifeBar;

    public Damageable(Rectangle bounds, int initialLife) {
        super(bounds);
        this.initialLife = initialLife;
        life = initialLife;
        lifeBar = new LifeBar(this);
    }

    public abstract void takeDamage(int damage); // TODO maybe use this

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
