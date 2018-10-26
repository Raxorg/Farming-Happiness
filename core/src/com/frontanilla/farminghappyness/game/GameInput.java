package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;

import static com.frontanilla.farminghappyness.utils.Enums.ConstructionState.NONE;

public class GameInput extends InputAdapter {

    private GameScreen gameScreen;
    private Vector3 usefulVector;
    private float touchDownX, touchDownY;

    public GameInput(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        usefulVector = new Vector3();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if (pointer == 0) {
            gameScreen.getCamera().unproject(usefulVector.set(screenX, screenY, 0));
            touchDownX = usefulVector.x;
            touchDownY = usefulVector.y;
            gameScreen.getGameLogic().touchDown(usefulVector, button);
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer == 0) {
            gameScreen.getCamera().unproject(usefulVector.set(screenX, screenY, 0));
            float distX = usefulVector.x - touchDownX;
            float distY = usefulVector.y - touchDownY;
            gameScreen.getCamera().oneFingerPan(distX, distY);
            return true;
        }
        return false;
    }
}
