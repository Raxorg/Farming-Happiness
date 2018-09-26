package com.frontanilla.farminghappyness.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.visualization.screens.loading.LoadingConnector;

public class FarmingGame extends Game {

    public static String phoneID;

    public FarmingGame(FirestoreInterface firestoreInterface, String phoneIdentifier) {
        FirestoreDBConnection.getInstance().setFirestore(firestoreInterface);
        phoneID = phoneIdentifier;
    }

    public FarmingGame() {
    }

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

        // Load the loading screen assets
        AssetManager am = new AssetManager();
        Assets.instance.loadDisplayAssets(am);

        // Rock and roll
        new LoadingConnector(this);
    }
}
