package cells;

abstract public class Cell{
    private int x;
    private int y;
    private boolean isAlive;

    public Cell(int x, int y){
        this.isAlive = true;
    }

    abstract public void spawn();

    abstract public void reproduce();

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isAlive(){
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

}