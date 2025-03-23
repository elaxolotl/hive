import cells.Cell;

public class Board {
    private Cell[][] grid;

    public Board(int rows, int cols) {
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j);
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

    public void reset() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public int calculatePopulation(){
        int population = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getAlive()) {
                    population++;
                }
            }
        }
        return population;
    }

    public double getAverageLifeSpan(){
        int totalLife = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getAlive()) {
                    totalLife = grid[i][j].getLifeSpan()+totalLife;
                }
            }
        }
        return (double)totalLife/calculatePopulation();
    }
}
