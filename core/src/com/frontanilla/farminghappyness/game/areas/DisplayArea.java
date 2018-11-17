package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.GameState;
import com.frontanilla.farminghappyness.game.other.ResourceFrame;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.SPAWN_TIME;

public class DisplayArea {

    private GameState gameState;
    private ResourceFrame moneyFrame, workerFrame;
    private float time;
    private SpriteBatch batch;
    private BitmapFont font;

    public DisplayArea(GameState gameState) {
        this.gameState = gameState;
        // TODO, set the rectangles
        moneyFrame = new ResourceFrame(
                Color.FOREST,
                new Point(
                        0,
                        Gdx.graphics.getHeight() - RESOURCE_FRAME_HEIGHT),
                Assets.dollar);
        moneyFrame.setQuantity(gameState.getMoney());
        workerFrame = new ResourceFrame(Color.SALMON,
                new Point(
                        RESOURCE_FRAME_WIDTH,
                        Gdx.graphics.getHeight() - RESOURCE_FRAME_HEIGHT),
                Assets.dollar
        );

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().scale(3);
        font.setColor(Color.RED);
    }

    public void update(float delta) {
        moneyFrame.setQuantity(gameState.getMoney());
        workerFrame.setQuantity(gameState.getWorkers());
        time += delta;
        if (time < SPAWN_TIME) {
            batch.begin();
            font.draw(
                    batch,
                    (int) (SPAWN_TIME + 1 - time) + "",
                    Gdx.graphics.getWidth() / 2,
                    Gdx.graphics.getHeight() - 10);
            batch.end();
        }
    }

    public void render(SpriteBatch staticBatch) {
        staticBatch.begin();
        moneyFrame.render(staticBatch);
        workerFrame.render(staticBatch);
        staticBatch.end();
    }
}
