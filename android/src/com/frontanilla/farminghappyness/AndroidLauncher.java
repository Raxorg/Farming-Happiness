package com.frontanilla.farminghappyness;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.frontanilla.farminghappyness.core.FarmingGame;

public class AndroidLauncher extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        // Get unique identifier for the phone
        @SuppressLint("HardwareIds")
        String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        // Your screen phone will never go off
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //creates an mainMenu of firebase connection
        FirestoreDB firebaseConnection = new FirestoreDB();

        initialize(new FarmingGame(firebaseConnection, android_id), config);
    }
}
