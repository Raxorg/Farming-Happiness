package com.frontanilla.farminghappyness.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.farminghappyness.grid.Cell;

import static com.frontanilla.farminghappyness.utils.Constants.CELL_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.TANK_BULLET_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TANK_BULLET_WIDTH;

public class Util {

    public static Vector2 getVector2FromRotation(float rotation) {
        return new Vector2(MathUtils.cosDeg(rotation), MathUtils.sinDeg(rotation));
    }

    public static Color getColorFromChar(char color) {
        switch (color) {
            case 'R':
                return Color.RED;
            case 'B':
                return Color.BLUE;
            case 'G':
                return Color.GREEN;
            case 'Y':
                return Color.YELLOW;
        }
        return Color.WHITE;
    }

    public static char getCharFromColor(Color color) {
        if (color == Color.RED) {
            return 'R';
        }
        if (color == Color.BLUE) {
            return 'B';
        }
        if (color == Color.GREEN) {
            return 'G';
        }
        if (color == Color.YELLOW) {
            return 'Y';
        }
        return 'W';
    }

    public static Color getColorFromString(String color) {
        switch (color) {
            case "red":
                return Color.RED;
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.GREEN;
            case "yellow":
                return Color.YELLOW;
        }
        return Color.WHITE;
    }

    public static String getStringFromColor(Color color) {
        if (color == Color.RED) {
            return "red";
        }
        if (color == Color.BLUE) {
            return "blue";
        }
        if (color == Color.GREEN) {
            return "green";
        }
        if (color == Color.YELLOW) {
            return "yellow";
        }
        return "white";
    }

    public static Vector2 getTextSize(String text, BitmapFont font) {
        Vector2 textSize = new Vector2();
        GlyphLayout layout = new GlyphLayout(font, text);
        textSize.x = layout.width;
        textSize.y = layout.height;
        return textSize;
    }

    public static void scaleFont(BitmapFont font, float scaleFactor, boolean usesCameraConstants) {
        // I'm a fucking genius
        float ratio;
        float height = Gdx.graphics.getHeight();
        if (usesCameraConstants) {
            float pivot = Constants.PIVOT_HEIGHT / Constants.CAMERA_HEIGHT;
            float here = height / Constants.CAMERA_HEIGHT;
            ratio = pivot / here;
        } else {
            ratio = 1;
        }
        font.getData().setScale(ratio * scaleFactor * (height / Constants.PIVOT_HEIGHT));
    }

    public static Polygon polygonFromPositionAndSize(float x, float y, float width, float height) {
        Polygon polygon = new Polygon(new float[]{
                0, 0,
                width, 0,
                width, height,
                0, height,
        });
        polygon.setOrigin(width / 2, height / 2);
        polygon.setPosition(x, y);
        return polygon;
    }

    public static Polygon PolygonFromCell(Cell cell) {
        Polygon polygon = new Polygon(new float[]{
                0, 0,
                CELL_SIZE, 0,
                CELL_SIZE, CELL_SIZE,
                0, CELL_SIZE,
        });
        polygon.setOrigin(CELL_SIZE / 2, CELL_SIZE / 2);
        Vector2 cellPosition = cell.getPosition();
        polygon.setPosition(cellPosition.x, cellPosition.y);
        return polygon;
    }

    public static Polygon PolygonFromBullet(Enums.BulletType bulletType, Vector2 position, float rotation) {
        float width, height;
        switch (bulletType) {
            case TANK_BULLET:
                width = TANK_BULLET_WIDTH;
                height = TANK_BULLET_HEIGHT;
                break;
            default:
                width = CELL_SIZE;
                height = CELL_SIZE;
                break;
        }
        Polygon polygon = new Polygon(new float[]{
                0, 0,
                width, 0,
                width, height,
                0, height,
        });
        polygon.setOrigin(width / 2, height / 2);
        polygon.setPosition(position.x, position.y);
        polygon.setRotation(rotation);
        return polygon;
    }

    public static int healthFromRepresentation(int representation) {
        switch (representation) {
            case 0:
                return 0;
            case 1:
                return Constants.WALL_INITIAL_HEALTH;
            case 2:
                return Constants.TANK_INITIAL_HEALTH;
            case 3:
                return Constants.BASE_INITIAL_HEALTH;
            default:
                return 0;
        }
    }

    public static Color cloneWithAlpha(Color color, float alpha) {
        return new Color(color.r, color.g, color.b, alpha);
    }

}