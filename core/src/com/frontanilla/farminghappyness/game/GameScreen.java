package com.frontanilla.farminghappyness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.farminghappyness.game.areas.DisplayArea;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.game.defenses.Turret;
import com.frontanilla.farminghappyness.game.other.Bullet;
import com.frontanilla.farminghappyness.game.units.Enemy;
import com.frontanilla.farminghappyness.tests.MyCamera;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_AREA_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_AREA_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_RANGE;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class GameScreen extends ScreenAdapter {

    private GameStuff gameStuff;
    private SpriteBatch batch, staticBatch;
    private GameMap map;
    private MyCamera camera;
    private GameLogic gameLogic;
    private DisplayArea displayArea; // TODO DELETE GameMap class

    public GameScreen() {
        gameStuff = new GameStuff();
        batch = new SpriteBatch();
        staticBatch = new SpriteBatch();
        map = new GameMap();
        displayArea = new DisplayArea(gameStuff);

        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new MyCamera(WORLD_WIDTH * 0.3f, WORLD_HEIGHT * 0.3f * (h / w));

        // Detect and process user input
        GameInput gameInput = new GameInput(this);
        Gdx.input.setInputProcessor(gameInput);

        // The logic of the game goes here
        gameLogic = new GameLogic(this);
    }

    public void render(float delta) {
        gameLogic.update(delta);

        batch.begin();
        map.render(batch);
        // TODO THIS IS TOO BIG TO BE HERE, RETHINK THIS PART, WE NEED TO DRAW THE RANGES BEHIND TURRETS
        for (Bullet b : gameLogic.getBullets()) {
            b.render(batch);
        }
        batch.setColor(Color.WHITE);
        for (Tile[] tileRows : map.getDefenseArea().getTiles()) {
            for (Tile tile : tileRows) {
                if (tile.getDefense() instanceof Turret) {
                    batch.draw(
                            Assets.rangeCircle,
                            tile.getDefense().getPosition().getX() - TURRET_RANGE + TURRET_WIDTH / 2,
                            tile.getDefense().getPosition().getY() - TURRET_RANGE + TURRET_WIDTH / 2,
                            TURRET_RANGE * 2,
                            TURRET_RANGE * 2);
                }
            }
        }
        // Render defenses
        for (int row = DEFENSE_AREA_ROWS - 1; row >= 0; row--) {
            for (int column = 0; column < DEFENSE_AREA_COLUMNS; column++) {
                if (getMap().getDefenseTiles()[row][column].getDefense() != null) {
                    getMap().getDefenseTiles()[row][column].getDefense().render(batch);
                }
            }
        }

        for (Enemy e : gameLogic.getEnemies()) {
            e.render(batch);
        }
        batch.end();
        displayArea.update(delta);
        displayArea.render(staticBatch);
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

    public GameStuff getGameStuff() {
        return gameStuff;
    }
}
