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
