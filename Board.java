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
        boolean[][] nextState = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int neighbors = countNeighbors(i, j);
                boolean isAlive = grid[i][j].isAlive();
                if (isAlive) {
                    nextState[i][j] = (neighbors == 2 || neighbors == 3);
                } else {
                    nextState[i][j] = (neighbors == 3);
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].setAlive(nextState[i][j]);
            }
        }
    }

    private int countNeighbors(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip self
                int nx = x + i;
                int ny = y + j;
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length) {
                    if (grid[nx][ny].isAlive()) count++;
                }
            }
        }
        return count;
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
