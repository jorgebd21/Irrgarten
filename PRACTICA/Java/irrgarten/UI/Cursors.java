package irrgarten.UI;

import irrgarten.Directions;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cursors extends JDialog {

    private Directions direction;

    public Cursors(JFrame parent, boolean modal) {
        super(parent, modal);
        setTitle("Movimiento");
        initComponents();
        pack();
        setLocationRelativeTo(parent); // Centrar sobre la ventana padre
    }

    private void initComponents() {
        setLayout(new GridLayout(2, 3, 5, 5));
        
        // [cite: 33] Botones para representar cada dirección
        JButton btnW = createButton("UP", Directions.UP);
        JButton btnA = createButton("LEFT", Directions.LEFT);
        JButton btnS = createButton("DOWN", Directions.DOWN);
        JButton btnD = createButton("RIGHT", Directions.RIGHT);

        add(new JLabel("")); // Hueco vacío
        add(btnW);
        add(new JLabel("")); // Hueco vacío
        add(btnA);
        add(btnS);
        add(btnD);
    }

    private JButton createButton(String text, Directions dir) {
        JButton btn = new JButton(text);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direction = dir;
                dispose(); 
            }
        });
        return btn;
    }

    public Directions getDirection() {
        this.direction = null;
        setVisible(true);
        return direction;
    }
}