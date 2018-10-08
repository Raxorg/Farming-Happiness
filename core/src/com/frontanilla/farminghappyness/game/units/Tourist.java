package com.frontanilla.farminghappyness.game.units;

import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.TOURIST_SPEED;

public class Tourist extends Enemy {

    public Tourist(float x, float y) {
        super(Assets.tourist, x, y, TOURIST_SPEED);
    }


}
