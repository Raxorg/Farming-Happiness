package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Image extends Component {

    protected TextureRegion texture;
    protected float rotation;

    public Image(float x, float y, float width, float height, TextureRegion texture) {
        super(x, y, width, height);
        this.texture = texture;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setColor(color);
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
                rotation
        );
        super.render(batch);
    }
}