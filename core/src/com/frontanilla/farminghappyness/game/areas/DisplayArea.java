package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.components.Button;
import com.frontanilla.farminghappyness.components.ResourceFrame;
import com.frontanilla.farminghappyness.components.ToggleMenu;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_X;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_Y;

public class DisplayArea {

    private ResourceFrame moneyFrame, workerFrame;
    private ToggleMenu toggleMenu;
    private Button readyButton;

    public DisplayArea() {
        // Money resource frame
        moneyFrame = new ResourceFrame(
                Color.FOREST,
                RESOURCE_FRAME_X,
                RESOURCE_FRAME_Y,
                Assets.dollar);
        // Workers resource frame
        workerFrame = new ResourceFrame(Color.SALMON,
                RESOURCE_FRAME_X + RESOURCE_FRAME_WIDTH,
                RESOURCE_FRAME_Y,
                Assets.dollar
        );
        // Defenses menu
        toggleMenu = new ToggleMenu();
        // Ready button
        readyButton = new Button(
                Assets.readyButton,
                Gdx.graphics.getWidth() / 2 - Assets.readyButton.getRegionWidth() / 2,
                0,
                Assets.readyButton.getRegionWidth(),
                Assets.readyButton.getRegionHeight());
    }

    public void update(float delta, int money, int workers) {
        moneyFrame.setQuantity(money);
        workerFrame.setQuantity(workers);
        toggleMenu.update(delta, money);
    }

    public void render(SpriteBatch staticBatch) {
        moneyFrame.render(staticBatch);
        workerFrame.render(staticBatch);
        toggleMenu.render(staticBatch);
        readyButton.render(staticBatch);
    }

    //----------------------------
    //      GETTERS & SETTERS
    //----------------------------

    public ToggleMenu getToggleMenu() {
        return toggleMenu;
    }

    public Button getReadyButton() {
        return readyButton;
    }
}
