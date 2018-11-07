package com.frontanilla.farminghappyness.game.plants;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Plant {

    protected Rectangle bounds;
    protected TextureRegion texture;
    protected int buyCost, sellCost;

    public Plant(int buyCost, int sellCost) {
        this.buyCost = buyCost;
        this.sellCost = sellCost;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                texture,
                bounds.x,
                bounds.y);
    }

    public class A extends Plant {

        public A(int buyCost, int sellCost) {
            super(5, 10);
        }
    }
}
