package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.components.Button;
import com.frontanilla.farminghappyness.components.TechTreePanel;

public class Laboratory extends Button {

    private TechTreePanel techTreePanel;
    private boolean showingTechTree;

    public Laboratory(TextureRegion texture, float x, float y, float w, float h) {
        super(texture, x, y, w, h);
        techTreePanel = new TechTreePanel();
        showingTechTree = false;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    public void renderTechTree(SpriteBatch batch) {
        if (showingTechTree) {
            techTreePanel.render(batch);
        }
    }

    public void toggleShowingTechTree() {
        showingTechTree = !showingTechTree;
    }
}
