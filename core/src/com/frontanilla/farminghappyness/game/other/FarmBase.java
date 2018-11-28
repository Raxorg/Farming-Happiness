package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.components.Button;
import com.frontanilla.farminghappyness.components.WorkersPanel;

public class FarmBase extends Button {

    private WorkersPanel workersPanel;
    private boolean showingWorkersPanel;

    public FarmBase(TextureRegion texture, float x, float y, float w, float h) {
        super(texture, x, y, w, h);
        workersPanel = new WorkersPanel();
        showingWorkersPanel = false;

    }

    public void update(int money) {
        workersPanel.update(money);
    }

    public void renderWorkersPanel(SpriteBatch batch) {
        if (showingWorkersPanel) {
            workersPanel.render(batch);
        }
    }

    //----------------------------
    //      GETTERS & SETTERS
    //----------------------------

    public WorkersPanel getWorkersPanel() {
        return workersPanel;
    }

    public boolean isShowingWorkersPanel() {
        return showingWorkersPanel;
    }

    public void toggleShowingWorkersPanel() {
        showingWorkersPanel = !showingWorkersPanel;
    }
}
