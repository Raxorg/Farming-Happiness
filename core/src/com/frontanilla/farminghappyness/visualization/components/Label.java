package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Util;

public class Label extends Component {

    protected String text;
    protected BitmapFont font;
    protected float textScale, textX, textY;

    public Label(float x, float y, float width, float height, boolean centered,
                 BitmapFont font, float textScale, Color color, String text) {
        super(x, y, width, height);
        this.centered = centered;
        this.font = font;
        this.textScale = textScale;
        this.color = color;
        setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        if (centered) {
            Util.scaleFont(font, textScale, true);
            textX = bounds.x + bounds.width / 2 - Util.getTextSize(text, font).x / 2;
            textY = bounds.y + bounds.height / 2 + Util.getTextSize(text, font).y / 2;
        }
    }

    @Override
    public void update(float delta) {
        // Nothing happens by default
    }

    @Override
    public void render(SpriteBatch batch) {
        Util.scaleFont(font, textScale, true);
        font.setColor(color);
        font.draw(
                batch,
                text,
                textX,
                textY
        );
    }
}