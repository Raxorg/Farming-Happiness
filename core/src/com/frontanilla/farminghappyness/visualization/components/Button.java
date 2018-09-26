package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Button extends Component {

    private TextureRegion texture;
    private boolean selected, hover, hooverButton;
    private Color darkColor;

    protected Button(float x, float y, float width, float height,
                     TextureRegion texture, Color color, boolean hooverButton) {
        super(x, y, width, height);
        this.texture = texture;
        this.hooverButton = hooverButton;
        this.color = color;
        if (hooverButton) {
            darkColor = color.cpy().mul(Color.GRAY);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if ((!hover && !selected) && hooverButton) {
            batch.setColor(darkColor);
        } else {
            batch.setColor(color);
        }
        batch.draw(
                texture,
                bounds.x,
                bounds.y,
                bounds.width / 2,
                bounds.height / 2,
                bounds.width,
                bounds.height,
                1,
                1,
                0 // TODO rotated buttons TODO fancy effect at the menus :0 needs implementation of polygons instead of rectangles
        );
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setHover(boolean hoover) {
        this.hover = hoover;
    }

    public abstract void onTap();
}