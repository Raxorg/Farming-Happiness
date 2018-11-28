package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.TECH_TREE_PANEL_CLOSE_BUTTON_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.TECH_TREE_PANEL_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TECH_TREE_PANEL_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.TECH_TREE_PANEL_X;
import static com.frontanilla.farminghappyness.utils.Constants.TECH_TREE_PANEL_Y;

public class TechTreePanel {

    private NinePatcher panel;
    //private DelayedRemovalArray<Button> buttons;
    private Button closeButton, researchButton;

    public TechTreePanel() {
        // Panel
        panel = new NinePatcher(
                Assets.toggleMenuPanel,
                10f,
                1);
        panel.setPosition(TECH_TREE_PANEL_X, TECH_TREE_PANEL_Y);
        panel.setWidth(TECH_TREE_PANEL_WIDTH);
        panel.setHeight(TECH_TREE_PANEL_HEIGHT);
        panel.setColor(Color.NAVY);
        // Plant buttons
        // Research button
        researchButton = new Button(
                Assets.research,
                TECH_TREE_PANEL_X + TECH_TREE_PANEL_WIDTH / 2 - TECH_TREE_PANEL_WIDTH / 4f,
                TECH_TREE_PANEL_Y + TECH_TREE_PANEL_HEIGHT / 2,
                TECH_TREE_PANEL_WIDTH / 4f,
                TECH_TREE_PANEL_WIDTH / 8f);
        // Close button
        closeButton = new Button(
                Assets.closeButton,
                TECH_TREE_PANEL_X - TECH_TREE_PANEL_CLOSE_BUTTON_SIZE / 2,
                TECH_TREE_PANEL_Y + TECH_TREE_PANEL_HEIGHT - TECH_TREE_PANEL_CLOSE_BUTTON_SIZE / 2,
                TECH_TREE_PANEL_CLOSE_BUTTON_SIZE,
                TECH_TREE_PANEL_CLOSE_BUTTON_SIZE);
    }

    public void render(SpriteBatch batch) {
        panel.render(batch);
        researchButton.render(batch);
        batch.setColor(Color.WHITE);
        batch.draw(
                Assets.researchCost,
                TECH_TREE_PANEL_X + TECH_TREE_PANEL_WIDTH / 2,
                TECH_TREE_PANEL_Y + TECH_TREE_PANEL_HEIGHT / 2,
                TECH_TREE_PANEL_WIDTH / 4f,
                TECH_TREE_PANEL_WIDTH / 8f);
        closeButton.render(batch);
    }

    //----------------------------
    //      GETTERS & SETTERS
    //----------------------------


    public Button getCloseButton() {
        return closeButton;
    }

    public Button getResearchButton() {
        return researchButton;
    }
}
