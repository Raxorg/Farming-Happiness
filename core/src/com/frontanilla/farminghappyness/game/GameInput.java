package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.frontanilla.farminghappyness.tests.MyCamera;

public class GameInput extends InputAdapter {

    private GameScreen gameScreen;
    private Vector3 usefulVector;

    public GameInput(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        usefulVector = new Vector3();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        gameScreen.getCamera().unproject(usefulVector.set(screenX, screenY, 0));
        gameScreen.getGameLogic().touchDown(usefulVector);
        return true;
    }
}
