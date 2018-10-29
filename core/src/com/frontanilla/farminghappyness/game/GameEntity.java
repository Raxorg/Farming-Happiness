package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.game.other.LifeBar;
import com.frontanilla.farminghappyness.utils.Point;

public class GameEntity {

    protected Point position;
    protected Rectangle bounds;
    protected int life, initialLife;
    protected LifeBar lifeBar;

    public GameEntity(Rectangle bounds, int life) {
        this.bounds = bounds;
        this.life = life;
        initialLife = life;
        lifeBar = new LifeBar(this);
    }

    public Point getPosition() {
        return position;
    }

    public int getInitialLife() {
        return initialLife;
    }

    public int getLife() {
        return life;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
