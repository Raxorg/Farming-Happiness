package com.frontanilla.farminghappyness.game.other;

import com.frontanilla.farminghappyness.game.GameLogic;
import com.frontanilla.farminghappyness.utils.Enums.EnemyType;

import static com.frontanilla.farminghappyness.utils.Enums.EnemyType.ALIEN;
import static com.frontanilla.farminghappyness.utils.Enums.EnemyType.MILITARY;
import static com.frontanilla.farminghappyness.utils.Enums.EnemyType.POLICE;
import static com.frontanilla.farminghappyness.utils.Enums.EnemyType.TOURIST;

public class Level {

    public static Level level1 = new Level(TOURIST, 30, 1f);
    public static Level level2 = new Level(POLICE, 30, 0.5f);
    public static Level level3 = new Level(MILITARY, 30, 0.25f);
    public static Level level4 = new Level(ALIEN, 30, 0.125f);


    private EnemyType enemyType;
    private int currentSpawn, maxSpawn;
    private float spawnInterval, time;

    private Level(EnemyType enemyType, int maxSpawn, float spawnInterval) {
        this.enemyType = enemyType;
        this.maxSpawn = maxSpawn;
        this.spawnInterval = spawnInterval;
        time = 0;
        currentSpawn = 0;

    }

    public boolean isCompleted(int kills) {
        return kills == maxSpawn;
    }

    public void update(float delta, GameLogic gameLogic) {
        time += delta;
        if (time >= spawnInterval && currentSpawn < maxSpawn) {
            gameLogic.spawnEnemy(enemyType);
            currentSpawn++;
            time -= spawnInterval;
        }
    }

    public int getLevelNumber() {
        if (this == level1) {
            return 1;
        }
        if (this == level2) {
            return 2;
        }
        if (this == level3) {
            return 3;
        }
        if (this == level4) {
            return 4;
        }
        return 0;
    }
}
