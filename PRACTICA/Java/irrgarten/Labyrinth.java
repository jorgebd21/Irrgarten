package irrgarten;

import java.util.ArrayList;

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

    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;

    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;

        this.playerSquares = new PlayerSquare[nRows][nCols];
        this.monsterSquares = new MonsterSquare[nRows][nCols];
        this.labyrinthSquares = new LabyrinthSquare[nRows][nCols];

        this.players = new ArrayList<Player>();
        this.monsters = new ArrayList<Monster>();
    }
    public void spreadPlayers(Player[] players){
        
    }
    public boolean haveAWinner(){
        return (playerSquares[exitRow][exitCol] != null);
    }
    public String toString(){
        String salida = "";
        for(int r=0; r<nRows; r++){
            for(int c=0; c<nCols; c++){
                salida += labyrinthSquares[r][c].toString();
                salida += playerSquares[r][c].toString();
                salida += monsterSquares[r][c].toString();
            }
            salida += "\n";
        }
        return salida;
    }
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col) && emptyPos(row, col)){
            labyrinthSquares[row][col] = new LabyrinthSquare(row, col, MONSTER_CHAR, this);
            monsterSquares[row][col] = new MonsterSquare(row, col, monster);
            monsters.add(monster);
            monster.setPos(row, col);
        }
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
        return (row>=0) && (row<nRows) && (col>=0) && (col<nCols);
    }
    private boolean emptyPos(int row, int col){
        return (playerSquares[row][col]==null) && (labyrinthSquares[row][col].getContent()==EMPTY_CHAR);
    }
    private boolean monsterPos(int row, int col){
        return (monsterSquares[row][col]!=null) && (playerSquares[row][col]==null);
    }
    private boolean exitPos(int row, int col){
        return (row==exitRow) && (col==exitCol);
    }
    private boolean combatPos(int row, int col){
        return (monsterSquares[row][col]!=null) && (playerSquares[row][col]!=null);
    }
    private boolean canStepOn(int row, int col){
        return (emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col)) && (posOK(row, col));
    }
    private void updateOldPos(int row, int col){
        if(posOK(row, col)){
            if(monsterPos(row, col)){
                labyrinthSquares[row][col].setContent(MONSTER_CHAR);
            } else if (exitPos(row, col)){
                labyrinthSquares[row][col].setContent(EXIT_CHAR);
            } else if (combatPos(row, col)){
                labyrinthSquares[row][col].setContent(COMBAT_CHAR);
            } else {
                labyrinthSquares[row][col].setContent(EMPTY_CHAR);
            }
        }
    }
    private int[] dir2Pos(int row, int col, Directions direction){
        int[] newPos = new int[2];
        int H=0, V=0;
        newPos[0] = row;
        newPos[1] = col;
        switch(direction){
            case UP:
                H= -1;
                break;
            case DOWN:
                H= 1;
                break;
            case LEFT:
                V= -1;
                break;
            case RIGHT:
                V= 1;
                break;
        }
        while(canStepOn(newPos[0]+H, newPos[1]+V)){
            newPos[0] += H;
            newPos[1] += V;
            
        }
        return newPos;
    }
    private int[] randomEmptyPos(){
        boolean found = false;
        int [] pos = new int[2];
        do{
            pos[0] = Dice.randomPos(nRows);
            pos[1] = Dice.randomPos(nCols);
            if(emptyPos(pos[0], pos[1])){
                found = true;
            }
        }while(!found);
        return  pos;
    }
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col){
        throw new UnsupportedOperationException();
    }
}
