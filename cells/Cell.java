package cells;

public class Cell{
    private int x;
    private int y;
    private boolean isAlive;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        double rand = Math.random();
        if (rand < 0.5){
            isAlive = true;
        }
        else{
            isAlive = false;
        }
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

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean getAlive(){
        return isAlive;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    public int countNeighbors(Cell[][] grid) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int nx = x + i;
                int ny = y + j;
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length) {
                    if (grid[nx][ny].getAlive()) count++;
                }
            }
        }
        return count;
    }
}