package com.frontanilla.farminghappyness.visualization.screens.loading;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.Util;
import com.frontanilla.farminghappyness.visualization.components.Container;
import com.frontanilla.farminghappyness.visualization.components.Image;
import com.frontanilla.farminghappyness.visualization.components.Label;
import com.frontanilla.farminghappyness.visualization.screens.Connector;
import com.frontanilla.farminghappyness.visualization.screens.Stuff;

public class LoadingStuff extends Stuff {

    private Container container; // TODO in other places maybe use more than 1 container

    public LoadingStuff(Connector connector) {
        super(connector);
    }

    @Override
    public void init() {
        Vector2 cameraWorldSize = connector.getScreen().getCamera().getWorldSize();
        // The container
        container = new Container(0, 0, cameraWorldSize.x, cameraWorldSize.y);
        // --------------------------------------
        // An image to show while loading
        // --------------------------------------
        float imageSize = container.getHeight() / 2;
        Image loadingImage = new Image(
                container.getWidth() / 2 - imageSize / 2,
                container.getHeight() / 2 - imageSize / 2,
                imageSize,
                imageSize,
                Assets.instance.displayAssets.loading
        ) {
            @Override
            public void update(float delta) {
                rotation += 50 * delta;
            }
        };
        container.addChild(loadingImage);
        // --------------------------------------
        // The text below the loading image
        // --------------------------------------
        Util.scaleFont(Assets.instance.displayAssets.timesSquare, Constants.LOADING_LABEL_TEXT_SCALE, true);
        Label loadingLabel = new Label(
                container.getWidth() / 2,
                container.getHeight() * 0.15f,
                0,
                0,
                true,
                Assets.instance.displayAssets.timesSquare,
                Constants.LOADING_LABEL_TEXT_SCALE,
                Color.WHITE,
                Constants.LOADING_TEXT
        );
        container.addChild(loadingLabel);
        // Add the container to the components array
        components.add(container);
    }
}
