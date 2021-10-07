package sds.cc.model;

public class GameMap {
    private char[][] cells;
    private int size;

    public GameMap(int size) {
        this.size = size;

        this.cells = new char[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.cells[i][j] = ' ';
            }
        }
    }

    public int size() {
        return size;
    }

    public char[][] getMap() {
        return cells;
    }

    public char getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(int row, int col, char val) {
        cells[row][col] = val;
    }
}
