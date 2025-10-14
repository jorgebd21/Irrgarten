package irrgarten;

public class Labyrinth {
    static private char BLOCK_CHAR = 'X';
    static private char EMPTY_CHAR = '-';
    static private char MONSTER_CHAR = 'M';
    static private char COMBAT_CHAR = 'C';
    static private char EXIT_CHAR = 'E';
    static private int ROW = 0;
    static private int COL = 1;

    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;

    private PlayerSquare[][] playerSquares;
    private MonsterSquare[][] monsterSquares;
    private LabyrinthSquare[][] labyrinthSquares;

    public Labyrinth(int nRows, int nCols) {

    }
    public void spreadPlayers(Player[] players){
        
    }
    public boolean haveAWinner(){
        throw new UnsupportedOperationException();
    }
    public String toString(){
        throw new UnsupportedOperationException();
    }
    public void addMonster(int row, int col, Monster monster){
        
    }
    public Monster putPlayer(Directions direction, Player player){
        throw new UnsupportedOperationException();
    }
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){

    }
    public Directions[] validMoves(int row, int col){
        throw new UnsupportedOperationException();
    }
    private boolean posOK(int row, int col){
        throw new UnsupportedOperationException();
    }
    private boolean emptyPos(int row, int col){
        throw new UnsupportedOperationException();
    }
    private boolean monsterPos(int row, int col){
        throw new UnsupportedOperationException();
    }
    private boolean exitPos(int row, int col){
        throw new UnsupportedOperationException();
    }
    private boolean combatPos(int row, int col){
        throw new UnsupportedOperationException();
    }
    private boolean canStepOn(int row, int col){
        throw new UnsupportedOperationException();
    }
    private void updateOldPos(int row, int col){
        
    }
    private int[] dir2Pos(int row, int col, Directions direction){
        throw new UnsupportedOperationException();
    }
    private int[] randomEmptyPos(){
        throw new UnsupportedOperationException();
    }
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col){
        throw new UnsupportedOperationException();
    }
}
