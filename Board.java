import cells.Cell;
import cells.ClassicCell;

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

    public void updateGrid() {
        boolean[][] nextState = new boolean[grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                nextState[i][j] = grid[i][j].spawn(grid);
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].setAlive(nextState[i][j]);
            }
        }
    }



    public Cell[][] getGrid() {
        return grid;
    }
}
