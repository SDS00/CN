package sds.cc.model;

import java.util.List;

public class Game {
    private GameMap map;
    private List<Player> players;
    private boolean isFinished = false;
    private int currentPlayer = 0;

    public Game(GameMap map, List<Player> players) {
        this.map = map;
        this.players = players;
    }

    public char[][] getMapCells() {
        return map.getMap();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    private void toggleCurrentPlayer() {
        if(currentPlayer >= players.size() - 1) {
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    }

    public void doTurn(int row, int col) {
        map.setCell(row, col, getCurrentPlayer().getSymbol());

        if(hasWinner(row, col)) {
            isFinished = true;
        }

        if(!isFinished) {
            toggleCurrentPlayer();
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    private boolean hasWinner(int row, int col) {
        char[][] gameMap = map.getMap();
        char player = gameMap[row][col];

        int r = row;
        int c = col;

        boolean onDiagonal = (row == col) || (col == -1 * row + (gameMap.length-1));
        boolean HorizontalWin = true, VerticalWin = true;
        boolean DiagonalWinOne = true, DiagonalWinTwo = true;

        // Check the rows and columns
        for(int n = 0; n < gameMap.length; n++){
            if(gameMap[r][n] != player)
                HorizontalWin = false;
            if(gameMap[n][c] != player)
                VerticalWin = false;
        }

        // Only check diagonals if the move is on a diagonal
        if(onDiagonal){
            // Check the diagonals
            for(int n = 0; n < gameMap.length; n++){
                if(gameMap[n][n] != player)
                    DiagonalWinOne = false;
                if(gameMap[n][-1*n+(gameMap.length-1)] != player)
                    DiagonalWinTwo = false;
            }
        }
        else{
            DiagonalWinOne = false;
            DiagonalWinTwo = false;
        }

        boolean hasWon = (HorizontalWin || VerticalWin || DiagonalWinOne || DiagonalWinTwo);

        return hasWon;
    }
}
