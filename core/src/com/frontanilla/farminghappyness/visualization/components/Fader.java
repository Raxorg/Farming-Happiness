package com.frontanilla.farminghappyness.visualization.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.OnResultListener;

public class Fader extends Component {

    private boolean fadeIn;
    private float alpha;
    private OnResultListener listener;
    private boolean started;

    public Fader(float x, float y, float width, float height) {
        super(x, y, width, height);
        started = false;
    }

    public void start(boolean fadeIn, OnResultListener listener) {
        this.fadeIn = fadeIn;
        this.listener = listener;
        alpha = fadeIn ? 1 : 0;
        started = true;
    }

    private void finished() {
        listener.onResult(fadeIn);
        started = false;
    }

    @Override
    public void update(float delta) {
        if (started) {
            if (fadeIn) {
                alpha -= Constants.FADING_SPEED * delta;
                if (alpha <= 0) {
                    alpha = 0;
                    finished();
                }
            } else {
                alpha += Constants.FADING_SPEED * delta;
                if (alpha >= 1) {
                    alpha = 1;
                    finished();
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setColor(
                batch.getColor().r,
                batch.getColor().g,
                batch.getColor().b,
                alpha
        );
        batch.draw(
                Assets.instance.displayAssets.pixel,
                bounds.x,
                bounds.y,
                bounds.width / 2,
                bounds.height / 2,
                bounds.width,
                bounds.height,
                1,
                1,
                0// TODO rotation
        );
    }
}