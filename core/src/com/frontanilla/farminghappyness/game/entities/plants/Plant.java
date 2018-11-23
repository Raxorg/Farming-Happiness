package com.frontanilla.farminghappyness.game.entities.plants;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.farminghappyness.components.ButtonTile;
import com.frontanilla.farminghappyness.game.entities.Progressable;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums.PlantType;

import static com.frontanilla.farminghappyness.utils.Constants.AYARN_COST;
import static com.frontanilla.farminghappyness.utils.Constants.AYARN_SELL_PRICE;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKA_COST;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKA_SELL_PRICE;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKER_COST;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKER_SELL_PRICE;
import static com.frontanilla.farminghappyness.utils.Constants.GRA_COST;
import static com.frontanilla.farminghappyness.utils.Constants.GRA_SELL_PRICE;
import static com.frontanilla.farminghappyness.utils.Constants.KAERLIGHED_COST;
import static com.frontanilla.farminghappyness.utils.Constants.KAERLIGHED_SELL_PRICE;
import static com.frontanilla.farminghappyness.utils.Constants.KOCHAM_COST;
import static com.frontanilla.farminghappyness.utils.Constants.KOCHAM_SELL_PRICE;
import static com.frontanilla.farminghappyness.utils.Constants.MILESTIBA_COST;
import static com.frontanilla.farminghappyness.utils.Constants.MILESTIBA_SELL_PRICE;
import static com.frontanilla.farminghappyness.utils.Constants.PLANT_PRODUCTION_TIME;
import static com.frontanilla.farminghappyness.utils.Constants.PLANT_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.PLANT_TILE_SPACING;
import static com.frontanilla.farminghappyness.utils.Constants.RAKKAUS_COST;
import static com.frontanilla.farminghappyness.utils.Constants.RAKKAUS_SELL_PRICE;
import static com.frontanilla.farminghappyness.utils.Constants.SEVIYORUM_COST;
import static com.frontanilla.farminghappyness.utils.Constants.SEVIYORUM_SELL_PRICE;
import static com.frontanilla.farminghappyness.utils.Constants.SZERELEM_COST;
import static com.frontanilla.farminghappyness.utils.Constants.SZERELEM_SELL_PRICE;

public class Plant extends Progressable {

    // Structure
    private TextureRegion textureRegion;
    // Attributes
    private PlantType plantType;
    private int buyCost, sellCost;
    //region Plants
    public static Plant ELSKER = new Plant(
            PlantType.ELSKER,
            ELSKER_COST,
            ELSKER_SELL_PRICE);
    public static Plant GRA = new Plant(
            PlantType.GRA,
            GRA_COST,
            GRA_SELL_PRICE);
    public static Plant KOCHAM = new Plant(
            PlantType.KOCHAM,
            KOCHAM_COST,
            KOCHAM_SELL_PRICE);
    public static Plant SZERELEM = new Plant(
            PlantType.SZERELEM,
            SZERELEM_COST,
            SZERELEM_SELL_PRICE);
    public static Plant ELSKA = new Plant(
            PlantType.ELSKA,
            ELSKA_COST,
            ELSKA_SELL_PRICE);
    public static Plant AYARN = new Plant(
            PlantType.AYARN,
            AYARN_COST,
            AYARN_SELL_PRICE);
    public static Plant SEVIYORUM = new Plant(
            PlantType.SEVIYORUM,
            SEVIYORUM_COST,
            SEVIYORUM_SELL_PRICE);
    public static Plant MILESTIBA = new Plant(
            PlantType.MILESTIBA,
            MILESTIBA_COST,
            MILESTIBA_SELL_PRICE);
    public static Plant RAKKAUS = new Plant(
            PlantType.RAKKAUS,
            RAKKAUS_COST,
            RAKKAUS_SELL_PRICE);
    public static Plant KAERLIGHED = new Plant(
            PlantType.KAERLIGHED,
            KAERLIGHED_COST,
            KAERLIGHED_SELL_PRICE);
    //endregion

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
                bounds.y);
        progressBar.render(batch);
    }
}
