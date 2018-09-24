package com.frontanilla.farminghappyness.utils;

public class Enums {

    public enum BulletType {
        TANK_BULLET
    }

    public enum PlayerAction {
        PLACING_WALL,
        PLACING_TANK,
        PLACING_BASE,
        PLAYING,
        SENDING_PLACEMENT_REQUEST,
        WAITING_PLACEMENT_RESULT,
        SENDING_PASS_TURN_REQUEST,
        WAITING_PASS_TURN,
        WAITING_TURN
    }

    public enum Team {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NEUTRAL
    }

    public enum CellType {
        NORMAL,
        SIDE,
        CORNER
    }

    public enum CellableType {
        WALL,
        TANK,
        NOTHING
    }

}
