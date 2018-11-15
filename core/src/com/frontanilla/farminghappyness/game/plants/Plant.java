package com.frontanilla.farminghappyness.game.plants;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.game.areas.Tile;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums.PlantType;

import static com.frontanilla.farminghappyness.utils.Constants.PLANT_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.TILE_SIZE;

public class Plant {

    private Rectangle bounds;
    private TextureRegion textureRegion;
    private PlantType plantType;
    private int buyCost, sellCost;

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
        float spacing = (TILE_SIZE - PLANT_SIZE) / 2;
        bounds = new Rectangle(tile.getX() + spacing, tile.getY() + spacing, PLANT_SIZE, PLANT_SIZE);
        textureRegion = plant.textureRegion;
        plantType = plant.plantType;
        buyCost = plant.buyCost;
        sellCost = plant.sellCost;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(
                textureRegion,
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
    }
}
