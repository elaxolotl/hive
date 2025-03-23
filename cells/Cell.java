package cells;

abstract public class Cell{
    private int x;
    private int y;
    private boolean isAlive;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.isAlive = true;
    }

    abstract public boolean spawn(Cell[][] grid);

    abstract public void reproduce();

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