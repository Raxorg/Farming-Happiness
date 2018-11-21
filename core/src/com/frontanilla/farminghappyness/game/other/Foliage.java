package com.frontanilla.farminghappyness.game.other;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.frontanilla.farminghappyness.utils.Assets;

import static com.frontanilla.farminghappyness.utils.Constants.BACKGROUND_GRASS_TILE_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.BACKGROUND_GRASS_TILE_ROWS;
import static com.frontanilla.farminghappyness.utils.Constants.BACKGROUND_GRASS_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.RIVER_TILE_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.SIDE_TREES_QUANTITY;
import static com.frontanilla.farminghappyness.utils.Constants.TREE_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TREE_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WORLD_WIDTH;

public class Foliage {

    private int[] bottomTreesRandYArray;
    private int[] leftTreesRandXArray;
    private TextureRegion[] leaves;

    public Foliage() {
        bottomTreesRandYArray = new int[SIDE_TREES_QUANTITY];
        for (int i = 0; i < bottomTreesRandYArray.length; i++) {
            bottomTreesRandYArray[i] = MathUtils.random(-TREE_HEIGHT / 2, 300);
        }
        leftTreesRandXArray = new int[SIDE_TREES_QUANTITY];
        for (int i = 0; i < leftTreesRandXArray.length; i++) {
            leftTreesRandXArray[i] = MathUtils.random(-TREE_WIDTH / 2, 300);
        }
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
}
