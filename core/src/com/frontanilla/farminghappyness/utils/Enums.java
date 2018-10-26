package com.frontanilla.farminghappyness.utils;

public class Enums {

    public enum TileType {
        DEFENSIVE_TILE,
        FARMING_TILE,
        HIDDEN_TILE
    }

    public enum EnemyState {
        MOVING,
        CLIMBING,
        SHOOTING,
        SHOOTING_AND_MOVING
    }

    public enum ConstructionState {
        BUILDING_TURRET,
        BUILDING_WALL,
        BUILDING_TRAP,
        NONE
    }
}
