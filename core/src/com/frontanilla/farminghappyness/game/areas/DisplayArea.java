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

    private ResourceFrame moneyFrame, workerFrame;
    private float time;
    private SpriteBatch batch;
    private BitmapFont font;

    public DisplayArea() {
        // TODO, set the rectangles
        moneyFrame = new ResourceFrame(
                Color.FOREST,
                new Point(
                        0,
                        Gdx.graphics.getHeight() - RESOURCE_FRAME_HEIGHT),
                Assets.dollar);
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

    public void restart() {
        time = 0;
    }

    public void update(float delta, int money, int workers) {
        moneyFrame.setQuantity(money);
        workerFrame.setQuantity(workers); // TODO don't update each frame, update each change
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
        moneyFrame.render(staticBatch);
        workerFrame.render(staticBatch);
    }
}
