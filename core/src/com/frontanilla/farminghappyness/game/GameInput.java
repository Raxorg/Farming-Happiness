package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;

public class GameInput extends InputAdapter {

    private GameScreen gameScreen;
    private Vector3 usefulVector;
    private float touchDownX, touchDownY, lastFrameX, lastFrameY;

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
            lastFrameX = touchDownX;
            lastFrameY = touchDownY;
            gameScreen.getGameLogic().touchDown(usefulVector, button);
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer == 0) {
            gameScreen.getCamera().unproject(usefulVector.set(screenX, screenY, 0));
            float distX = usefulVector.x - lastFrameX;
            float distY = usefulVector.y - lastFrameY;
            gameScreen.getCamera().oneFingerPan(distX, distY);
            lastFrameX = usefulVector.x;
            lastFrameY = usefulVector.y;
            return true;
        }
        return false;
    }
}
