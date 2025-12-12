package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

// [cite: 19] Hereda de JFrame y realiza la interfaz UI
public class GraphicalUI extends JFrame implements UI {

    // Atributos de la interfaz
    private JTextArea labyrinthArea;
    private JTextArea playersArea;
    private JTextArea monstersArea;
    private JTextArea logArea;
    private JLabel statusLabel;

    // [cite: 38] Atributo privado para la entrada de movimiento
    private Cursors cursors;

    public GraphicalUI() {
        setTitle("IRRGARTEN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuración de la ventana
        initGUI();

        // [cite: 38, 39, 40] Inicialización de Cursors (this, true para modo modal)
        this.cursors = new Cursors(this, true);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        
        // [cite: 23] Hacer visible la ventana al final del constructor
        setVisible(true);
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;

        // [cite: 24] Creación de JTextAreas
        // 1. Laberinto
        labyrinthArea = new JTextArea(13, 22);
        labyrinthArea.setFont(new Font("Monospaced", Font.BOLD, 18));
        labyrinthArea.setEditable(false);
        labyrinthArea.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Laberinto"));
        
        // 2. Jugadores
        playersArea = new JTextArea(5, 22);
        playersArea.setEditable(false);
        playersArea.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Jugadores"));

        // 3. Monstruos
        monstersArea = new JTextArea(5, 22);
        monstersArea.setEditable(false);
        monstersArea.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Monstruos"));

        // 4. Log (Registro)
        logArea = new JTextArea(20, 15);
        logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Registro"));

        // --- COLOCACIÓN EN EL LAYOUT ---
        // Columna Izquierda
        c.gridx = 0; c.gridy = 0; 
        mainPanel.add(labyrinthArea, c);
        
        c.gridy = 1;
        mainPanel.add(playersArea, c);
        
        c.gridy = 2;
        mainPanel.add(monstersArea, c);

        // Columna Derecha (ocupa 3 filas)
        c.gridx = 1; c.gridy = 0; 
        c.gridheight = 3;
        mainPanel.add(logScrollPane, c);

        // Añadir panel principal
        getContentPane().setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // [cite: 28] Etiqueta de estado inferior
        statusLabel = new JLabel("Esperando inicio...", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    // [cite: 29] Lee todos los campos de GameState y los muestra
    @Override
    public void showGame(GameState gameState) {
        if (gameState == null) return;

        // Actualizar los 3 paneles principales
        labyrinthArea.setText(gameState.getLabyrinth());
        playersArea.setText(gameState.getPlayers());
        monstersArea.setText(gameState.getMonsters());

        // Lógica para formatear el Log
        String currentLog = gameState.getLog();
        boolean isStartOfGame = logArea.getText().trim().isEmpty();
        int currentPlayerNumber = gameState.getCurrentPlayer() + 1;

        if (isStartOfGame) {
            logArea.append("Turno del jugador " + currentPlayerNumber + ":\n");
        } else {
            logArea.append(currentLog + "\n");
            logArea.append("-----------------\n");
            if (!gameState.isWinner()) {
                logArea.append("Turno del jugador " + currentPlayerNumber + ":\n");
            }
        }
        // Auto-scroll al final del log
        logArea.setCaretPosition(logArea.getDocument().getLength());

        // Actualizar etiqueta inferior
        if (gameState.isWinner()) {
            statusLabel.setText("¡GANADOR: Jugador " + currentPlayerNumber + "!");
        } else {
            statusLabel.setText("Jugando: Jugador " + gameState.getCurrentPlayer());
        }

        //  Llamada obligatoria a repaint
        repaint();
    }

    //  Solicita la dirección delegando en Cursors
    @Override
    public Directions nextMove() {
        // [cite: 43] Ejecuta getDirection que hace visible el cuadro de diálogo
        return cursors.getDirection();
    }
}