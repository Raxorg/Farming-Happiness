package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.game.structures.Turret;
import com.frontanilla.farminghappyness.game.units.Enemy;
import com.frontanilla.farminghappyness.tests.MyCamera;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.TURRET_RANGE;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class GameScreen extends ScreenAdapter {

    private SpriteBatch batch, staticBatch;
    private GameMap map;
    private MyCamera camera;
    private GameInput gameInput;
    private GameLogic gameLogic;

    public GameScreen() {
        batch = new SpriteBatch();
        staticBatch = new SpriteBatch();
        map = new GameMap();

        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new MyCamera(WORLD_WIDTH * 0.3f, WORLD_HEIGHT * 0.3f * (h / w));

        // Detect and process user input
        gameInput = new GameInput(this);
        Gdx.input.setInputProcessor(gameInput);

        // The logic of the game goes here
        gameLogic = new GameLogic(this);
    }

    public void render(float delta) {
        gameLogic.update(delta);

        batch.begin();
        map.render(batch);
        // TODO THIS IS TOO BIG TO BE HERE, RETHINK THIS PART, WE NEED TO DRAW THE RANGES BEHIND TURRETS
        for (Tile[] tileRows : map.getDefenseArea().getTiles()) {
            for (Tile tile : tileRows) {
                if(tile.getContent() instanceof Turret) {
                    batch.draw(
                            Assets.rangeCircle,
                            tile.getContent().getPosition().getX() - TURRET_RANGE + TURRET_SIZE / 2,
                            tile.getContent().getPosition().getY() - TURRET_RANGE + TURRET_SIZE / 2,
                            TURRET_RANGE * 2,
                            TURRET_RANGE * 2);
                }
            }
        }
        for (Enemy e : gameLogic.getEnemies()) {
            e.render(batch);
        }
        for (Bullet b : gameLogic.getBullets()) {
            b.render(batch);
        }
        batch.end();
        staticBatch.begin();
        staticBatch.draw(
                Assets.triangle,
                Gdx.graphics.getWidth() / 2f - Assets.triangle.getRegionWidth() / 2f,
                0,
                Gdx.graphics.getWidth() / 10f,
                Gdx.graphics.getWidth() / 20f);
        staticBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.resize(width, height);
    }

    public MyCamera getCamera() {
        return camera;
    }

    public Batch getBatch() {
        return batch;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public GameMap getMap() {
        return map;
    }
}
