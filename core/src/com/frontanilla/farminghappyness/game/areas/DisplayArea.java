package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.GameStuff;
import com.frontanilla.farminghappyness.game.other.ResourceFrame;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Point;

import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_WIDTH;

public class DisplayArea {

    private GameStuff gameStuff;
    private ResourceFrame moneyFrame, workerFrame;

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
    }

    public void update(float delta) {
        moneyFrame.setQuantity(gameStuff.getMoney());
        workerFrame.setQuantity(gameStuff.getWorkers());
    }

    public void render(SpriteBatch staticBatch) {
        staticBatch.begin();
        moneyFrame.render(staticBatch);
        workerFrame.render(staticBatch);
        staticBatch.setColor(Color.BLUE);
        staticBatch.draw(
                Assets.triangle,
                Gdx.graphics.getWidth() / 2f - Assets.triangle.getRegionWidth() / 2f,
                0,
                Gdx.graphics.getWidth() / 10f,
                Gdx.graphics.getWidth() / 20f);
        staticBatch.end();
    }
}
