package com.frontanilla.farminghappyness.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frontanilla.farminghappyness.core.FarmingGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1000;
        config.height = 500;
        new LwjglApplication(new FarmingGame(), config);
    }
}
