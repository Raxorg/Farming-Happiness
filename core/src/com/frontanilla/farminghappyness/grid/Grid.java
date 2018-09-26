package com.frontanilla.farminghappyness.grid;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.entities.cellables.Cellable;
import com.frontanilla.farminghappyness.entities.cellables.buildings.Base;
import com.frontanilla.farminghappyness.entities.cellables.buildings.Wall;
import com.frontanilla.farminghappyness.entities.cellables.units.Tank;
import com.frontanilla.farminghappyness.utils.Constants;
import com.frontanilla.farminghappyness.utils.Quadrlet;
import com.frontanilla.farminghappyness.utils.Util;

import static com.frontanilla.farminghappyness.utils.Constants.GRID_BORDER;
import static com.frontanilla.farminghappyness.utils.Constants.GRID_COLUMNS;
import static com.frontanilla.farminghappyness.utils.Constants.GRID_ROWS;

public class Grid {

    private Cell[][] cells;
    private String[] gridRows;
    private DelayedRemovalArray<Player> players;

    public Grid() {
        cells = new Cell[GRID_ROWS][GRID_COLUMNS];
        for (int row = 0; row < cells.length; row++) {
            cells[row] = new Cell[GRID_COLUMNS];
            for (int column = 0; column < cells[row].length; column++) {
                float cellX = GRID_BORDER + (Constants.CELL_SIZE + Constants.CELL_SPACING) * column;
                float cellY = GRID_BORDER + (Constants.CELL_SIZE + Constants.CELL_SPACING) * row;
                cells[row][column] = new Cell(new Vector2(cellX, cellY));
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Cell[] cellRow : cells) {
            for (Cell cell : cellRow) {
                cell.render(batch);
            }
        }
    }

    //-----------------------
    //      Special ops
    //-----------------------

    public boolean thisPlayerHasABase() {
        for (Player player : players) {
            if (player.getPhoneID().equals(FarmingGame.phoneID)) {
                for (Cell[] cellRow : cells) {
                    for (Cell cell : cellRow) {
                        if (cell.getOwner() == player && !cell.isEmpty()) {
                            if (cell.getContent() instanceof Base) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }

    //-----------------------
    //       Getters
    //-----------------------

    public Cell[][] getCells() {
        return cells;
    }

    public DelayedRemovalArray<Player> getPlayers() {
        return players;
    }

    //-----------------------
    //       Updating
    //-----------------------

    public void update(String[] gridRows, DelayedRemovalArray<Player> players) {
        this.gridRows = gridRows;
        this.players = players;
        Quadrlet[][] quadrlets = gridRowsToQuadrlets(gridRows, players);
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int column = 0; column < GRID_COLUMNS; column++) {
                cells[row][column].setOwner(quadrlets[row][column].owner());
                cells[row][column].setContent(contentFromQuadrlet(row, column, quadrlets[row][column]));
            }
        }
    }

    public void update(String[] gridRows) {
        this.gridRows = gridRows;
        if (players != null) {
            update(gridRows, players);
        } else {
            System.out.println("Error updating grid, players are null");
        }
    }

    public void update(DelayedRemovalArray<Player> players) {
        this.players = players;
        if (gridRows != null) {
            update(gridRows, players);
        } else {
            System.out.println("Error updating grid, grid rows are null");
        }
    }

    //

    private Cellable contentFromQuadrlet(int row, int column, Quadrlet contentData) {
        switch (contentData.representation()) {
            case 1:
                return new Wall(
                        contentData.owner(),
                        cells[row][column],
                        contentData.health(),
                        contentData.rotation()
                );
            case 2:
                return new Tank(
                        contentData.owner(),
                        cells[row][column],
                        contentData.health(),
                        contentData.rotation()
                );
            case 3:
                return new Base(
                        contentData.owner(),
                        cells[row][column],
                        contentData.health(),
                        contentData.rotation()
                );
            default:
                return null;
        }
    }

    private Quadrlet[][] gridRowsToQuadrlets(String[] gridRows, DelayedRemovalArray<Player> players) {
        Quadrlet[][] quadrletArray = new Quadrlet[GRID_ROWS][GRID_COLUMNS];
        for (int row = 0; row < quadrletArray.length; row++) {
            quadrletArray[row] = new Quadrlet[GRID_COLUMNS];
            String[] data = gridRows[row].split(",");
            for (int column = 0; column < GRID_COLUMNS; column++) {
                quadrletArray[row][column] = new Quadrlet(
                        getPlayerFromColorChar(data[column].charAt(0), players),
                        Integer.valueOf(data[column].charAt(1) + ""),
                        Integer.valueOf(data[column].charAt(2) + ""),
                        Float.valueOf(data[column].substring(3))
                );
            }
        }
        return quadrletArray;
    }

    private Player getPlayerFromColorChar(char playerColor, DelayedRemovalArray<Player> players) {
        for (Player p : players) {
            if (p.getColor() == Util.getColorFromChar(playerColor)) {
                return p;
            }
        }
        return null;
    }
}
