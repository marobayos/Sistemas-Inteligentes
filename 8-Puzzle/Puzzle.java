
import java.util.*;

public class Puzzle {
    static final byte[][] goal = {{1, 2, 3},{4, 5, 6},{7, 8, 0 }};
    static final Board answer = new Board(goal);
    static Random rn = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        System.out.println("BFS\tIDDFS\tDFS \tMan\tMis");
        for (int i = 0; i < 1000; i++) {
            Board init = generate(10);
            System.out.print(BFS(init, answer)+"\t");
            System.out.print(iterativeDFS(init, answer)+"\t");
            System.out.print(DFS(init, answer)+"\t");
            System.out.print(asterixMan(init, answer)+"\t");
            System.out.println(asterixMis(init, answer));
        }
    }

    public static Board generate(int movements){
        int[] pos =  {2, 2};
        Board board = new Board(goal, pos);
        for (int i = 0; i < movements ; i++) {
            int newCoord = rn.nextInt(3);
            Board newBoard;
            if(i%2==1){
                int[] newPos = {pos[0], newCoord};
                newBoard = board.move(newPos);
            }else{
                int[] newPos = {newCoord, pos[1]} ;
                newBoard = board.move(newPos);
            }
            if(newBoard.equals(board)){
                i--;
            }else{
                board = newBoard;
                pos = newBoard.getPos();
            }
        }
        board.setDistance(0);
        return board;
    }

    public static boolean res = false;
    public static int iterativeDFS(Board initial, Board solution){
        int nodes = 0;
        res = false;
        for (int i = 0; !res; i++) {
            nodes += DFSIteration(initial, solution, i);
        }
        return nodes;
    }

    public static int BFS(Board initial, Board solution){
        LinkedList<Board> list  = new LinkedList<>();
        HashSet<Board> visited = new HashSet<>();
        list.add(initial);
        int nodes = 0;
        while( !list.isEmpty() ){
            nodes ++;
            Board board = list.removeFirst();
            if(!visited.contains(board))
                for( Board b : nextMovements(board) ){
                    if( b.equals(solution) )
                        return nodes;
                    list.addLast(b);
                }
            visited.add(board);
        }
        return nodes;
    }

    public static int DFSIteration(Board initial, Board solution, int maxDepth){
        LinkedList<Board> list  = new LinkedList<>();
        HashSet<Board> visited = new HashSet<>();
        list.add(initial);
        int nodes = 0;
        while( !list.isEmpty() ){
            nodes ++;
            Board board = list.removeFirst();
            if(!visited.contains(board) && board.getDistance() < maxDepth)
                for( Board b : nextMovements(board) ){
                    if( b.equals(solution) ){
                        res = true;
                        return nodes;
                    }
                    list.addFirst(b);
                }
            visited.add(board);
        }
        return nodes;
    }

    public static int DFS(Board initial, Board solution){
        LinkedList<Board> list  = new LinkedList<>();
        HashSet<Board> visited = new HashSet<>();
        list.add(initial);
        int nodes = 0;
        while( !list.isEmpty() ){
            nodes ++;
            Board board = list.removeFirst();
            if(!visited.contains(board))
                for( Board b : nextMovements(board) ){
                    if( b.equals(solution) )
                        return nodes;
                    list.addFirst(b);
                }
            visited.add(board);
        }
        return nodes;
    }

    public static int asterixMan(Board initial, Board solution) {
        PriorityQueue<Board> list = new PriorityQueue<>();
        list.add(initial);
        int nodes = 0;
        while (!list.isEmpty()){
            nodes++;
            for( Board b : nextMovements(list.poll()) ){
                if( b.equals(solution) )
                    return nodes;
                b.setDistanceTotal(b.getDistance()+b.mannhattan());
                list.add(b);
            }
        }
        return nodes;
    }

    public static int asterixMis(Board initial, Board solution) {
        PriorityQueue<Board> list = new PriorityQueue<>();
        list.add(initial);
        int nodes = 0;
        Board solved;
        while (!list.isEmpty()){
            nodes++;
            for( Board b : nextMovements(list.poll()) ){
                if( b.equals(solution) )
                    return nodes;
                b.setDistanceTotal(b.getDistance()+b.misplaced());
                list.add(b);
            }
        }
        return nodes;
    }

    public static LinkedList<Board> nextMovements(Board board){
        LinkedList<Board> list = new LinkedList<>();
        Board mod;
        int[] pos = board.getPos();
        if( pos[0] != 0 ){
            mod = board.move(pos[0]-1, pos[1]);
            mod.setDistanceTotal(mod.getDistance()+mod.misplaced());
            list.add(mod);
        }
        if( pos[0] != 2 ){
            mod = board.move(pos[0]+1, pos[1]);
            mod.setDistanceTotal(mod.getDistance()+mod.misplaced());
            list.add(mod);
        }
        if( pos[1] != 0 ){
            mod = board.move(pos[0], pos[1]-1);
            mod.setDistanceTotal(mod.getDistance()+mod.misplaced());
            list.add(mod);
        }
        if( pos[1] != 2 ){
            mod = board.move(pos[0], pos[1]+1);
            mod.setDistanceTotal(mod.getDistance()+mod.misplaced());
            list.add(mod);
        }
        return list;
    }

}
