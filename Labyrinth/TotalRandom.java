package unalcol.agents.examples.labyrinth.teseo.IA2018.SiPerdemosEsPorLag;

import unalcol.agents.*;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.types.collection.vector.Vector;

import java.util.*;

public class TotalRandom implements AgentProgram {
    /* VARIABLES DEL AGENTE COMO TAL */
    protected SimpleLanguage language;
    protected Vector<String> actions = new Vector<String>();
    protected final byte EXPLORE = 0, RETURN = 1, WIN = 2;
    protected final byte NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    protected byte state = EXPLORE;
    protected Position position = new Position(50, 50);
    protected int a = 0;
    protected Random rn = new Random(System.currentTimeMillis());

    Memory memory = new Memory();

    public TotalRandom() {}

    public TotalRandom(   SimpleLanguage language  ) {
        this.language = language;
    }

    public void setLanguage(SimpleLanguage language) {
        this.language = language;
    }

    @Override
    public Action compute(Percept p) {
        if ( (Boolean)p.getAttribute("treasure") && this.state != WIN ){
            this.actions.clear();
            this.state = WIN;
        }
        if( this.actions.isEmpty() ) {
            memory.save( this.position, p );
            if( this.state == EXPLORE){
                _random();
            }
            if( this.state == RETURN ){
                System.out.println("RETURN");
                _return();
            }
            if( this.state == WIN ){
                _win();
            }
        }
        String act = this.actions.get(0);
        this.actions.remove(0);
        if(!act.equals("no_op")){
            a++;
        } else {
            System.out.println(a);
        }
        return new Action(act);
    }

    private void _random(){
        int posib = 0;
        if (!memory.getFront(position) && !memory.getSeenFront(position)) {
            posib++;
        }
        if (!memory.getBack(position) && !memory.getSeenBack(position)) {
            posib++;
        }
        if (!memory.getRight(position) && !memory.getSeenRight(position)) {
            posib++;
        }
        if (!memory.getLeft(position) && !memory.getSeenLeft(position)) {
            posib++;
        }
        if(posib == 0){
            this.state = RETURN;
            return;
        }
        int move = rn.nextInt(posib);
        if (!memory.getFront(position) && !memory.getSeenFront(position)) {
            if( move == 0 )
                this.moveFront();
            move --;
        }
        if (!memory.getBack(position) && !memory.getSeenBack(position)) {
            if( move == 0 )
                this.moveBack();
            move --;
        }
        if (!memory.getRight(position) && !memory.getSeenRight(position)) {
            if( move == 0 )
                this.moveRight();
            move --;
        }
        if (!memory.getLeft(position) && !memory.getSeenLeft(position)) {
            if( move == 0 )
                this.moveLeft();
            move --;
        }
    }

