package cells;

public class SpecialCell extends Cell {

    public SpecialCell(int x, int y){
        super(x, y);
    }

    @Override
    public int countNeighbors(Cell[][] grid) {
            int count = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue;
                    int nx = getX() + i;
                    int ny = getY() + j;
                    if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length) {
                        if (grid[nx][ny].getAlive() && grid[nx][ny] instanceof SpecialCell) count++;
                    }
                }
            }
            return count;
    }

    public boolean spawn(Cell[][] grid){
        int neighbors = countNeighbors(grid);
        boolean result;
        if (getAlive()) {
            result = (neighbors == 2 || neighbors == 3);
        } else {
            result = (neighbors == 3);
        }
        return result;
    }

    public void reproduce(){

    }
}
