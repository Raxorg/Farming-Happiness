package com.frontanilla.farminghappyness.game.plants;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.LifeBar;
import com.frontanilla.farminghappyness.components.ProgressBar;
import com.frontanilla.farminghappyness.game.GameEntity;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.game.other.Progressable;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums.PlantType;

import static com.frontanilla.farminghappyness.utils.Constants.PLANT_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.PLANT_TILE_SPACING;

public class Plant extends Progressable {

    // Structure
    private TextureRegion textureRegion;
    // Plant
    private PlantType plantType;
    private int buyCost, sellCost;
    // Logic
    private float time;
    private ProgressBar progressBar;
    // Plants
    public static Plant ELSKER = new Plant(PlantType.ELSKER, 1, 2);
    public static Plant GRA = new Plant(PlantType.GRA, 1, 2);
    public static Plant KOCHAM = new Plant(PlantType.KOCHAM, 1, 2);
    public static Plant SZERELEM = new Plant(PlantType.SZERELEM, 1, 2);
    public static Plant ELSKA = new Plant(PlantType.ELSKA, 1, 3);
    public static Plant AYARN = new Plant(PlantType.AYARN, 1, 3);
    public static Plant SEVIYORUM = new Plant(PlantType.SEVIYORUM, 1, 3);
    public static Plant MILESTIBA = new Plant(PlantType.MILESTIBA, 1, 4);
    public static Plant RAKKAUS = new Plant(PlantType.RAKKAUS, 1, 4);
    public static Plant KAERLIGHED = new Plant(PlantType.KAERLIGHED, 1, 5);

    private Plant(PlantType plantType, int buyCost, int sellCost) {
        super(null);
        switch (plantType) {
            case ELSKER:
                textureRegion = Assets.plantTest;
                break;
            case GRA:
                textureRegion = Assets.plantTest;
                break;
            case KOCHAM:
                textureRegion = Assets.plantTest;
                break;
            case SZERELEM:
                textureRegion = Assets.plantTest;
                break;
            case ELSKA:
                textureRegion = Assets.plantTest;
                break;
            case AYARN:
                textureRegion = Assets.plantTest;
                break;
            case SEVIYORUM:
                textureRegion = Assets.plantTest;
                break;
            case MILESTIBA:
                textureRegion = Assets.plantTest;
                break;
            case RAKKAUS:
                textureRegion = Assets.plantTest;
                break;
            case KAERLIGHED:
                textureRegion = Assets.plantTest;
                break;
        }
        this.plantType = plantType;
        this.buyCost = buyCost;
        this.sellCost = sellCost;
    }

    public Plant(Plant plant, Tile tile) {
        super(new Rectangle(tile.getX() + PLANT_TILE_SPACING, tile.getY() + PLANT_TILE_SPACING, PLANT_SIZE, PLANT_SIZE));
        // Structure
        textureRegion = plant.textureRegion;
        // Plant
        plantType = plant.plantType;
        buyCost = plant.buyCost;
        sellCost = plant.sellCost;
        // Logic
        time = 0;
        progressBar = new ProgressBar(this);
    }

    public void update(float delta) {
        time += delta;
        // TODO UPDATE STATUS BAR
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                textureRegion,
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
        progressBar.render(batch);
    }
}
