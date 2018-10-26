package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.GameStuff;
import com.frontanilla.farminghappyness.game.other.Button;
import com.frontanilla.farminghappyness.game.other.ResourceFrame;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_BUTTON_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.SPAWN_TIME;

public class DisplayArea {

    private GameStuff gameStuff;
    private ResourceFrame moneyFrame, workerFrame;
    private float time;
    private SpriteBatch batch;
    private BitmapFont font;

    public DisplayArea(GameStuff gameStuff) {
        this.gameStuff = gameStuff;
        // TODO, set the rectangles
        moneyFrame = new ResourceFrame(
                Color.FOREST,
                new Point(
                        0,
                        Gdx.graphics.getHeight() - RESOURCE_FRAME_HEIGHT),
                Assets.dollar);
        moneyFrame.setQuantity(gameStuff.getMoney());
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
        moneyFrame.setQuantity(gameStuff.getMoney());
        workerFrame.setQuantity(gameStuff.getWorkers());
        time += delta;
        if (time < SPAWN_TIME) {
            batch.begin();
            font.draw(
                    batch,
                    (int) (10 - time) + "",
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
