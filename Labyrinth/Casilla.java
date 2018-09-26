package unalcol.agents.examples.labyrinth.teseo.IA2018.SiPerdemosEsPorLag;

public class Casilla implements Comparable<Casilla> {
    private int peso;
    private Position pos;
    private byte orientation;

    public Casilla(int peso, Position pos, byte orientation) {
        this.peso = peso;
        this.pos = pos;
        this.orientation = orientation;
    }

    public Position getPos(){
        return pos;
    }

    public int newWeigth( int newOrientation ){
        if( orientation == 0 ){
            if( newOrientation == 0 ){
                return peso + 1;
            }
            else if( newOrientation == 1 ){
                return peso + 2;
            }
            else if( newOrientation == 2 ){
                return peso + 3;
            }
            else{
                return peso + 4;
            }
        }
        else if ( orientation == 1 ) {
            if (newOrientation == 1) {
                return peso + 1;
            } else if (newOrientation == 2) {
                return peso + 2;
            } else if (newOrientation == 3) {
                return peso + 3;
            } else {
                return peso + 4;
            }
        }
        else if ( orientation == 2 ) {
            if (newOrientation == 2) {
                return peso + 1;
            } else if ( newOrientation == 3 ){
                return peso + 2;
            } else if( newOrientation == 0 ){
                return peso + 3;
            } else{
                return peso + 4;
            }
        }
        else {
            if (newOrientation == 3) {
                return peso + 1;
            } else if (newOrientation == 0) {
                return peso + 2;
            } else if (newOrientation == 1) {
                return peso + 3;
            } else {
                return peso + 4;
            }
        }
    }

    @Override
    public int compareTo(Casilla cas) {
        return Integer.compare(peso, cas.peso);
    }

    @Override
    public String toString() {
        return pos + " " + peso + " " + orientation;
    }
}
