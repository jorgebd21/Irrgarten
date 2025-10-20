package irrgarten;

public class LabyrinthSquare {
    private int row;
    private int col;
    private char content;
    private Labyrinth labyrinth;

    public LabyrinthSquare(int row, int col, char content, Labyrinth labyrinth) {
        this.row = row;
        this.col = col;
        this.content = content;
        this.labyrinth = labyrinth;
    }
}
