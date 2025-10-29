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
            switch(c) {
                case 'w':
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
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

        // Laberinto
        try {
            String lab = gameState.getLabyrinth();
            System.out.println("LABERINTO:");
            System.out.println(lab != null ? lab : "(vacío)");
        } catch (Exception e) {
            System.out.println("LABERINTO: (no disponible)");
        }
        System.out.println("----------------------------------------");

        // Jugadores
        try {
            String players = gameState.getPlayers();
            System.out.println("JUGADORES:");
            System.out.println(players != null ? players : "(vacío)");
        } catch (Exception e) {
            System.out.println("JUGADORES: (no disponible)");
        }
        System.out.println("----------------------------------------");

        // Monstruos
        try {
            String monsters = gameState.getMonsters();
            System.out.println("MONSTRUOS:");
            System.out.println(monsters != null ? monsters : "(vacío)");
        } catch (Exception e) {
            System.out.println("MONSTRUOS: (no disponible)");
        }
        System.out.println("----------------------------------------");

        // Información adicional
        try {
            System.out.println("Turno actual (índice jugador): " + gameState.getCurrentPlayer());
        } catch (Exception e) {
            // ignorar si no existe
        }

        try {
            System.out.println("Juego terminado: ");
        } catch (Exception e) {
            // ignorar si no existe
        }

        // Registro (log)
        try {
            String log = gameState.getLog();
            if (log != null && !log.isEmpty()) {
                System.out.println("----------------------------------------");
                System.out.println("REGISTRO:");
                System.out.println(log);
            }
        } catch (Exception e) {
            // ignorar si no existe
        }

        System.out.println("========================================");
        System.out.println();
    }
    
}
