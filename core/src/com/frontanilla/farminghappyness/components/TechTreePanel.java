package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.TECH_TREE_PANEL_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TECH_TREE_PANEL_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.TECH_TREE_PANEL_X;
import static com.frontanilla.farminghappyness.utils.Constants.TECH_TREE_PANEL_Y;

public class TechTreePanel {

    private NinePatcher panel;
    private DelayedRemovalArray<Button> buttons;
    private Button closeButton;

    public TechTreePanel() {
        panel = new NinePatcher(
                Assets.toggleMenuPanel,
                10f,
                1);
        panel.setPosition(TECH_TREE_PANEL_X, TECH_TREE_PANEL_Y);
        panel.setWidth(TECH_TREE_PANEL_WIDTH);
        panel.setHeight(TECH_TREE_PANEL_HEIGHT);
        panel.setColor(Color.NAVY);
    }

    public void render(SpriteBatch batch) {
        panel.render(batch);
    }
}
