package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.grid.Grid;

public class MapVisualizer extends Component {

    private Grid grid;

    public MapVisualizer(float x, float y, float width, float height, Grid grid) {
        super(x, y, width, height);
        this.grid = grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void update(float delta) {
        // Nothing happens by default
    }

    // TODO make the map be inside a square by default?
    @Override
    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        for (int row = 0; row < grid.getCells().length; row++) {
            for (int column = 0; column < grid.getCells()[row].length; column++) {
                batch.draw(
                        grid.getCells()[row][column].getTexture(),
                        bounds.x + 20 * column,
                        bounds.y + 20 * row,
                        20,
                        20
                );
            }
        }
    }
}