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

    private PlayerSquare[][] players;
    private MonsterSquare[][] monsters;
    private LabyrinthSquare[][] labyrinth;


    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;

        this.players = new PlayerSquare[nRows][nCols];
        this.monsters = new MonsterSquare[nRows][nCols];
        this.labyrinth = new LabyrinthSquare[nRows][nCols];
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                this.labyrinth[r][c] = new LabyrinthSquare(r, c, EMPTY_CHAR);
            }
        }
        this.labyrinth[exitRow][exitCol] = new LabyrinthSquare(exitRow, exitCol, EXIT_CHAR);
    }
    public void spreadPlayers(Player[] player){
        for(int i=0; i<player.length; i++){
            Player p = player[i];
            int[] pos = randomEmptyPos();
            putPlayer2D(-1, -1, pos[0], pos[1], p);
        }
    }
    public boolean haveAWinner(){
        return (players[exitRow][exitCol] != null);
    }
    @Override
    public String toString(){
        String salida = "";
        for(int r=0; r<nRows; r++){
            for(int c=0; c<nCols; c++){
                if(players[r][c] != null){
                    salida += players[r][c].toString();
                }else{
                    salida += labyrinth[r][c].getContent();
                }
            }
            salida += "\n";
        }
        return salida;
    }
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col) && emptyPos(row, col)){
            labyrinth[row][col] = new LabyrinthSquare(row, col, MONSTER_CHAR);
            monsters[row][col] = new MonsterSquare(row, col, monster);
            monsters[row][col].getMonster().setPos(row, col);
        }
    }
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow, oldCol, direction);
        return putPlayer2D(oldRow, oldCol, newPos[0], newPos[1], player);
    }
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int incRow, incCol;
        if(orientation == orientation.VERTICAL){
            incRow = 1;
            incCol = 0;
        }else{
            incRow = 0;
            incCol = 1;
        }

        int row = startRow;
        int col = startCol;

        while(posOK(row,col) && (emptyPos(row, col) && (length > 0))){
            labyrinth[row][col].setContent(BLOCK_CHAR);
            length -= 1;
            row += incRow;
            col += incCol;
        }
    }
    public ArrayList<Directions> validMoves(int row, int col){
        ArrayList<Directions> output = new ArrayList<Directions>();
        if(canStepOn(row-1, col)){
            output.add(Directions.UP);
        }
        if (canStepOn(row+1, col)){
            output.add(Directions.DOWN);
        }
        if (canStepOn(row, col-1)){
            output.add(Directions.LEFT);
        }
        if (canStepOn(row, col+1)){
            output.add(Directions.RIGHT);
        }
        return output;
    }
    private boolean posOK(int row, int col){
        return (row>=0) && (row<nRows) && (col>=0) && (col<nCols);
    }
    private boolean emptyPos(int row, int col){
        return (players[row][col]==null) && (labyrinth[row][col].getContent()==EMPTY_CHAR);
    }
    private boolean monsterPos(int row, int col){
        return (monsters[row][col]!=null) && (players[row][col]==null);
    }
    private boolean exitPos(int row, int col){
        return (row==exitRow) && (col==exitCol);
    }
    private boolean combatPos(int row, int col){
        return (monsters[row][col]!=null) && (players[row][col]!=null);
    }
    private boolean canStepOn(int row, int col){
        if (!posOK(row, col)) return false;
        return emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col);
    }
    private void updateOldPos(int row, int col){
        if(posOK(row, col)){
            if(monsterPos(row, col)){
                labyrinth[row][col].setContent(MONSTER_CHAR);
            } else if (exitPos(row, col)){
                labyrinth[row][col].setContent(EXIT_CHAR);
            } else if (combatPos(row, col)){
                labyrinth[row][col].setContent(COMBAT_CHAR);
            } else {
                labyrinth[row][col].setContent(EMPTY_CHAR);
            }
        }
    }
    private int[] dir2Pos(int row, int col, Directions direction) {
        int[] newPos = new int[] { row, col };
        int dRow = 0, dCol = 0;
        switch (direction) {
            case UP:
                dRow = -1;
                break;
            case DOWN:
                dRow = 1;
                break;
            case LEFT:
                dCol = -1;
                break;
            case RIGHT:
                dCol = 1;
                break;
            default:
                break;
        }

        int nextRow = newPos[0] + dRow;
        int nextCol = newPos[1] + dCol;
        while (canStepOn(nextRow, nextCol)) {
            newPos[0] = nextRow;
            newPos[1] = nextCol;
            nextRow = newPos[0] + dRow;
            nextCol = newPos[1] + dCol;
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
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        Monster output = null;
        if(canStepOn(row, col)){
            if(posOK(oldRow, oldCol)){
                Player p = players[oldRow][oldCol].get();
                if(p == player){
                    players[oldRow][oldCol]=null;
                    updateOldPos(oldRow, oldCol);
                }
            }

            boolean monsterPos = monsterPos(row, col);
            if(monsterPos){
                labyrinth[row][col].setContent(COMBAT_CHAR);
                output = monsters[row][col].getMonster();
            }else{
                char number = player.getNumber();
                labyrinth[row][col].setContent(number);
            }

            players[row][col] = new PlayerSquare(row, col, player);
            player.setPos(row, col);
        }

        return output;
    }
}
