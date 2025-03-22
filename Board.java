import cells.Cell;
import cells.ClassicCell;

import java.util.Arrays;

public class Board {
    private Cell[][] grid;

    public Board(int rows, int cols) {
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new ClassicCell(i, j);
            }
        }
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!grid[i][j].isAlive()) {
                    System.out.print("   ");
                }
                else {
                    System.out.print(" ■ ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Board board = new Board(20, 20);
        board.printGrid();
    }
}
