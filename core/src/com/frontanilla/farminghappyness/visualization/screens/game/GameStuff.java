package com.frontanilla.farminghappyness.visualization.screens.game;

import com.badlogic.gdx.graphics.Color;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.visualization.components.Container;
import com.frontanilla.farminghappyness.visualization.components.Label;
import com.frontanilla.farminghappyness.visualization.screens.Stuff;

public class GameStuff extends Stuff {

    // Components
    private Label turnLabel, moneyLabel;

    public GameStuff(GameConnector connector) {
        super(connector);
    }

    @Override
    public void init() {
        Container gameContainer = new Container(0, 0, connector.getScreen().getCamera().getWorldSize().x, connector.getScreen().getCamera().getWorldSize().y);
        // --------------------------------------
        // A label for the turn
        // --------------------------------------
        turnLabel = new Label(
                100,
                100,
                0,
                0,
                true,
                Assets.instance.displayAssets.timesSquare,
                1,
                Color.ORANGE,
                "Turn"
        );
        gameContainer.addChild(turnLabel);
        // --------------------------------------
        // A label for the money
        // --------------------------------------
        moneyLabel = new Label(
                200,
                200,
                0,
                0,
                true,
                Assets.instance.displayAssets.timesSquare,
                1,
                Color.ORANGE,
                "Money"
        );
        gameContainer.addChild(moneyLabel);
        components.add(gameContainer);
    }
}
