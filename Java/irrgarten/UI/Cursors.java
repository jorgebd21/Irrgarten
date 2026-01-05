package irrgarten.UI;

import irrgarten.Directions;

public class Cursors extends javax.swing.JDialog {;
    private Directions direction;

    public Cursors(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        button_w = new javax.swing.JButton();
        button_a = new javax.swing.JButton();
        button_s = new javax.swing.JButton();
        button_d = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        button_w.setText("W");
        button_w.addActionListener(this::button_wActionPerformed);
        button_a.setText("A");
        button_a.addActionListener(this::button_aActionPerformed);
        button_s.setText("S");
        button_s.addActionListener(this::button_sActionPerformed);
        button_d.setText("D");
        button_d.addActionListener(this::button_dActionPerformed);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addContainerGap()
                .addComponent(
                        button_a, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(button_w, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .addComponent(button_s, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_d, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addComponent(button_w, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(button_a, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(button_s, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(button_d, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pack();
    }

    private void button_aActionPerformed(java.awt.event.ActionEvent evt) {
        direction = Directions.LEFT;
        dispose();
    }

    private void button_sActionPerformed(java.awt.event.ActionEvent evt) {
        direction = Directions.DOWN;
        dispose();
    }

    private void button_wActionPerformed(java.awt.event.ActionEvent evt) {
        direction = Directions.UP;
        dispose();
    }

    private void button_dActionPerformed(java.awt.event.ActionEvent evt) {
        direction = Directions.RIGHT;
        dispose();
    }

    private javax.swing.JButton button_a;
    private javax.swing.JButton button_d;
    private javax.swing.JButton button_s;
    private javax.swing.JButton button_w;

    public Directions getDirection() {
        setVisible(true);
        return direction;
    }
}