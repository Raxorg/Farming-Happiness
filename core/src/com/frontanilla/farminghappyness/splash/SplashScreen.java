package com.frontanilla.farminghappyness.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.menus.MainMenu;
import com.frontanilla.farminghappyness.utils.Assets;

public class SplashScreen extends ScreenAdapter {

    private FarmingGame game;
    private SpriteBatch batch;
    private float alpha;
    private float logoWidth, logoHeight;
    private boolean fadeIn;

    public SplashScreen(FarmingGame game) {
        this.game = game;
        batch = new SpriteBatch();
        logoWidth = Assets.logo.getRegionWidth() * ((Gdx.graphics.getHeight() * 0.8f) / Assets.logo.getRegionHeight());
        logoHeight = Gdx.graphics.getHeight() * 0.8f;
        fadeIn = true;
    }

    @Override
    public void show() {
        Assets.music1.play();
    }

    private void update(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (fadeIn) {
            alpha += delta / 2.5f;
            if (alpha >= 1) {
                alpha = 1;
                fadeIn = false;
            }
        } else {
            alpha -= delta / 2.5f;
            if (alpha <= 0) {
                alpha = 0;
                game.setScreen(new MainMenu(game));
            }
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        batch.draw(
                Assets.logo,
                Gdx.graphics.getWidth() / 2 - logoWidth / 2,
                Gdx.graphics.getHeight() / 2 - logoHeight / 2,
                logoWidth,
                logoHeight);
        batch.end();
    }
}
