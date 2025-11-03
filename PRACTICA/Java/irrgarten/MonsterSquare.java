package irrgarten;

public class MonsterSquare {
    private int row;
    private int col;
    private Monster monster;

    public MonsterSquare(int row, int col, Monster monster) {
        this.row = row;
        this.col = col;
        this.monster = monster;
    }

    public Monster get(){
        return monster;
    }

    public void set(int row, int col, Monster monster){
        this.row = row;
        this.col = col;
        this.monster = monster;
    }
}
