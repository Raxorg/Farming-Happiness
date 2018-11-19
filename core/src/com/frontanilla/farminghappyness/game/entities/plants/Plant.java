package com.frontanilla.farminghappyness.game.entities.plants;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.ButtonTile;
import com.frontanilla.farminghappyness.game.entities.Progressable;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums.PlantType;

import static com.frontanilla.farminghappyness.utils.Constants.PLANT_PRODUCTION_TIME;
import static com.frontanilla.farminghappyness.utils.Constants.PLANT_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.PLANT_TILE_SPACING;

public class Plant extends Progressable {

    // Structure
    private TextureRegion textureRegion;
    // Plant
    private PlantType plantType;
    private int buyCost, sellCost;
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
        super(new Rectangle(), PLANT_PRODUCTION_TIME);
        switch (plantType) {
            case ELSKER:
                textureRegion = Assets.elsker;
                break;
            case GRA:
                textureRegion = Assets.gra;
                break;
            case KOCHAM:
                textureRegion = Assets.kocham;
                break;
            case SZERELEM:
                textureRegion = Assets.szerelem;
                break;
            case ELSKA:
                textureRegion = Assets.elska;
                break;
            case AYARN:
                textureRegion = Assets.ayarn;
                break;
            case SEVIYORUM:
                textureRegion = Assets.seviyorum;
                break;
            case MILESTIBA:
                textureRegion = Assets.milestiba;
                break;
            case RAKKAUS:
                textureRegion = Assets.rakkaus;
                break;
            case KAERLIGHED:
                textureRegion = Assets.kaerlighed;
                break;
        }
        this.plantType = plantType;
        this.buyCost = buyCost;
        this.sellCost = sellCost;
    }

    public Plant(Plant plant, ButtonTile buttonTile) {
        super(new Rectangle(buttonTile.getX() + PLANT_TILE_SPACING, buttonTile.getY() + PLANT_TILE_SPACING, PLANT_SIZE, PLANT_SIZE), PLANT_PRODUCTION_TIME);
        // Structure
        textureRegion = plant.textureRegion;
        // Plant
        plantType = plant.plantType;
        buyCost = plant.buyCost;
        sellCost = plant.sellCost;
        // Logic
        progress = 0;
    }

    public void update(float delta) {
        progress = Math.min(progress + delta, maxProgress);
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
