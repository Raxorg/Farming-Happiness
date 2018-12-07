package com.frontanilla.farminghappyness.core;

import com.badlogic.gdx.Game;
import com.frontanilla.farminghappyness.menus.MainMenu;
import com.frontanilla.farminghappyness.splash.SplashScreen;
import com.frontanilla.farminghappyness.utils.Assets;

public class FarmingGame extends Game {

    @Override
    public void create() {
        // Catch all fucking errors?
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t + " throws exception: " + e);
                e.printStackTrace();
            }
        });

        Assets.init();

        setScreen(new SplashScreen(this));
    }
}
