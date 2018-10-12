package com.frontanilla.farminghappyness.utils;

public class Util {

    public static float getAngle(Point current, Point target) {
        float angle = (float) Math.toDegrees(Math.atan2(
                target.getY() - current.getY(),
                target.getX() - current.getX()));

        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }

    public static float getDistance(Point a, Point b) {
        double temp = Math.pow(a.getX() - b.getX(), 2);
        double temp2 = Math.pow(a.getY() - b.getY(), 2);
        return (float) Math.sqrt(temp + temp2);
    }

}