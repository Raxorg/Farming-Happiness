package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public abstract class Component {

    protected boolean centered; // TODO think if centering is needed
    protected boolean visible;
    protected Color color;
    protected DelayedRemovalArray<Component> children;
    protected Rectangle bounds; // TODO make this a polygon

    public Component(float x, float y, float width, float height) {
        centered = false;
        visible = true;
        color = Color.WHITE;
        children = new DelayedRemovalArray<>();
        bounds = new Rectangle(x, y, width, height);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setCentered(boolean centered) {
        this.centered = centered;
    }

    public DelayedRemovalArray<Component> getChildren() {
        return children;
    }

    public void addChild(Component child) {
        if(children.contains(child,true)) {
            System.out.println("WARNING: CHILD ALREADY PRESENT");
        }
        children.add(child);
    }

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }

    public void update(float delta) {
        for (Component component : children) {
            component.update(delta);
        }
    }

    public void render(SpriteBatch batch) {
        for (Component component : children) {
            component.render(batch);
        }
    }
}