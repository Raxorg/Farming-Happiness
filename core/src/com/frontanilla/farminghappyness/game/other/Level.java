package com.frontanilla.farminghappyness.game.other;

import com.frontanilla.farminghappyness.game.GameLogic;
import com.frontanilla.farminghappyness.utils.Enums.EnemyType;

import static com.frontanilla.farminghappyness.utils.Enums.EnemyType.ALIEN;
import static com.frontanilla.farminghappyness.utils.Enums.EnemyType.MILITARY;
import static com.frontanilla.farminghappyness.utils.Enums.EnemyType.POLICE;
import static com.frontanilla.farminghappyness.utils.Enums.EnemyType.TOURIST;

public class Level {

    public static Level level1 = new Level(TOURIST, 200, 0.5f);
    public static Level level2 = new Level(POLICE, 40, 0.5f);
    public static Level level3 = new Level(MILITARY, 50, 0.5f);
    public static Level level4 = new Level(ALIEN, 30, 0.5f);


    private EnemyType enemyType;
    private int currentSpawn, maxSpawn;
    private float spawnInterval, time;

    private Level(EnemyType enemyType, int maxSpawn, float spawnInterval) {
        this.enemyType = enemyType;
        this.maxSpawn = maxSpawn;
        this.spawnInterval = spawnInterval;
        currentSpawn = 0;
        time = 0;

    }

    public Level restart() {
        currentSpawn = 0;
        time = 0;
        level2.currentSpawn = 0;
        level2.time = 0;
        level3.currentSpawn = 0;
        level3.time = 0;
        level4.currentSpawn = 0;
        level4.time = 0;
        return this;
    }

    public void update(float delta, GameLogic gameLogic) {
        time += delta;
        if (time >= spawnInterval && currentSpawn < maxSpawn) {
            gameLogic.spawnEnemy(enemyType);
            currentSpawn++;
            time -= spawnInterval;
        }
    }

    public boolean isCompleted(int kills) {
        return kills == maxSpawn;
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
