public class OX {
    String table[][] = {
            {" ", "0", "1", "2"},
            {"0", "-", "-", "-"},
            {"1", "-", "-", "-"},
            {"2", "-", "-", "-"},

    };
    private String currentPlayer;
    private int turnCount;
    private int scoreX;
    private int scoreO;
    private int scoreDraw;

    public OX(){
        currentPlayer = "X";
        turnCount = 0;
        scoreX = 0;
        scoreO = 0;
        scoreDraw = 0;
    }

    public String getTableString() {
        String result = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result = result + table[i][j];
            }

            result = result + "\n";
        }
        return result;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        if( currentPlayer.equals("X")){
            currentPlayer = "O";
        }else{
            currentPlayer = "X";
        }
    }

    public boolean put(int row, int column) {
        try{
            if(!table[row+1][column+1].equals("-")){
                return false;
            }
            table[row+1][column+1] = currentPlayer;
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
        turnCount++;
        if(checkWin(row, column)){
            if(currentPlayer.equals("X")){
                scoreX++;
            }else if(currentPlayer.equals("O")){
                scoreO++;
            }
        }

        if(isDraw()){
            scoreDraw++;
        }
        return true;
    }

    public String get(int row, int column) {
        if(row > 2 || row < 0 || column > 2 || column < 0){
            return null;
        }
        return table[row+1][column+1];
    }

    public boolean checkWin(int row, int column) {
        /*checkColWin*/
        boolean colWin = true;
        for(int i = 0; i < 3 ; i++){
            if(!table[i+1][column+1].equals(currentPlayer)){
                colWin = false;
            }
        }
        if(colWin){
            return true;
        }

        /*checkRowWin*/
        boolean RowWin = true;
        for(int i = 0; i < 3 ; i++){
            if(!table[row+1][i+1].equals(currentPlayer)){
                RowWin = false;
            }
        }
        if(RowWin){
            return true;
        }

        /*checkEsWin*/
        boolean esWin = true;
        for(int i = 0; i < 3 ; i++){
            if(!table[i+1][i+1].equals(currentPlayer)){
                esWin = false;
            }
        }
        if(esWin){
            return true;
        }

        /*checkSSWin*/
        boolean ssWin = true;
        for(int i = 0; i < 3 ; i++){
            /*row,column -> 0,2 1,1 2,0*/
            /*column,row -> 3,1 2,2 1,3*/
            /*column,row -> i:0 i+1 3-i , i:1 2,2 3,1*/

            if(!table[i+1][3-i].equals(currentPlayer)){
                ssWin = false;
            }
        }
        if(ssWin){
            return true;
        }
        return false;
    }

    public void reset() {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                table[i+1][j+1] = "-";
            }
        }
        currentPlayer = "X";
        turnCount = 0;
    }

    public int getTurnCount() {
        return  turnCount;
    }

    public boolean isDraw() {
        if(turnCount < 9){
            return false;
        }
        return true;
    }

    public int getScoreX() {
        return scoreX;
    }

    public int getScoreO() {
        return scoreO;
    }

    public int getScoreDraw() {
        return scoreDraw;
    }
}
