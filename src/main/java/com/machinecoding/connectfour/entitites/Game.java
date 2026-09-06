package com.machinecoding.connectfour.entitites;

import com.machinecoding.connectfour.ConnectfourApplication;
import com.machinecoding.connectfour.enums.GameState;

import java.util.Arrays;
import java.util.Stack;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player winner;
    private Board board;
    private GameState state;
    Stack<Move> moveHistory;


    public Game(Player player1, Player player2, int rows, int cols) {
        this.player1 = player1;
        this.player2 = player2;
        board = new Board(rows, cols);
        state = GameState.IN_PROGRESS;
        currentPlayer = player1;
        moveHistory = new Stack<>();
    }

    public boolean getBoard(){
        System.out.println(Arrays.deepToString(board.grid));
        return true;
    }

    public boolean makeMove(Player player, int column){
        System.out.println("Make Move "+ player.getName() + " -> column "+ column);
        if (state != GameState.IN_PROGRESS){
            return false;
        }

        if(player !=currentPlayer ){
            return false;
        }

        int row = board.placeDisc(player.getColor(), column);
        System.out.printf("Place Disc (%s, %d) -> %d%n", player.getColor(), column, row);
        if(row ==-1) {
            return false;
        }

        if (board.checkWin(row, column, player.getColor())) {
            winner = player;
            state = GameState.WON;
            System.out.println("Game won ");
        } else if (board.isFull()){
            state = GameState.DRAW;
            return false;
        } else {
            currentPlayer = currentPlayer == player1? player2: player1;
        }
        return true;
    }

    public GameState getState() {
        return state;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean pushMove(Player player, int row, int col){
        moveHistory.push(new Move(player, row, col));
        return true;
    }

    public boolean undoMove(){
        Move move = moveHistory.pop();
        currentPlayer = move.getPlayer();
        int row = move.getRow();
        int col = move.getCol();
        board.clearCell(row, col);
        return true;
    }
}
