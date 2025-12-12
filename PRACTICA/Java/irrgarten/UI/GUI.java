// File: GUI.java (en el paquete irrgarten.UI)
package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.*;

public class GUI extends JFrame { // El nombre de la clase ha cambiado a GUI

    private JTextArea labyrinthArea;
    private JTextArea playersArea;
    private JTextArea monstersArea;
    private JTextArea logArea;
    private JLabel statusLabel;
    
    // Cola para almacenar la dirección elegida por el usuario
    private BlockingQueue<Directions> nextMoveQueue;

    public GUI() {
        // Configuración básica del Frame
        setTitle("IrRGARTEN - El Laberinto de los Monstruos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Inicializar la cola para la comunicación
        nextMoveQueue = new LinkedBlockingQueue<>();

        // Configurar la interfaz
        initGUI();

        pack(); // Ajusta el tamaño del frame a sus componentes
        setResizable(false); // Recomendado para mantener la proporción de la UI
        setVisible(true);
    }

    private void initGUI() {
        // Usaremos un BorderLayout para la estructura principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding general

        // --- Panel Central (para el Laberinto) ---
        labyrinthArea = new JTextArea(20, 40); 
        labyrinthArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
        labyrinthArea.setEditable(false);
        JScrollPane labyrinthScrollPane = new JScrollPane(labyrinthArea);
        labyrinthScrollPane.setBorder(BorderFactory.createTitledBorder("LABERINTO"));

        // --- Panel de Información (Jugadores, Monstruos, Log) ---
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); 

        playersArea = createInfoArea("JUGADORES:", 5, 40);
        monstersArea = createInfoArea("MONSTRUOS:", 5, 40);
        logArea = createInfoArea("REGISTRO:", 10, 40);
        
        // Usamos un JSeparator para simular las líneas de separación del TextUI
        infoPanel.add(playersArea);
        infoPanel.add(Box.createVerticalStrut(5)); // Espacio vertical
        infoPanel.add(new JSeparator());
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(monstersArea);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(new JSeparator());
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(logArea);

        // --- Status Bar (Turno actual/Mensajes de fin de juego) ---
        statusLabel = new JLabel("Esperando comenzar...", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); 
        
        // --- Ensamblaje de Paneles al Frame ---
        mainPanel.add(labyrinthScrollPane, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.EAST);
        add(mainPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        
        // --- Manejo de la entrada del teclado ---
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Directions dir = null;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W, KeyEvent.VK_UP -> dir = Directions.UP;
                    case KeyEvent.VK_S, KeyEvent.VK_DOWN -> dir = Directions.DOWN;
                    case KeyEvent.VK_A, KeyEvent.VK_LEFT -> dir = Directions.LEFT;
                    case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> dir = Directions.RIGHT;
                }
                
                if (dir != null) {
                    nextMoveQueue.offer(dir);
                }
            }
        });
        
        setFocusable(true); 
    }
    
    private JTextArea createInfoArea(String title, int rows, int cols) {
        JTextArea area = new JTextArea(rows, cols);
        area.setEditable(false);
        // Quitar setBorder para usar el título del panel directamente, pero mantendremos la decoración para el ejemplo
        area.setBorder(BorderFactory.createTitledBorder(title)); 
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        return area;
    }

    public void showGame(GameState gameState) {
        if (gameState == null) {
            statusLabel.setText("Estado del juego nulo.");
            return;
        }

        // 1. Actualizar Laberinto
        labyrinthArea.setText(gameState.getLabyrinth());
        
        // 2. Actualizar Jugadores
        playersArea.setText(gameState.getPlayers());
        
        // 3. Actualizar Monstruos
        monstersArea.setText(gameState.getMonsters());
        
        // 4. Actualizar Log
        logArea.setText(
            "Turno actual (índice jugador): " + gameState.getCurrentPlayer() + "\n" +
            gameState.getLog()
        );
        
        // 5. Actualizar Estado
        if (gameState.isWinner()) {
            // El jugador actual es el ganador según la lógica de 'Game.nextStep()'
            statusLabel.setText("¡¡¡JUEGO TERMINADO!!! El ganador es: Jugador " + (gameState.getCurrentPlayer() + 1));
        } else {
            statusLabel.setText("Esperando movimiento (W: UP, A: LEFT, S: DOWN, D: RIGHT)...");
        }
        
        requestFocusInWindow();
    }

    public Directions nextMove() {
        try {
            // Espera y toma la dirección de la cola (se bloquea hasta que se pulsa una tecla)
            return nextMoveQueue.take(); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Directions.DOWN; 
        }
    }
}