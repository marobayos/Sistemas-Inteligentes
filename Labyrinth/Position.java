package unalcol.agents.examples.labyrinth.teseo.IA2018.SiPerdemosEsPorLag;

public class Position {
    protected final byte NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    private int x; // vertical;
    private int y; // horizontal;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position front(byte orientation){
        switch (orientation){
            case NORTH:
                return new Position(x-1, y);
            case SOUTH:
                return new Position(x+1, y);
            case EAST:
                return new Position(x, y+1);
            case WEST:
                return new Position(x, y-1);
            default:
                return null;
        }
    }

    public Position right(byte orientation){
        switch (orientation){
            case WEST:
                return new Position(x-1, y);
            case NORTH:
                return new Position(x, y+1);
            case EAST:
                return new Position(x+1, y);
            case SOUTH:
                return new Position(x, y-1);
            default:
                return null;
        }
    }

    public Position back(byte orientation){
        switch (orientation){
            case SOUTH:
                return new Position(x-1, y);
            case WEST:
                return new Position(x, y+1);
            case NORTH:
                return new Position(x+1, y);
            case EAST:
                return new Position(x, y-1);
            default:
                return null;
        }
    }

    public Position left(byte orientation){
        switch (orientation){
            case EAST:
                return new Position(x-1, y);
            case SOUTH:
                return new Position(x, y+1);
            case WEST:
                return new Position(x+1, y);
            case NORTH:
                return new Position(x, y-1);
            default:
                return null;
        }
    }

    public Position north(){ return new Position(x-1, y); }

    public Position east(){
        return new Position(x, y+1);
    }

    public Position south(){
        return new Position(x+1, y);
    }

    public Position west(){
        return new Position(x, y-1);
    }

    @Override
    public String toString( ){
        return ( y - 50 ) + " " + ( x - 50 );
    }
}
