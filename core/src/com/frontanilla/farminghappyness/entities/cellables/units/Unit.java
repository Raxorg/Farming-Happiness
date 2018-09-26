package com.frontanilla.farminghappyness.entities.cellables.units;

import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.entities.cellables.Cellable;
import com.frontanilla.farminghappyness.grid.Cell;

public abstract class Unit extends Cellable {

    public Unit(Player player, Cell cell, int health,
                float width, float height, float rotation, int representation) {
        super(player, cell, health, width, height, rotation, representation);
    }
}