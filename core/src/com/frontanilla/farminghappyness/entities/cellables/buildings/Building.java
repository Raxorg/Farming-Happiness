package com.frontanilla.farminghappyness.entities.cellables.buildings;

import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.entities.cellables.Cellable;
import com.frontanilla.farminghappyness.grid.Cell;

public abstract class Building extends Cellable {

    public Building(Player player, Cell cell, int health, float width, float height,
                    float rotation, int representation) {
        super(player, cell, health, width, height, rotation, representation);
    }
}
