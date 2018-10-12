package com.frontanilla.farminghappyness.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frontanilla.farminghappyness.core.FarmingGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1400;
        config.height = config.width / 2;
        new LwjglApplication(new FarmingGame(), config);
    }
}
