package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.components.ToggleMenu;
import com.frontanilla.farminghappyness.components.ResourceFrame;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.RESOURCE_FRAME_Y;

public class DisplayArea {

    private ResourceFrame moneyFrame, workerFrame;
    private ToggleMenu toggleMenu;

    public DisplayArea() {
        // Money resource frame
        moneyFrame = new ResourceFrame(
                Color.FOREST,
                0,
                RESOURCE_FRAME_Y,
                Assets.dollar);
        // Workers resource frame
        workerFrame = new ResourceFrame(Color.SALMON,
                RESOURCE_FRAME_WIDTH,
                RESOURCE_FRAME_Y,
                Assets.dollar
        );
        // Defenses menu
        toggleMenu = new ToggleMenu();
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
    }

    //----------------------------
    //      GETTERS & SETTERS
    //----------------------------

    public ToggleMenu getToggleMenu() {
        return toggleMenu;
    }
}
