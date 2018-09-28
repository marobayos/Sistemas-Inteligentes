public class Board {
    private byte[][] board = new byte[3][3];
    private int[] pos = new int[2];

    private int distancia;

    public Board(byte[][] board){
        this.board = board;
    }

    public Board(byte[][] board, int[] pos){
        this.board = board;
        this.pos = pos;
    }

    public int getDistancia(){
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int mannhattan(byte[][] goal){
        int res = 0;
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3 ; j++) {
                int[] my = search(goal[i][j]);
                res += Math.abs(my[0]-i) + Math.abs(my[0]-j);
            }
        }
        return res;
    }

    public int misplaced(byte[][] goal){
        int res = 0;
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3 ; j++) {
                res += (goal[i][j] == this.board[i][j])? 0 : 1;
            }
        }
        return res;
    }

    public Board move(int[] newPos){
        byte[][] res = {{board[0][0], board[0][1], board[0][2]},{board[1][0], board[1][1], board[1][2]},{board[2][0], board[2][1], board[2][2]}};
        if( newPos[0] == pos[0] ){
            if (newPos[1] < pos[1]){
                for (int i = newPos[1]+1; i <= pos[1]; i++)
                    res[pos[0]][i] = this.board[pos[0]][i-1];
            } else {
                for (int i = pos[1] ; i < newPos[1]; i++)
                    res[pos[0]][i] = this.board[pos[0]][i+1];
            }
        } else if( newPos[1] == pos[1] ){
            if (newPos[0] < pos[0]){
                for (int i = newPos[0]+1; i <= pos[0]; i++)
                    res[i][pos[1]] = this.board[i-1][pos[1]];
            } else {
                for (int i = pos[0]; i < newPos[0]; i++)
                    res[i][pos[1]] = this.board[i+1][pos[1]];
            }
        }
        res[newPos[0]][newPos[1]] = 0;
        Board newBoard = new Board(res, newPos);
        newBoard.distancia = this.distancia + 1;
        return newBoard;
    }

    public Board move(int x, int y){
        int[] newPos = {x, y};
        return move(newPos);
    }

    public String toString(){
        String str = board[0][0]+" "+board[0][1]+" "+board[0][2]+"\n";
        str += board[1][0]+" "+board[1][1]+" "+board[1][2]+"\n";
        str += board[2][0]+" "+board[2][1]+" "+board[2][2]+"\n";
        return str;
    }

    @Override
    public boolean equals(Object o){
        Board otherBoard = (Board)o;
        if( board.length != otherBoard.board.length || board[0].length != otherBoard.board[0].length )
            return false;
        for (int i = 0; i < board.length ; i++) {
            for (int j = 0; j < board[i].length ; j++) {
                if( board[i][j] != otherBoard.board[i][j] )
                    return false;
            }
        }
        return true;
    }

    public int[] search(byte val){
        int[] res = new int[2];
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j] == val){
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    public int[] getPos(){
        int[] res = {pos[0], pos[1]};
        return res;
    }
}
