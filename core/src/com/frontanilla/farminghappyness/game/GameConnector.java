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
import com.frontanilla.farminghappyness.game.other.Button;
import com.frontanilla.farminghappyness.game.units.Enemy;
import com.frontanilla.farminghappyness.tests.MyCamera;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_AREA_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_AREA_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_BUTTON_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_X;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_Y;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_RANGE;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class GameConnector extends ScreenAdapter {

    private GameState gameState;
    private GameLogic gameLogic;
    private SpriteBatch batch, staticBatch;
    private GameRenderer renderer;
    private MyCamera camera;
    private DisplayArea displayArea; // TODO DELETE GameRenderer class
    private Button towerButton, wallButton, trapButton;

    public GameConnector() {
        gameState = new GameState();
        batch = new SpriteBatch();
        staticBatch = new SpriteBatch();
        renderer = new GameRenderer();
        displayArea = new DisplayArea(gameState);

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

        // Defense buttons
        towerButton = new Button(
                Assets.turret,
                FARMING_AREA_X - FARMING_AREA_WIDTH,
                FARMING_AREA_Y + DEFENSE_BUTTON_SIZE / 2f,
                DEFENSE_BUTTON_SIZE,
                DEFENSE_BUTTON_SIZE);
        wallButton = new Button(
                Assets.wall,
                FARMING_AREA_X - FARMING_AREA_WIDTH + DEFENSE_BUTTON_SIZE * 1.5f,
                FARMING_AREA_Y + DEFENSE_BUTTON_SIZE / 2,
                DEFENSE_BUTTON_SIZE,
                DEFENSE_BUTTON_SIZE);
        trapButton = new Button(
                Assets.trap,
                FARMING_AREA_X - FARMING_AREA_WIDTH + DEFENSE_BUTTON_SIZE * 3f,
                FARMING_AREA_Y + DEFENSE_BUTTON_SIZE / 2f,
                DEFENSE_BUTTON_SIZE,
                DEFENSE_BUTTON_SIZE);
    }

    public void render(float delta) {
        gameLogic.update(delta);

        batch.begin();
        renderer.render(batch);
        // TODO THIS IS TOO BIG TO BE HERE, RETHINK THIS PART, WE NEED TO DRAW THE RANGES BEHIND TURRETS
        for (Bullet b : gameState.getBullets()) {
            b.render(batch);
        }
        batch.setColor(Color.WHITE);
        for (Tile[] tileRows : renderer.getDefenseArea().getTiles()) {
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
                if (getRenderer().getDefenseTiles()[row][column].getDefense() != null) {
                    getRenderer().getDefenseTiles()[row][column].getDefense().render(batch);
                }
            }
        }

        for (Enemy e : gameState.getEnemies()) {
            e.render(batch);
        }
        batch.end();
        displayArea.update(delta);
        displayArea.render(staticBatch);
        batch.begin();
        towerButton.render(batch);
        wallButton.render(batch);
        trapButton.render(batch);
        batch.end();
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

    public GameRenderer getRenderer() {
        return renderer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public DisplayArea getDisplayArea() {
        return displayArea;
    }

    public Button getTurretButton() {
        return towerButton;
    }

    public Button getWallButton() {
        return wallButton;
    }

    public Button getTrapButton() {
        return trapButton;
    }
}
