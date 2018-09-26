package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.utils.Util;

public class StaticInfo extends Component {

    private Color moneyTextColor, turnTextColor;
    private String message, turnText, moneyText;
    private float messageX, turnTextX, moneyTextX;
    private BitmapFont font;

    public StaticInfo(float x, float y, BitmapFont font) {
        super(x, y, 0, 0); // TODO calculate width and height
        moneyTextColor = Color.WHITE;
        turnTextColor = Color.WHITE;
        message = "";
        turnText = "";
        moneyText = "";
        this.font = font;
    }

    @Override
    public void render(SpriteBatch batch) {
        font.setColor(moneyTextColor);
        font.draw(batch, message, messageX, Gdx.graphics.getHeight());
        font.setColor(turnTextColor);
        font.draw(batch, turnText, turnTextX, Gdx.graphics.getHeight() * 0.9f);
        font.setColor(moneyTextColor);
        font.draw(batch, moneyText, moneyTextX, Gdx.graphics.getHeight() * 0.8f);
    }

    public void setMessage(String message) { // TODO Make this method useful
        this.message = message;
        Util.scaleFont(font, 1, false);
        messageX = Gdx.graphics.getWidth() / 2 - Util.getTextSize(message, font).x / 2;
    }

    public void updateTurnText(int turn, DelayedRemovalArray<Player> players) {
        for (Player player : players) {
            if (player.getTurn() == turn) {
                turnText = player.getName() + "'s turn";
                turnTextColor = player.getColor();
                break;
            }
        }
        Util.scaleFont(font, 1, false);
        turnTextX = Gdx.graphics.getWidth() / 2 - Util.getTextSize(turnText, font).x / 2;
    }

    public void updateMoneyText(DelayedRemovalArray<Player> players) {
        for (Player player : players) {
            if (player.getPhoneID().equals(FarmingGame.phoneID)) {
                moneyText = "$: " + player.getMoney();
                moneyTextColor = player.getColor();
                break;
            }
        }
        Util.scaleFont(font, 1, false);
        moneyTextX = Gdx.graphics.getWidth() / 2 - Util.getTextSize(moneyText, font).x / 2;
    }
}
