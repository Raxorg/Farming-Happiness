package com.frontanilla.farminghappyness.components;

import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.DEFENSE_BUTTON_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_X;
import static com.frontanilla.farminghappyness.utils.Constants.FARMING_AREA_Y;

public class DefensesMenu {
    private Button towerButton, wallButton, trapButton;

    public DefensesMenu() {
        // Defense buttons
        towerButton = new Button(
                Assets.turret,
                FARMING_AREA_X - FARMING_AREA_SIZE,
                FARMING_AREA_Y + DEFENSE_BUTTON_SIZE / 2f,
                DEFENSE_BUTTON_SIZE,
                DEFENSE_BUTTON_SIZE);
        wallButton = new Button(
                Assets.wall,
                FARMING_AREA_X - FARMING_AREA_SIZE + DEFENSE_BUTTON_SIZE * 1.5f,
                FARMING_AREA_Y + DEFENSE_BUTTON_SIZE / 2,
                DEFENSE_BUTTON_SIZE,
                DEFENSE_BUTTON_SIZE);
        trapButton = new Button(
                Assets.trap,
                FARMING_AREA_X - FARMING_AREA_SIZE + DEFENSE_BUTTON_SIZE * 3f,
                FARMING_AREA_Y + DEFENSE_BUTTON_SIZE / 2f,
                DEFENSE_BUTTON_SIZE,
                DEFENSE_BUTTON_SIZE);
    }

    // TODO this was on GameConnector class
    public void derp() {
        // Check if this happened on a defense construction button
//        if (connector.getTurretButton().contains(usefulVector.x, usefulVector.y)) {
//            connector.getTurretButton().setColor(Color.GREEN);
//            connector.getWallButton().setColor(Color.WHITE);
//            connector.getTrapButton().setColor(Color.WHITE);
//            constructionState = BUILDING_TURRET;
//        } else if (connector.getWallButton().contains(usefulVector.x, usefulVector.y)) {
//            connector.getWallButton().setColor(Color.GREEN);
//            connector.getTurretButton().setColor(Color.WHITE);
//            connector.getTrapButton().setColor(Color.WHITE);
//            constructionState = Enums.ConstructionState.BUILDING_WALL;
//        } else if (connector.getTrapButton().contains(usefulVector.x, usefulVector.y)) {
//            connector.getTrapButton().setColor(Color.GREEN);
//            connector.getTurretButton().setColor(Color.WHITE);
//            connector.getWallButton().setColor(Color.WHITE);
//            constructionState = Enums.ConstructionState.BUILDING_TRAP;
//            return;
//        }
    }
}
