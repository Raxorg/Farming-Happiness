package com.frontanilla.farminghappyness.game.areas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.game.entities.ambient.Cloud;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.BACKGROUND_GRASS_TILE_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.BACKGROUND_GRASS_TILE_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.BACKGROUND_GRASS_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.CLOUD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.CLOUD_QUANTITY;
import static com.frontanilla.farminghappyness.utils.Constants.CLOUD_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.SIDE_TREES_QUANTITY;
import static com.frontanilla.farminghappyness.utils.Constants.TREE_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TREE_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class AmbientArea {

    private int[] bottomTreesRandYArray;
    private int[] leftTreesRandXArray;
    private TextureRegion[] leaves;
    private DelayedRemovalArray<Cloud> clouds;

    public AmbientArea() {
        // Init tree positions
        bottomTreesRandYArray = new int[SIDE_TREES_QUANTITY];
        for (int i = 0; i < bottomTreesRandYArray.length; i++) {
            bottomTreesRandYArray[i] = MathUtils.random(-TREE_HEIGHT / 2, 300);
        }
        leftTreesRandXArray = new int[SIDE_TREES_QUANTITY];
        for (int i = 0; i < leftTreesRandXArray.length; i++) {
            leftTreesRandXArray[i] = MathUtils.random(-TREE_WIDTH / 2, 300);
        }
        // Randomize tree leaves
        leaves = new TextureRegion[SIDE_TREES_QUANTITY];
        for (int i = 0; i < leftTreesRandXArray.length; i++) {
            switch (MathUtils.random(1, 3)) {
                case 1:
                    leaves[i] = Assets.tree;
                    break;
                case 2:
                    leaves[i] = Assets.appleTree;
                    break;
                case 3:
                    leaves[i] = Assets.flowerTree;
                    break;
            }
        }
        // Init clouds
        clouds = new DelayedRemovalArray<>();
        for (int i = 0; i < CLOUD_QUANTITY; i++) {
            clouds.add(new Cloud(new Rectangle(
                    MathUtils.random(WORLD_WIDTH),
                    MathUtils.random(WORLD_HEIGHT - CLOUD_HEIGHT),
                    CLOUD_WIDTH,
                    CLOUD_HEIGHT)));
        }
    }

    public void update(float delta) {
        for (Cloud cloud : clouds) {
            cloud.update(delta);
        }
    }

    public void renderGrass(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        for (int row = 0; row < BACKGROUND_GRASS_TILE_ROWS; row++) {
            for (int column = 0; column < BACKGROUND_GRASS_TILE_COLUMNS; column++) {
                batch.draw(
                        Assets.grassTile,
                        column * BACKGROUND_GRASS_TILE_SIZE,
                        row * BACKGROUND_GRASS_TILE_SIZE,
                        BACKGROUND_GRASS_TILE_SIZE,
                        BACKGROUND_GRASS_TILE_SIZE);
            }
        }
    }

    public void renderTrunks(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        // Shadows
        for (int i = 0; i < SIDE_TREES_QUANTITY; i++) {
            batch.draw(Assets.treeShadow, i * (WORLD_WIDTH / SIDE_TREES_QUANTITY), bottomTreesRandYArray[i]);
        }
        for (int i = 0; i < SIDE_TREES_QUANTITY; i++) {
            batch.draw(Assets.treeShadow, leftTreesRandXArray[i], i * (WORLD_HEIGHT - RIVER_TILE_SIZE) / SIDE_TREES_QUANTITY);
        }
        // Actual trunks
        for (int i = 0; i < SIDE_TREES_QUANTITY; i++) {
            batch.draw(Assets.trunk, i * (WORLD_WIDTH / SIDE_TREES_QUANTITY), bottomTreesRandYArray[i]);
        }
        for (int i = 0; i < SIDE_TREES_QUANTITY; i++) {
            batch.draw(Assets.trunk, leftTreesRandXArray[i], i * (WORLD_HEIGHT - RIVER_TILE_SIZE) / SIDE_TREES_QUANTITY);
        }
    }

    public void renderLeaves(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        for (int i = 0; i < SIDE_TREES_QUANTITY; i++) {
            batch.draw(leaves[i], i * (WORLD_WIDTH / SIDE_TREES_QUANTITY), bottomTreesRandYArray[i]);
        }
        for (int i = 0; i < SIDE_TREES_QUANTITY; i++) {
            batch.draw(leaves[i], leftTreesRandXArray[i], i * (WORLD_HEIGHT - RIVER_TILE_SIZE) / SIDE_TREES_QUANTITY);
        }
    }

    public void renderClouds(SpriteBatch batch) {
        for (Cloud cloud : clouds) {
            cloud.render(batch);
        }
    }
}