    private void _return(){
        HashMap<Position, ArrayList<Byte>> mapa = new HashMap<>();
        PriorityQueue<Casilla> lista = new PriorityQueue<Casilla>();
        Position actual = new Position(position.getX(), position.getY());
        Casilla actualCas = new Casilla( 0, actual, memory.getOrientation());
        mapa.put(actual, new ArrayList<Byte>());
        while (memory.getSeen(actual)){
            if( ( !memory.getSeen(actual.north()) && !memory.getNorth(actual) ) || ( !memory.getSeen(actual.south())  && !memory.getSouth(actual) ) || ( !memory.getSeen(actual.west())  && !memory.getWest(actual) ) || ( !memory.getSeen(actual.east())  && !memory.getEast(actual) ) ){
                break;
            }
            if( !memory.getNorth(actual) ){
                Position newPos = actual.north();
                Casilla newCas = new Casilla( actualCas.newWeigth( NORTH ), newPos, NORTH);
                lista.add(newCas);
                mapa.put(newPos, (ArrayList<Byte>) mapa.get(actual).clone());
                mapa.get(newPos).add(NORTH);
            }
            if( !memory.getEast(actual) ){
                Position newPos = actual.east();
                Casilla newCas = new Casilla( actualCas.newWeigth( EAST ), newPos, EAST);
                lista.add(newCas);
                mapa.put(newPos, (ArrayList<Byte>) mapa.get(actual).clone());
                mapa.get(newPos).add(EAST);
            }
            if( !memory.getSouth(actual) ){
                Position newPos = actual.south();
                Casilla newCas = new Casilla( actualCas.newWeigth( SOUTH ), newPos, SOUTH);
                lista.add(newCas);
                mapa.put(newPos, (ArrayList<Byte>) mapa.get(actual).clone());
                mapa.get(newPos).add(SOUTH);
            }
            if( !memory.getWest(actual) ){
                Position newPos = actual.west();
                Casilla newCas = new Casilla( actualCas.newWeigth( WEST ), newPos, WEST);
                lista.add(newCas);
                mapa.put(newPos, (ArrayList<Byte>) mapa.get(actual).clone());
                mapa.get(newPos).add(WEST);
            }
            actualCas = lista.poll();
            actual = actualCas.getPos();
        }
        System.out.println(actualCas);
        ArrayList<Byte> way = mapa.get(actual);
        System.out.println(way.size());
        for (int i = 0; i < way.size(); i++) {
            System.out.print(way.get(i)+" ");
        }
        System.out.println();
        while (!way.isEmpty()){
            switch (way.remove(0)){
                case NORTH:
                    moveNorth();
                    break;
                case EAST:
                    moveEast();
                    break;
                case SOUTH:
                    moveSouth();
                    break;
                case WEST:
                    moveWest();
                    break;
            }
        }
        state = EXPLORE;
    }

    private void _win(){
        this.actions.add(0, "no_op");
    }
    @Override
    public void init() {
        this.position = new Position(50, 50);
        this.state = EXPLORE;
        this.actions.clear();
        this.memory.init();
    }

    public void moveFront(){
        System.out.println("FRONT");
        this.position = this.position.front(memory.getOrientation());
        actions.add("advance");
    }

    public void moveRight(){
        System.out.println("RIGHT");
        this.position = this.position.right(memory.getOrientation());
        actions.add("rotate");
        actions.add("advance");
        memory.rotate(1);
    }

    public void moveBack(){
        System.out.println("BACK");
        this.position = this.position.back(memory.getOrientation());
        actions.add("rotate");
        actions.add("rotate");
        actions.add("advance");
        memory.rotate(2);
    }

    public void moveLeft(){
        System.out.println("LEFT");
        this.position = this.position.left(memory.getOrientation());
        actions.add("rotate");
        actions.add("rotate");
        actions.add("rotate");
        actions.add("advance");
        memory.rotate(3);
    }

    public void moveNorth(){
        switch (memory.getOrientation()){
            case NORTH:
                moveFront();
                break;
            case EAST:
                moveLeft();
                break;
            case SOUTH:
                moveBack();
                break;
            case WEST:
                moveRight();
                break;
        }
    }

    public void moveEast(){
        switch (memory.getOrientation()){
            case EAST:
                moveFront();
                break;
            case SOUTH:
                moveLeft();
                break;
            case WEST:
                moveBack();
                break;
            case NORTH:
                moveRight();
                break;
        }
    }

    public void moveSouth(){
        switch (memory.getOrientation()){
            case SOUTH:
                moveFront();
                break;
            case WEST:
                moveLeft();
                break;
            case NORTH:
                moveBack();
                break;
            case EAST:
                moveRight();
                break;
        }
    }

    public void moveWest(){
        switch (memory.getOrientation()){
            case WEST:
                moveFront();
                break;
            case NORTH:
                moveLeft();
                break;
            case EAST:
                moveBack();
                break;
            case SOUTH:
                moveRight();
                break;
        }
    }

    public void wait(int time){
        for (int i = 0; i < time; i++) {
            actions.add("no_op");
        }
    }
}
