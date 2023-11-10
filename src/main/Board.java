package main;

import main.Ship.Rotation;
import main.Ship.ShipType;

public class Board {
    private final Ship[][] board;

    public Board() {
        board = new Ship[10][10];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                board[row][col] = new Ship(col, row);
            }
        }
    }

    public Ship getSpace(int colX, int rowY) {
        return board[rowY][colX];
    }

    public boolean addShip(Ship ship) {
        int col = ship.getY();
        int row = ship.getX();

        Rotation rotation = ship.getRotation();

        switch (ship.getShipType()) {
            case EMPTY:
                break;
            case DESTROYER:
                switch (rotation) {
                    case DOWN -> {
                        if (col + 1 > 9 || (!board[row][col].isEmpty() && board[row][col].getShipType() != ShipType.DESTROYER))
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col][row + 1].setShip(ship, 0);
                        board[col][row].setShip(ship, 1);
                    }
                    case LEFT -> {
                        if (row - 1 < 0)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col - 1][row].setShip(ship, 0);
                        board[col][row].setShip(ship, 1);
                    }
                    case UP -> {
                        if (col - 1 < 0)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col][row].setShip(ship, 0);
                        board[col][row + 1].setShip(ship, 1);
                    }
                    case RIGHT -> {
                        if (row + 1 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col + 1][row].setShip(ship, 0);
                        board[col][row].setShip(ship, 1);
                    }
                }
                break;
            case CRUISER, SUBMARINE:
                switch (rotation) {
                    case DOWN -> {
                        if (col - 1 < 0 || col + 1 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col][row + 1].setShip(ship, 0);
                        board[col][row].setShip(ship, 1);
                        board[col][row - 1].setShip(ship, 2);
                    }
                    case LEFT -> {
                        if (row - 1 < 0 || row + 1 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col - 1][row].setShip(ship, 0);
                        board[col][row].setShip(ship, 1);
                        board[col + 1][row].setShip(ship, 2);
                    }
                    case UP -> {
                        if (col - 1 < 0 || col + 1 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col][row - 1].setShip(ship, 0);
                        board[col][row].setShip(ship, 1);
                        board[col][row + 1].setShip(ship, 2);
                    }
                    case RIGHT -> {
                        if (row - 1 < 0 || row + 1 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col + 1][row].setShip(ship, 0);
                        board[col][row].setShip(ship, 1);
                        board[col - 1][row].setShip(ship, 2);
                    }
                }
                break;
            case BATTLESHIP:
                switch (rotation) {
                    case DOWN -> {
                        if (col - 2 < 0 || col + 1 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col][row + 1].setShip(ship, 0);
                        board[col][row].setShip(ship, 1);
                        board[col][row - 1].setShip(ship, 2);
                        board[col][row - 2].setShip(ship, 3);
                    }
                    case LEFT -> {
                        if (row - 1 < 0 || row + 2 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col - 1][row].setShip(ship, 0);
                        board[col][row].setShip(ship, 1);
                        board[col + 1][row].setShip(ship, 2);
                        board[col + 2][row].setShip(ship, 3);
                    }
                    case UP -> {
                        if (col - 1 < 0 || col + 2 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col][row - 1].setShip(ship, 0);
                        board[col][row].setShip(ship, 1);
                        board[col][row + 1].setShip(ship, 2);
                        board[col][row + 2].setShip(ship, 3);
                    }
                    case RIGHT -> {
                        if (row - 2 < 0 || row + 1 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col + 1][row].setShip(ship, 0);
                        board[col][row].setShip(ship, 1);
                        board[col - 1][row].setShip(ship, 2);
                        board[col - 2][row].setShip(ship, 3);
                    }
                }
                break;
            case CARRIER:
                switch (rotation) {
                    case DOWN -> {
                        if (col - 2 < 0 || col + 2 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col][row + 2].setShip(ship, 0);
                        board[col][row + 1].setShip(ship, 1);
                        board[col][row].setShip(ship, 2);
                        board[col][row - 1].setShip(ship, 3);
                        board[col][row - 2].setShip(ship, 4);
                    }
                    case LEFT -> {
                        if (row - 2 < 0 || row + 2 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col - 2][row].setShip(ship, 0);
                        board[col - 1][row].setShip(ship, 1);
                        board[col][row].setShip(ship, 2);
                        board[col + 1][row].setShip(ship, 3);
                        board[col + 2][row].setShip(ship, 4);
                    }
                    case UP -> {
                        if (col - 2 < 0 || col + 2 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col][row - 2].setShip(ship, 0);
                        board[col][row - 1].setShip(ship, 1);
                        board[col][row].setShip(ship, 2);
                        board[col][row + 1].setShip(ship, 3);
                        board[col][row + 2].setShip(ship, 4);
                    }
                    case RIGHT -> {
                        if (row - 2 < 0 || row + 2 > 9)
                            throw new IndexOutOfBoundsException("Cannot Place Ship There. The space is OutOfBounds, or on another Ship.");
                        board[col + 2][row].setShip(ship, 0);
                        board[col + 1][row].setShip(ship, 1);
                        board[col][row].setShip(ship, 2);
                        board[col - 1][row].setShip(ship, 3);
                        board[col - 2][row].setShip(ship, 4);
                    }
                }
                break;
        }
        return true;
    }
}