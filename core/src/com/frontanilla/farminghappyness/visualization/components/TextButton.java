package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.utils.Util;

public abstract class TextButton extends Button {

    protected String text;
    protected BitmapFont font;
    protected float textScale, textX, textY;
    protected Color fontColor;

    public TextButton(float x, float y, float width, float height, TextureRegion texture,
                      Color color, boolean hooverButton, BitmapFont font,
                      float textScale, Color fontColor, String initialText) {
        super(x, y, width, height, texture, color, hooverButton);
        this.font = font;
        this.textScale = textScale;
        this.fontColor = fontColor;
        setText(initialText);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        Util.scaleFont(font, textScale, true);
        textX = bounds.x + bounds.width / 2 - Util.getTextSize(text, font).x / 2;
        textY = bounds.y + bounds.height / 2 + Util.getTextSize(text, font).y / 2;
    }

    @Override
    public void render(SpriteBatch batch) {
        // Draw BG button
        super.render(batch);
        // Draw the text on top
        if (text != null && font != null) {
            Util.scaleFont(font, textScale, true);
            font.setColor(fontColor);
            font.draw(
                    batch,
                    text,
                    textX,
                    textY
            );
        }
    }
}