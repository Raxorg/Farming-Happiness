package com.frontanilla.farminghappyness.entities.cellables;

import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.entities.Entity;
import com.frontanilla.farminghappyness.grid.Cell;
import com.frontanilla.farminghappyness.utils.Util;

public abstract class Cellable extends Entity {

    protected Cell cell;
    protected int representation;

    public Cellable(Player owner, Cell cell, int health, float width, float height,
                    float rotation, int representation) {
        super(owner, Util.PolygonFromCell(cell), health, width, height, rotation);
        this.cell = cell;
        this.representation = representation;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getRepresentation() {
        return representation;
    }
}