package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;

public class GameInput extends InputAdapter {

    private GameConnector gameConnector;
    private Vector3 usefulVector;
    private float touchDownX, touchDownY;

    public GameInput(GameConnector gameConnector) {
        this.gameConnector = gameConnector;
        usefulVector = new Vector3();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if (pointer == 0) {
            gameConnector.getCamera().unproject(usefulVector.set(screenX, screenY, 0));
            touchDownX = usefulVector.x;
            touchDownY = usefulVector.y;
            gameConnector.getGameLogic().touchDown(usefulVector);
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer == 0) {
            gameConnector.getCamera().unproject(usefulVector.set(screenX, screenY, 0));
            float distX = usefulVector.x - touchDownX;
            float distY = usefulVector.y - touchDownY;
            gameConnector.getCamera().oneFingerPan(distX, distY);
            return true;
        }
        return false;
    }
}
