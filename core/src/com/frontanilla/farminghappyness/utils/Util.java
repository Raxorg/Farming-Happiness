package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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

    public static TextureRegion[] regions(Texture texture, int regionWidth, int regionHeight, int empty) {
        int columns = texture.getWidth() / regionWidth;
        int rows = texture.getHeight() / regionHeight;
        TextureRegion[] regions = new TextureRegion[columns * rows - empty];
        int counter = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (counter == regions.length) {
                    break;
                }
                regions[counter] = new TextureRegion(texture, column * regionWidth, row * regionHeight, regionWidth, regionHeight);
                counter++;
            }
        }
        return regions;
    }
}