package com.frontanilla.farminghappyness.game.defenses;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.game.entities.Damageable;
import com.frontanilla.farminghappyness.utils.Point;

public abstract class Defense extends Damageable {

    protected Point center;

    public Defense(Rectangle bounds, int life) {
        super(bounds, life);
        center = new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
    }

    public Point getCenter() {
        return center;
    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);
}