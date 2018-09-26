package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CellInfo extends Component {

    private int health;
    private BitmapFont font;

    public CellInfo(float x, float y, int health, BitmapFont font) {
        super(x, y, 0, 0); // TODO calculate width & height
        this.health = health;
        this.font = font;
        visible = false;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (visible) {
            font.draw(
                    batch,
                    health + "",
                    bounds.x,
                    bounds.y
            );
            super.render(batch);
        }
    }

    // TODO MAKE THE NEXT METHOD USEFUL

    public void setX(float x) {
        bounds.x = x;
    }

    public void setY(float y) {
        bounds.y = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
