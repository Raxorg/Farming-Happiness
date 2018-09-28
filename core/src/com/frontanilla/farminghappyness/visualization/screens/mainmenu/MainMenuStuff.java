package com.frontanilla.farminghappyness.visualization.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.Util;
import com.frontanilla.farminghappyness.visualization.components.Button;
import com.frontanilla.farminghappyness.visualization.components.Container;
import com.frontanilla.farminghappyness.visualization.components.FancyBG;
import com.frontanilla.farminghappyness.visualization.components.Image;
import com.frontanilla.farminghappyness.visualization.components.Label;
import com.frontanilla.farminghappyness.visualization.components.TextButton;
import com.frontanilla.farminghappyness.visualization.screens.Connector;
import com.frontanilla.farminghappyness.visualization.screens.Stuff;

public class MainMenuStuff extends Stuff {

    // Stuff
    private Label messageLabel;
    private Image loadingImage;
    private TextButton playerNameButton, joinBattleButton;
    private Button redButton, blueButton, greenButton, yellowButton;

    public MainMenuStuff(Connector connector) {
        super(connector);
    }

    @Override
    public void init() {
        // A container for all the components
        Container container = new Container(0, 0, connector.getScreen().getCamera().getWorldSize().x, connector.getScreen().getCamera().getWorldSize().y);
        // Some common data
        float colorButtonsStartingX = container.getWidth() / 2 - container.getHeight() / 2;
        float colorButtonsStartingY = container.getHeight() * 0.45f;
        float colorButtonsSize = container.getHeight() * 0.15f;
        float colorButtonsSpacing = (container.getHeight() - 4 * colorButtonsSize) / 3;
        // --------------------------------------
        // The fancy Background
        // --------------------------------------
        FancyBG fancyBG = new FancyBG(
                0,
                0,
                container.getWidth(),
                container.getHeight(),
                Assets.instance.gameAssets.wall3,
                Color.PURPLE,
                connector.getScreen().getCamera(),
                14
        );
        container.addChild(fancyBG);
        // --------------------------------------
        // The title
        // --------------------------------------
        Util.scaleFont(Assets.instance.displayAssets.timesSquare, 2, true);
        Label title = new Label(
                container.getWidth() / 2,
                container.getHeight() * 0.95f,
                0,
                0,
                true,
                Assets.instance.displayAssets.timesSquare,
                2,
                Color.WHITE,
                Constants.MAIN_MENU_TITLE
        );
        container.addChild(title);
        // --------------------------------------
        // The message label
        // --------------------------------------
        Util.scaleFont(Assets.instance.displayAssets.timesSquare, 1.5f, true);
        messageLabel = new Label(
                container.getWidth() / 2,
                container.getHeight() * 0.75f,
                0,
                0,
                true,
                Assets.instance.displayAssets.timesSquare,
                1.5f,
                Color.WHITE,
                Constants.INITIAL_MENU_MESSAGE
        );
        messageLabel.setCentered(true);
        container.addChild(messageLabel);
        // --------------------------------------
        // Loading image
        // --------------------------------------
        loadingImage = new Image(
                0,
                0,
                container.getHeight() / 10,
                container.getHeight() / 10,
                Assets.instance.displayAssets.loading
        ) {
            @Override
            public void update(float delta) {
                rotation += 150 * delta;
            }
        };
        loadingImage.setVisible(false);
        container.addChild(loadingImage);
        // --------------------------------------
        // Player name button
        // --------------------------------------
        playerNameButton = new TextButton(
                container.getWidth() * 0.5f - (container.getHeight() * 0.5f) / 2,
                container.getHeight() * 0.25f,
                container.getHeight() * 0.5f,
                container.getHeight() * 0.1f,
                Assets.instance.displayAssets.buttonFrame5,
                Color.WHITE,
                false,
                Assets.instance.displayAssets.timesSquare,
                1,
                Color.BLACK,
                "Unnamed Newbie"
        ) {
            @Override
            public void onTap() {
                Gdx.input.getTextInput(new Input.TextInputListener() {
                    @Override
                    public void input(String userText) {
                        setText(userText.substring(0, Math.min(userText.length(), 14)));
                    }

                    @Override
                    public void canceled() {
                        setText("Unnamed Newbie");
                    }
                }, "Player name", "", "Unnamed Newbie");
            }
        };
        container.addChild(playerNameButton);
        // --------------------------------------
        // Join battle button
        // --------------------------------------
        joinBattleButton = new TextButton(
                container.getWidth() * 0.5f - (container.getHeight() * 0.5f) / 2,
                container.getHeight() * 0.1f,
                container.getHeight() * 0.5f,
                container.getHeight() * 0.1f,
                Assets.instance.displayAssets.buttonFrame5,
                Color.DARK_GRAY,
                true,
                Assets.instance.displayAssets.timesSquare,
                1,
                Color.WHITE,
                "Join battle"
        ) {
            @Override
            public void onTap() {
                ((MainMenuLogic) connector.getLogic()).joinButtonPressed();
            }
        };
        container.addChild(joinBattleButton);
        // --------------------------------------
        // Select red
        // --------------------------------------
        redButton = new Button(
                colorButtonsStartingX,
                colorButtonsStartingY,
                colorButtonsSize,
                colorButtonsSize,
                Assets.instance.displayAssets.buttonFrame1,
                Color.RED,
                true
        ) {
            @Override
            public void onTap() {
                ((MainMenuLogic) connector.getLogic()).colorButtonTapped(this);
            }
        };
        container.addChild(redButton);
        // --------------------------------------
        // Select blue
        // --------------------------------------
        blueButton = new Button(
                colorButtonsStartingX + colorButtonsSpacing + colorButtonsSize,
                colorButtonsStartingY,
                colorButtonsSize,
                colorButtonsSize,
                Assets.instance.displayAssets.buttonFrame1,
                Color.BLUE,
                true
        ) {
            @Override
            public void onTap() {
                ((MainMenuLogic) connector.getLogic()).colorButtonTapped(this);
            }
        };
        container.addChild(blueButton);
        // --------------------------------------
        // Select green
        // --------------------------------------
        greenButton = new Button(
                colorButtonsStartingX + colorButtonsSpacing * 2 + colorButtonsSize * 2,
                colorButtonsStartingY,
                colorButtonsSize,
                colorButtonsSize,
                Assets.instance.displayAssets.buttonFrame1,
                Color.GREEN,
                true
        ) {
            @Override
            public void onTap() {
                ((MainMenuLogic) connector.getLogic()).colorButtonTapped(this);
            }
        };
        container.addChild(greenButton);
        // --------------------------------------
        // Select yellow
        // --------------------------------------
        yellowButton = new Button(
                colorButtonsStartingX + colorButtonsSpacing * 3 + colorButtonsSize * 3,
                colorButtonsStartingY,
                colorButtonsSize,
                colorButtonsSize,
                Assets.instance.displayAssets.buttonFrame1,
                Color.YELLOW,
                true
        ) {
            @Override
            public void onTap() {
                ((MainMenuLogic) connector.getLogic()).colorButtonTapped(this);
            }
        };
        container.addChild(yellowButton);
        // Final touch
        components.add(container);
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public Image getLoadingImage() {
        return loadingImage;
    }

    public TextButton getPlayerNameButton() {
        return playerNameButton;
    }

    public DelayedRemovalArray<Button> getColorButtons() {
        DelayedRemovalArray<Button> buttons = new DelayedRemovalArray<>();
        buttons.add(redButton);
        buttons.add(blueButton);
        buttons.add(greenButton);
        buttons.add(yellowButton);
        return buttons;
    }

    public DelayedRemovalArray<Button> getButtons() {
        DelayedRemovalArray<Button> buttons = new DelayedRemovalArray<>();
        buttons.add(playerNameButton);
        buttons.add(joinBattleButton);
        buttons.add(redButton);
        buttons.add(blueButton);
        buttons.add(greenButton);
        buttons.add(yellowButton);
        return buttons;
    }
}
