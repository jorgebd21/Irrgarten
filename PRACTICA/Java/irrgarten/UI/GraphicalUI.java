package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

// [cite: 19] Clase que hereda de JFrame y realiza la interfaz UI
public class GraphicalUI extends JFrame implements UI {

    // --- CONFIGURACIÓN DE TAMAÑOS ---
    private static final int LAB_ROWS = 13;
    private static final int LAB_COLS = 22;
    private static final int RIGHT_COLUMN_WIDTH = 500; // Ajustado al quitar controles

    private static final int FONT_SIZE_LAB = 18;
    private static final int FONT_SIZE_TEXT = 12;

    private JTextArea labyrinthArea;
    private JTextArea playersArea;
    private JTextArea monstersArea;
    private JTextArea logArea;
    private JLabel statusLabel;

    // [cite: 38] Atributo privado de la clase Cursors
    private Cursors cursors;

    public GraphicalUI() {
        setTitle("IRRGARTEN - JBD");
        //  No hay main, el programa principal instanciará esta clase
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initGUI();

        // [cite: 38] Inicialización de Cursors (this, true para modal)
        this.cursors = new Cursors(this, true);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        
        // [cite: 23] setVisible(true) tras inicializar componentes
        setVisible(true);
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        // [cite: 24] Instancias de JTextArea para mostrar estado
        
        // A. LABERINTO
        labyrinthArea = new JTextArea(LAB_ROWS, LAB_COLS);
        labyrinthArea.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE_LAB));
        labyrinthArea.setEditable(false);
        labyrinthArea.setBackground(new Color(240, 240, 240));
        labyrinthArea.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Laberinto"));
        
        // B. JUGADORES
        playersArea = new JTextArea(5, LAB_COLS);
        playersArea.setEditable(false);
        playersArea.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Jugadores"));
        
        // C. MONSTRUOS
        monstersArea = new JTextArea(5, LAB_COLS);
        monstersArea.setEditable(false);
        monstersArea.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Monstruos"));
        
        // D. REGISTRO
        logArea = new JTextArea(20, 15);
        logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Registro"));
        logScrollPane.setPreferredSize(new Dimension(RIGHT_COLUMN_WIDTH, 300));

        // ---------------------------------------------------------
        // LAYOUT
        // ---------------------------------------------------------
        
        // Columna Izquierda (Laberinto, Jugadores, Monstruos)
        c.gridx = 0; c.gridy = 0;
        mainPanel.add(labyrinthArea, c);

        c.gridy = 1;
        mainPanel.add(playersArea, c);

        c.gridy = 2;
        mainPanel.add(monstersArea, c);

        // Columna Derecha (Log)
        c.gridx = 1; c.gridy = 0;
        c.gridheight = 3; 
        c.fill = GridBagConstraints.BOTH;
        mainPanel.add(logScrollPane, c);

        getContentPane().setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // [cite: 28] Etiquetas (JLabel) para información adicional
        statusLabel = new JLabel("Esperando comenzar...", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    // [cite: 29] El método showGame lee los campos de GameState y los muestra
    @Override
    public void showGame(GameState gameState) {
        if (gameState == null) return;

        // Actualización de áreas de texto
        labyrinthArea.setText(gameState.getLabyrinth());
        playersArea.setText(gameState.getPlayers());
        monstersArea.setText(gameState.getMonsters());

        // Lógica visual del Log y estado
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
        logArea.setCaretPosition(logArea.getDocument().getLength());

        if (gameState.isWinner()) {
            statusLabel.setText("¡GANADOR: Jugador " + currentPlayerNumber + "!");
        } else {
            statusLabel.setText("Jugando: Jugador " + gameState.getCurrentPlayer());
        }

        // [cite: 30] Llamada a repaint al final
        repaint();
    }

    //  Delegamos en el cuadro de diálogo la comunicación con el usuario
    @Override
    public Directions nextMove() {
        // [cite: 43] nextMove delega en la instancia de Cursors (cursors.getDirection())
        return cursors.getDirection();
    }
}