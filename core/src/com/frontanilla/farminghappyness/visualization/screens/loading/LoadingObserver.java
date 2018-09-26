package com.frontanilla.farminghappyness.visualization.screens.loading;

import com.badlogic.gdx.assets.AssetManager;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.visualization.screens.Observer;

public class LoadingObserver extends Observer {

    // Logic related
    private AssetManager assetManager;
    private boolean doneLoading;
    private float time;

    public LoadingObserver(LoadingConnector connector) {
        super(connector);
    }

    public void init() {
        assetManager = new AssetManager();
        Assets.instance.loadAllAssets(assetManager);
        doneLoading = false;
        time = 0;
    }

    @Override
    public void update(float delta) {
        time += delta;
        if (assetManager.update() && !doneLoading && time > 2) {
            doneLoading = true;
            Assets.instance.getAllAssets();
            ((LoadingScreen) connector.getScreen()).enterMainMenu();
        }
    }

    @Override
    public void onScreenShow() {
        // TODO
    }
}