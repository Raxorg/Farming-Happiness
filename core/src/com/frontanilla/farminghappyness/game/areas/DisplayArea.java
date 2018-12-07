package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.components.Button;
import com.frontanilla.farminghappyness.components.LevelLabel;
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
    private LevelLabel levelLabel;

    public DisplayArea() {
        // Money resource frame
        moneyFrame = new ResourceFrame(
                Color.FOREST,
                RESOURCE_FRAME_X,
                RESOURCE_FRAME_Y,
                Assets.dollar);
        // Workers resource frame
        workerFrame = new ResourceFrame(
                Color.SALMON,
                RESOURCE_FRAME_X + RESOURCE_FRAME_WIDTH,
                RESOURCE_FRAME_Y,
                Assets.pickaxe
        );
        // Defenses menu
        toggleMenu = new ToggleMenu();
        // Ready button
        readyButton = new Button(
                Assets.readyButton,
                Gdx.graphics.getWidth() / 2 - Assets.readyButton.getRegionWidth(),
                0,
                Assets.readyButton.getRegionWidth() * 2,
                Assets.readyButton.getRegionHeight() * 2);
        readyButton.setVisible(true);
        // Level label
        levelLabel = new LevelLabel();
    }

    public void update(float delta, int money, int availableWorkers) {
        moneyFrame.setQuantity(money);
        workerFrame.setQuantity(availableWorkers);
        toggleMenu.update(delta, money, availableWorkers);
        levelLabel.update(delta);
    }

    public void render(SpriteBatch staticBatch) {
        moneyFrame.render(staticBatch);
        workerFrame.render(staticBatch);
        toggleMenu.render(staticBatch);
        readyButton.render(staticBatch);
        levelLabel.render(staticBatch);
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

    public LevelLabel getLevelLabel() {
        return levelLabel;
    }
}
