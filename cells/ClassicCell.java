package cells;

public class ClassicCell extends Cell {

    public ClassicCell(int x, int y){
        super(x, y);
        double rand  = (Math.random());
        setAlive(rand < 0.5);
    }

    public void spawn(Cell[][] grid){
        int x=getX();
        int y=getY();
        int neighbors=0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int neighborX = x + i;
                int neighborY = y + j;
                if (neighborX >= 0 && neighborX < grid.length && neighborY >= 0 && neighborY < grid[0].length) {
                    if (grid[neighborX][neighborY].isAlive()) {
                        neighbors++;
                    }
                }
            }
        }
        if (neighbors < 3){
            setAlive(false);
        } else if (neighbors == 3) {
            setAlive(true);
        }
        else {
            setAlive(false);
        }
    }

    public void reproduce(){

    }
}
