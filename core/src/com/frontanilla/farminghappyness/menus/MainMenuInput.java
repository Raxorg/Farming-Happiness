package com.frontanilla.farminghappyness.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class MainMenuInput extends InputAdapter {

    private MainMenu mainMenu;

    public MainMenuInput(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = Gdx.graphics.getHeight() - screenY;
        mainMenu.touchDown(screenX, screenY);
        return true;
    }
}
