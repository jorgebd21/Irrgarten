package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;

public class GraphicUI extends javax.swing.JFrame implements UI {
    private static final java.util.logging.Logger logger = java.util.logging.Logger
            .getLogger(GraphicUI.class.getName());
    private Cursors cursors;

    public GraphicUI() {
        setTitle("IRRGARTEN");
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
        this.cursors = new Cursors(this, true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        laberinto = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jugadores = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        monstruos = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        registro = new javax.swing.JTextArea();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IRRGARTEN - JBD");
        laberinto.setColumns(20);
        laberinto.setFont(new java.awt.Font("Monospaced", 0, 15));
        laberinto.setRows(5);
        laberinto.setBorder(javax.swing.BorderFactory.createTitledBorder("Laberinto"));
        jScrollPane1.setViewportView(laberinto);
        jugadores.setColumns(20);
        jugadores.setRows(5);
        jugadores.setBorder(javax.swing.BorderFactory.createTitledBorder("Jugadores"));
        jScrollPane2.setViewportView(jugadores);
        monstruos.setColumns(20);
        monstruos.setRows(5);
        monstruos.setBorder(javax.swing.BorderFactory.createTitledBorder("Monstruos"));
        jScrollPane3.setViewportView(monstruos);
        registro.setColumns(20);
        registro.setRows(5);
        registro.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro"));
        jScrollPane5.setViewportView(registro);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1).addComponent(jScrollPane2)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                        .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                        .addComponent(jScrollPane5))
                .addContainerGap()));
        pack();
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jugadores;
    private javax.swing.JTextArea laberinto;
    private javax.swing.JTextArea monstruos;
    private javax.swing.JTextArea registro;

    @Override
    public void showGame(GameState gameState) {
        if (gameState == null) {
            return;
        }
        laberinto.setText(gameState.getLabyrinth());
        jugadores.setText(gameState.getPlayers());
        monstruos.setText(gameState.getMonsters());
        String currentLog = gameState.getLog();
        boolean isStartOfGame = registro.getText().trim().isEmpty();
        int currentPlayerNumber = gameState.getCurrentPlayer() + 1;
        if (isStartOfGame) {
            registro.append("Turno del jugador " + currentPlayerNumber + ":\n");
        } else {
            registro.append(currentLog + "\n");
            registro.append("-----------------\n");
            if (!gameState.isWinner()) {
                registro.append("Turno del jugador " + currentPlayerNumber + ":\n");
            } else {
                registro.append("\n¡ VICTORIA PARA EL JUGADOR " + currentPlayerNumber + " !\n");
            }
        }
        registro.setCaretPosition(registro.getDocument().getLength());
        this.repaint();
    }

    @Override
    public Directions nextMove() {
        return cursors.getDirection();
    }
}