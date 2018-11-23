package com.frontanilla.farminghappyness.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.farminghappyness.components.Button;
import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.game.GameConnector;
import com.frontanilla.farminghappyness.utils.Assets;

public class MainMenu extends ScreenAdapter {

    private FarmingGame game;

    private Button playButton, creditsButton;

    private SpriteBatch batch;

    //private Animation<TextureRegion> animation;
    //private float time;

    public MainMenu(FarmingGame game) {
        this.game = game;

        float ratio = 37f / 100f;
        float width = Gdx.graphics.getWidth() / 5f;
        playButton = new Button(Assets.playButton, Gdx.graphics.getWidth() / 2 - width / 2, width * ratio, width, width * ratio);
        creditsButton = new Button(Assets.creditsButton, Gdx.graphics.getWidth() / 2 - width / 2, 0, width, width * ratio);

        //animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("animations/backgroundGIF.gif").read());
        //time = 0;

        batch = new SpriteBatch();
        // Detect and process user input
        MainMenuInput mainMenuInput = new MainMenuInput(this);
        Gdx.input.setInputProcessor(mainMenuInput);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //time+= delta;
        batch.begin();
        // Draw background
        //batch.draw(animation.getKeyFrame(time),
        batch.draw(Assets.mainMenuBackground,
                0,
                0,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        // Draw buttons
        playButton.render(batch);
        creditsButton.render(batch);
        batch.end();
    }

    public void touchDown(int screenX, int screenY) {
        if (playButton.contains(screenX, screenY)) {
            game.setScreen(new GameConnector());
        } else if (creditsButton.contains(screenX, screenY)) {
            // showingCredits = true
            game.setScreen(new GameConnector());
        }
    }
}
