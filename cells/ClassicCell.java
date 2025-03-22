package cells;

public class ClassicCell extends Cell {

    public ClassicCell(int x, int y){
        super(x, y);
        double rand  = (Math.random());
        setAlive(rand < 0.5);
    }

    public void spawn(){

    }

    public void reproduce(){

    }
}
