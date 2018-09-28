import java.util.*;

public class BFS {
    static final byte[][] goal = {{1, 2, 3},{4, 5, 6},{7, 8, 0 }};
    static final int[] initPos = {2,2};
    static final Board answer = new Board(goal);
    static Random rn = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Board init = generate(5);
            //System.out.println(BFS(init, answer)+"\t"+DFS(init, answer)+"\t"+aMannhattan(init, answer));
            System.out.print(BFS(init, answer)+"\t");
            System.out.print(iterativeDFS(init, answer)+"\t");
            System.out.println(DFS(init, answer, 100000));
            //System.out.println(aMannhattan(init, answer));
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

    public static int aMannhattan(Board initial, Board solution){
        PriorityQueue<Board> list = new PriorityQueue<Board>();
        int nodes = 0;
        list.add(initial);
        while( !list.element().equals(solution) ){
            nodes ++;
            Board board = list.remove();
            int []pos = board.getPos();
            for (int i = 0; i < 3 ; i++) {
                    list.add(board.move(pos[0], i));
                    nodes++;
                    list.add(board.move(i, pos[1]));
                    nodes++;
            }
//            System.out.println(list.size());
        }
        return nodes;
    }

    public static boolean res = false;
    public static int iterativeDFS(Board initial, Board solution){
        LinkedList<Board> list  = new LinkedList<>();
        list.add(initial);
        int nodes = 0;
        res = false;
        for (int i = 0; !res; i++) {
            nodes += DFS(initial, solution, i);
        }
        return nodes;
    }

    public static int BFS(Board initial, Board solution){
        LinkedList<Board> list  = new LinkedList<>();
        list.add(initial);
        int nodes = 0;
        while( !list.getFirst().equals(solution)){
            nodes ++;
            Board board = list.removeFirst();
            int []pos = board.getPos();
            for (int i = 0; i < 3 ; i++) {
                if ( !list.contains(board.move(pos[0], i)) ){
                    list.addLast(board.move(pos[0], i));
                    nodes++;
                }
                if ( !list.contains(board.move(i, pos[1])) ) {
                    list.addLast(board.move(i, pos[1]));
                    nodes++;
                }
            }
        }
        return nodes;
    }

    public static int DFS(Board initial, Board solution, int maxDepth){
        LinkedList<Board> list  = new LinkedList<>();
        HashSet<Board> visited = new HashSet<>();
        list.add(initial);
        visited.add(initial);
        int nodes = 0;
        while( !list.isEmpty() ){
            nodes ++;
            Board board = list.removeFirst();
            if( board.getDistance()> maxDepth )
                continue;
            int []pos = board.getPos();
            for (int i = 0; i < 3 ; i++) {
                if( board.move(pos[0], i).equals(answer) || board.move(i, pos[1]).equals(answer)){
                    res = true;
                    return nodes;
                }
                if ( !(visited.contains(board.move(pos[0], i)) || board.move(pos[0], i).equals(board) )){
                    list.addFirst(board.move(pos[0], i));
                    visited.add(board.move(pos[0], i));
                    nodes++;
                }
                if ( !(visited.contains(board.move(i, pos[1])) || board.move(i, pos[1]).equals(board)) ) {
                    list.addFirst(board.move(i, pos[1]));
                    visited.add(board.move(i, pos[1]));
                    nodes++;
                }
            }
        }
        return nodes;
    }
}
