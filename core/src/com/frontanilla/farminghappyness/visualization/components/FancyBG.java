package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.visualization.DynamicCamera;

public class FancyBG extends Component {

    private TextureRegion texture;
    private Vector2[] positions;
    private float size;
    private DynamicCamera camera;// TODO delete this line
    private int rows;

    public FancyBG(float x, float y, float width, float height,
                   TextureRegion texture, Color color, DynamicCamera camera, int rows) { // TODO camera is temp, delete
        super(x, y, width, height);
        this.texture = texture;
        this.color = color;
        this.camera = camera;
        this.rows = rows;
        init();
    }

    // TODO configurable spacing, speed? verify that rows is even?
    public void init() {
        size = bounds.height / rows;
        int columns = (int) ((bounds.width / 2) / size);
        float xSpacing = size + ((bounds.width + size) - size * (columns - 1)) / columns;
        float ySpacing = size + ((bounds.height + size) - size * rows) / (rows * 0.7f);

        positions = new Vector2[rows * columns];
        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++, index++) {
                float xPos;
                if (r % 2 != 0) {
                    xPos = -size + xSpacing / 2 + xSpacing * c + bounds.x;
                } else {
                    xPos = -size + xSpacing * c + bounds.x;
                }
                positions[index] = new Vector2(
                        xPos,
                        -size + ySpacing * r + bounds.y
                );
            }
        }
    }

    @Override
    public void update(float delta) {
        for (Vector2 position : positions) {
            position.x += Constants.FANCY_BG_SPEED * delta;
            position.y += Constants.FANCY_BG_SPEED * delta;
            if (position.x >= bounds.x + bounds.width + size / 2) {
                position.x = bounds.x - size * 1.5f;
            }
            if (position.y >= bounds.y + bounds.height + size / 4) {
                position.y = bounds.y - size * 1.25f;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setColor(color);
        batch.draw(
                Assets.instance.displayAssets.pixel,
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height
        );

        renderImages(batch, bounds.x, bounds.y, bounds.width, bounds.height, camera);
    }

    private void renderImages(SpriteBatch spriteBatch, float x, float y, float w, float h, DynamicCamera camera) {
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(x, y, w, h);
        ScissorStack.calculateScissors(camera, spriteBatch.getTransformMatrix(), clipBounds, scissors);
        boolean pop = ScissorStack.pushScissors(scissors);
        spriteBatch.setColor(color.r, color.g, color.b, color.a / 2);
        for (Vector2 position : positions) {
            spriteBatch.draw(
                    texture,
                    position.x,
                    position.y,
                    size / 2,
                    size / 2,
                    size,
                    size,
                    1,
                    1,
                    45
            );
        }

        spriteBatch.flush();
        if (pop) {
            ScissorStack.popScissors();
        }
    }
}