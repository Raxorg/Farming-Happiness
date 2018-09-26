package com.frontanilla.farminghappyness.entities.bullets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.Util;

import static com.frontanilla.farminghappyness.utils.Constants.TANK_BULLET_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TANK_BULLET_WIDTH;

public class TankBullet extends Bullet {

    public TankBullet(Player player, Polygon bounds, int health,
                      float rotation) {
        super(player, bounds, health, TANK_BULLET_WIDTH, TANK_BULLET_HEIGHT,
                rotation, Util.getVector2FromRotation(rotation));
    }

    @Override
    public TextureRegion textureOfHealth() {
        switch (health) {
            case 1:
                return Assets.instance.displayAssets.pixel;
            default:
                return Assets.instance.displayAssets.pixel;
        }
    }

    @Override
    public void update(float delta) {
        Vector2 position = new Vector2(bounds.getX(), bounds.getY());
        position.mulAdd(velocity, Constants.BULLET_SPEED * delta);
        bounds.setPosition(position.x, position.y);
        // TODO Check collision against enemies
        // TODO Check collision against grid bounds
        // TODO report to the ScreenLogic
    }

    public static boolean collision(Polygon a, Polygon b) {
        return Intersector.overlapConvexPolygons(a, b);
    }
}