package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.components.Button;
import com.frontanilla.farminghappyness.components.TechTreePanel;
import com.frontanilla.farminghappyness.game.entities.Progressable;
import com.frontanilla.farminghappyness.utils.Assets;

public class Laboratory extends Button {

    private TechTreePanel techTreePanel;
    private boolean showingTechTree;

    public Laboratory(TextureRegion texture, float x, float y, float w, float h) {
        super(texture, x, y, w, h);
        techTreePanel = new TechTreePanel();
        showingTechTree = false;
    }

    public void renderTechTree(SpriteBatch batch) {
        if (showingTechTree) {
            techTreePanel.render(batch);
        }
    }

    //----------------------------
    //      GETTERS & SETTERS
    //----------------------------


    public TechTreePanel getTechTreePanel() {
        return techTreePanel;
    }

    public boolean isShowingTechTree() {
        return showingTechTree;
    }

    public void toggleShowingTechTree() {
        showingTechTree = !showingTechTree;
    }
}
