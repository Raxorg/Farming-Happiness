package com.frontanilla.farminghappyness.utils;

public class Enums {

    public enum TileType {
        DEFENSIVE_TILE,
        FARMING_TILE,
        HIDDEN_TILE
    }

    public enum EnemyState {
        MOVING,
        STUCK,
        SHOOTING,
        SHOOTING_AND_MOVING
    }

    public enum EnemyType {
        TOURIST,
        POLICE,
        MILITARY,
        ALIEN
    }

    public enum MenuState {
        DEACTIVATED,
        SHOWING_DEFENSES_MENU,
        SHOWING_PLANTS_MENU,
        ACTIVATING_DEFENSES_MENU,
        ACTIVATING_PLANTS_MENU,
        DEACTIVATING
    }

    public enum PlantType {
        ELSKER,
        GRA,
        KOCHAM,
        SZERELEM,
        ELSKA,
        AYARN,
        SEVIYORUM,
        MILESTIBA,
        RAKKAUS,
        KAERLIGHED
    }
}
