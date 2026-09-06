package com.machinecoding.connectfour.entitites;

import com.machinecoding.connectfour.enums.DiscColor;
import com.machinecoding.connectfour.enums.GameState;

public class Board {
    int rows;
    int cols;
    DiscColor[][] grid;

    Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        grid = new DiscColor[rows][cols];
    }


    public int placeDisc(DiscColor color, int column) {
        if(!canPlace(column)){
            System.out.println("can place is false");
            return -1;
        }
        for (int row = rows-1; row>=0; row--){
            if(grid[row][column]==null) {
                grid[row][column]=color;
                return row;
            }
        }
        return -1;

    }

    public boolean canPlace(int col){
        if(col < 0 || col >= cols){
            return false;
        }
        return grid[0][col] == null;
    }

    public boolean checkWin(int row, int column, DiscColor color) {
        int[][] dirs = {{0,1}, {1,0}, {1,1}, {-1,1}};

        for (int[] dir: dirs){
            int count = 1;
            count += countInDirection(dir[0], dir[1],color, row, column);
            count += countInDirection(-1* dir[0], -1 * dir[1],color, row, column);
            if(count >=4){
                return true;
            }
        }
        return false;
    }

    private int countInDirection(int dr,int dc, DiscColor color, int row, int col) {
        int nextRow = row + dr;
        int nextCol = col + dc;
        int count = 0;
        while (inbounds(nextRow, nextCol) && grid[nextRow][nextCol]==color){
            count++;
            nextRow += dr;
            nextCol += dc;
        }
        return count;

    }

    public boolean isFull() {
        for (int col = 0; col < cols; col++){
            if(grid[0][col]==null){
               return false;
            }
        }
        return true;
    }

    public boolean inbounds(int r, int c){
        return r >= 0 && r < rows && c >=0 && c < cols;
    }
}
