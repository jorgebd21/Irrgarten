package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;

public class TextUI {

    private static Scanner in = new Scanner(System.in);
    
    private char readChar() {
        String s = in.nextLine();
        return s.charAt(0);
    }

    public Directions nextMove() {
        System.out.print("Where? ");

        Directions direction = Directions.DOWN;
        boolean gotInput = false;

        while (!gotInput) {
            char c = readChar();
            switch (c) {
                case 'w' -> {
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                }
                case 's' -> {
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                }
                case 'd' -> {
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                }
                case 'a' -> {
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;
                }
            }
        }
        return direction;
    }

    public void showGame(GameState gameState) {
        if (gameState == null) {
            System.out.println("Estado del juego nulo.");
            return;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("              ESTADO DEL JUEGO          ");
        System.out.println("========================================");
        System.out.println();
        System.out.println("LABERINTO:");
        System.out.println(gameState.getLabyrinth());
        System.out.println("----------------------------------------");
        System.out.println("JUGADORES:");
        System.out.println(gameState.getPlayers());
        System.out.println("----------------------------------------");
        System.out.println("MONSTRUOS:");
        System.out.println(gameState.getMonsters());
        System.out.println("----------------------------------------");
        System.out.println("REGISTRO:");
        System.out.println("Turno actual (índice jugador): " + gameState.getCurrentPlayer());
        System.out.println(gameState.getLog());
        System.out.println("========================================");
        if (gameState.isWinner()) {
            System.out.println("¡¡¡JUEGO TERMINADO!!!");
            System.out.println("========================================");
        }
        System.out.println();
    }

}
