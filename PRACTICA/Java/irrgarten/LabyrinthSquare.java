package irrgarten;

public class LabyrinthSquare {
    private int row;
    private int col;
    private char content;

    public LabyrinthSquare(int row, int col, char content) {
        this.row = row;
        this.col = col;
        this.content = content;
    }

    public char get() {
        return content;
    }

    public void set(int row, int col, char content) {
        this.row = row;
        this.col = col;
        this.content = content;
    }
}
