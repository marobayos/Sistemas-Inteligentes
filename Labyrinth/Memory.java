package unalcol.agents.examples.labyrinth.teseo.IA2018.SiPerdemosEsPorLag;

import unalcol.agents.Percept;

public class Memory {
    final byte NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    private byte matrix[][];
    private byte orientation;
    public Memory (int size){
        this.matrix = new byte[size][size];
        this.orientation = NORTH;
    }
    // X -> Vertical
    // Y -> Horizontal

    public Memory () {
        this(100);
    }

    public void init(){
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[i].length ; j++) {
                this.matrix[i][j] = 0;
            }
        }
        this.orientation = NORTH;
    }

    public void setOrientation(byte orientation) {
        this.orientation = orientation;
    }

    public byte getOrientation() {
        return orientation;
    }

    public void rotate(int times){
        this.orientation = (byte)( ( this.orientation + times )% 4 );
    }

    public void setSeen(int x, int y, boolean val){
        matrix[x][y] = (byte) (matrix[x][y] | 1);
        if ( !val ) {
            matrix[x][y] = (byte) (matrix[x][y] ^ 1);
        }
    }

    public void setSeen(Position pos, boolean val){
        this.setSeen(pos.getX(), pos.getY(), val);
    }

    public void setNorth(int x, int y, boolean val){
        matrix[x][y] = (byte) (matrix[x][y] | 2);
        if ( !val ) {
            matrix[x][y] = (byte) (matrix[x][y] ^ 2);
        }
    }

    public void setEast(int x, int y, boolean val){
        matrix[x][y] = (byte) (matrix[x][y] | 4);
        if ( !val ) {
            matrix[x][y] = (byte) (matrix[x][y] ^ 4);
        }
    }

    public void setSouth(int x, int y, boolean val){
        matrix[x][y] = (byte) (matrix[x][y] | 8);
        if ( !val ) {
            matrix[x][y] = (byte) (matrix[x][y] ^ 8);
        }
    }

    public void setWest(int x, int y, boolean val){
        matrix[x][y] = (byte) (matrix[x][y] | 16);
        if ( !val ) {
            matrix[x][y] = (byte) (matrix[x][y] ^ 16);
        }
    }

    public boolean getSeen(int x, int y){
        return (int)(matrix[x][y])%2 == 1;
    }

    public boolean getSeen(Position pos){
        return this.getSeen(pos.getX(), pos.getY());
    }

    public boolean getNorth(int x, int y){
        return (int)(matrix[x][y]/2)%2 == 1;
    }

    public boolean getNorth(Position pos){
        return this.getNorth(pos.getX(), pos.getY());
    }

    public boolean getEast(int x, int y){
        return (int)(matrix[x][y]/4)%2 == 1;
    }

    public boolean getEast(Position pos){
        return this.getEast(pos.getX(), pos.getY());
    }

    public boolean getSouth(int x, int y){
        return (int)(matrix[x][y]/8)%2 == 1;
    }

    public boolean getSouth(Position pos){
        return this.getSouth(pos.getX(), pos.getY());
    }

    public boolean getWest(int x, int y){
        return (int)(matrix[x][y]/16)%2 == 1;
    }

    public boolean getWest(Position pos){
        return this.getWest(pos.getX(), pos.getY());
    }

    public boolean getSeenFront(int x, int y){
        switch (orientation){
            case NORTH:
                return matrix[x-1][y]%2 == 1;
            case EAST:
                return matrix[x][y+1]%2 == 1;
            case SOUTH:
                return matrix[x+1][y]%2 == 1;
            case WEST:
                return matrix[x][y-1]%2 == 1;
            default:
                return false;
        }
    }

    public boolean getSeenFront(Position pos){
        return this.getSeenFront(pos.getX(), pos.getY());
    }

    public boolean getSeenRight(int x, int y){
        switch (orientation){
            case WEST:
                return matrix[x-1][y]%2 == 1;
            case NORTH:
                return matrix[x][y+1]%2 == 1;
            case EAST:
                return matrix[x+1][y]%2 == 1;
            case SOUTH:
                return matrix[x][y-1]%2 == 1;
            default:
                return false;
        }
    }

    public boolean getSeenRight(Position pos){
        return this.getSeenRight(pos.getX(), pos.getY());
    }

    public boolean getSeenBack(int x, int y){
        switch (orientation){
            case SOUTH:
                return matrix[x-1][y]%2 == 1;
            case WEST:
                return matrix[x][y+1]%2 == 1;
            case NORTH:
                return matrix[x+1][y]%2 == 1;
            case EAST:
                return matrix[x][y-1]%2 == 1;
            default:
                return false;
        }
    }

    public boolean getSeenBack(Position pos){
        return this.getSeenBack(pos.getX(), pos.getY());
    }

    public boolean getSeenLeft(int x, int y){
        switch (orientation){
            case EAST:
                return matrix[x-1][y]%2 == 1;
            case SOUTH:
                return matrix[x][y+1]%2 == 1;
            case WEST:
                return matrix[x+1][y]%2 == 1;
            case NORTH:
                return matrix[x][y-1]%2 == 1;
            default:
                return false;
        }
    }

    public boolean getSeenLeft(Position pos){
        return this.getSeenLeft(pos.getX(), pos.getY());
    }

    public boolean getFront(int x, int y){
        switch (orientation){
            case NORTH:
                return (matrix[x][y]/2)%2 == 1;
            case EAST:
                return (matrix[x][y]/4)%2 == 1;
            case SOUTH:
                return (matrix[x][y]/8)%2 == 1;
            case WEST:
                return (matrix[x][y]/16)%2 == 1;
            default:
                return false;
        }
    }

    public boolean getFront(Position pos){
        return this.getFront(pos.getX(), pos.getY());
    }

    public boolean getRight(int x, int y){
        switch (orientation){
            case NORTH:
                return (matrix[x][y]/4)%2 == 1;
            case EAST:
                return (matrix[x][y]/8)%2 == 1;
            case SOUTH:
                return (matrix[x][y]/16)%2 == 1;
            case WEST:
                return (matrix[x][y]/2)%2 == 1;
            default:
                return false;
        }
    }

    public boolean getRight(Position pos){
        return this.getRight(pos.getX(), pos.getY());
    }

    public boolean getBack(int x, int y){
        switch (orientation){
            case NORTH:
                return (matrix[x][y]/8)%2 == 1;
            case EAST:
                return (matrix[x][y]/16)%2 == 1;
            case SOUTH:
                return (matrix[x][y]/2)%2 == 1;
            case WEST:
                return (matrix[x][y]/4)%2 == 1;
            default:
                return false;
        }
    }

    public boolean getBack(Position pos){
        return this.getBack(pos.getX(), pos.getY());
    }

    public boolean getLeft(int x, int y){
        switch (orientation){
            case NORTH:
                return (matrix[x][y]/16)%2 == 1;
            case EAST:
                return (matrix[x][y]/2)%2 == 1;
            case SOUTH:
                return (matrix[x][y]/4)%2 == 1;
            case WEST:
                return (matrix[x][y]/8)%2 == 1;
            default:
                return false;
        }
    }

    public boolean getLeft(Position pos){
        return this.getLeft(pos.getX(), pos.getY());
    }

    public void save(int x, int y, Percept p){
        switch (orientation){
            case NORTH:
                this.setNorth(x, y, (Boolean)p.getAttribute("front"));
                this.setEast(x, y, (Boolean)p.getAttribute("right"));
                this.setSouth(x, y, (Boolean)p.getAttribute("back"));
                this.setWest(x, y, (Boolean)p.getAttribute("left"));
                break;
            case EAST:
                this.setNorth(x, y, (Boolean)p.getAttribute("left"));
                this.setEast(x, y, (Boolean)p.getAttribute("front"));
                this.setSouth(x, y, (Boolean)p.getAttribute("right"));
                this.setWest(x, y, (Boolean)p.getAttribute("back"));
                break;
            case SOUTH:
                this.setNorth(x, y, (Boolean)p.getAttribute("back"));
                this.setEast(x, y, (Boolean)p.getAttribute("left"));
                this.setSouth(x, y, (Boolean)p.getAttribute("front"));
                this.setWest(x, y, (Boolean)p.getAttribute("right"));
                break;
            case WEST:
                this.setNorth(x, y, (Boolean)p.getAttribute("right"));
                this.setEast(x, y, (Boolean)p.getAttribute("back"));
                this.setSouth(x, y, (Boolean)p.getAttribute("left"));
                this.setWest(x, y, (Boolean)p.getAttribute("front"));
                break;
        }
        this.setSeen(x, y, true);
    }

    public void save(Position pos, Percept p){
        this.save(pos.getX(), pos.getY(), p);
    }
}