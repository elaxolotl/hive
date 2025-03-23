package cells;

public class ClassicCell extends Cell {

    public ClassicCell(int x, int y){
        super(x, y);
        double rand  = (Math.random());
        setAlive(rand < 0.5);
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
