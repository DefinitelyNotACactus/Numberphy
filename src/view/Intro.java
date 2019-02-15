/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author david
 */
public class Intro extends JFrame {

    /**
     * Creates new form Intro
     */
    public Intro() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        optionsPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        halleyButton = new javax.swing.JButton();
        riddersButton = new javax.swing.JButton();
        hermiteButton = new javax.swing.JButton();
        splinesButton = new javax.swing.JButton();
        gaussButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 455));
        setPreferredSize(new java.awt.Dimension(1000, 455));
        setSize(new java.awt.Dimension(800, 455));

        contentPanel.setBackground(new java.awt.Color(51, 51, 255));
        contentPanel.setLayout(new javax.swing.BoxLayout(contentPanel, javax.swing.BoxLayout.LINE_AXIS));

        optionsPanel.setBackground(new java.awt.Color(115, 129, 212));

        titleLabel.setFont(new java.awt.Font("Lucida Grande", 0, 26)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Numberphy");
        titleLabel.setMaximumSize(new java.awt.Dimension(159, 60));
        titleLabel.setMinimumSize(new java.awt.Dimension(159, 60));
        titleLabel.setPreferredSize(new java.awt.Dimension(159, 60));
        optionsPanel.add(titleLabel);
        titleLabel.setVerticalTextPosition(SwingConstants.TOP);

        halleyButton.setText("Método de Halley");
        halleyButton.setMaximumSize(new java.awt.Dimension(160, 40));
        halleyButton.setMinimumSize(new java.awt.Dimension(160, 40));
        halleyButton.setPreferredSize(new java.awt.Dimension(160, 40));
        halleyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                halleyButtonActionPerformed(evt);
            }
        });
        optionsPanel.add(halleyButton);

        riddersButton.setText("Método de Ridders");
        riddersButton.setMaximumSize(new java.awt.Dimension(160, 40));
        riddersButton.setMinimumSize(new java.awt.Dimension(160, 40));
        riddersButton.setPreferredSize(new java.awt.Dimension(160, 40));
        riddersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riddersButtonActionPerformed(evt);
            }
        });
        optionsPanel.add(riddersButton);

        hermiteButton.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        hermiteButton.setText("Interpolacao de Hermite");
        hermiteButton.setEnabled(false);
        hermiteButton.setMaximumSize(new java.awt.Dimension(160, 40));
        hermiteButton.setMinimumSize(new java.awt.Dimension(160, 40));
        hermiteButton.setPreferredSize(new java.awt.Dimension(160, 40));
        hermiteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hermiteButtonActionPerformed(evt);
            }
        });
        optionsPanel.add(hermiteButton);

        splinesButton.setText("Splines");
        splinesButton.setEnabled(false);
        splinesButton.setMaximumSize(new java.awt.Dimension(160, 40));
        splinesButton.setMinimumSize(new java.awt.Dimension(160, 40));
        splinesButton.setPreferredSize(new java.awt.Dimension(160, 40));
        splinesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splinesButtonActionPerformed(evt);
            }
        });
        optionsPanel.add(splinesButton);

        gaussButton.setText("Quadratura de Gauss");
        gaussButton.setEnabled(false);
        gaussButton.setMaximumSize(new java.awt.Dimension(160, 40));
        gaussButton.setMinimumSize(new java.awt.Dimension(160, 40));
        gaussButton.setPreferredSize(new java.awt.Dimension(160, 40));
        gaussButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaussButtonActionPerformed(evt);
            }
        });
        optionsPanel.add(gaussButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        contentPanel.add(new Description(Methods.WELCOME));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void halleyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_halleyButtonActionPerformed
        update(Methods.HALLEY);
    }//GEN-LAST:event_halleyButtonActionPerformed

    private void riddersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riddersButtonActionPerformed
        update(Methods.RIDDERS);
    }//GEN-LAST:event_riddersButtonActionPerformed

    private void hermiteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hermiteButtonActionPerformed
        update(Methods.HERMITE);
    }//GEN-LAST:event_hermiteButtonActionPerformed

    private void splinesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splinesButtonActionPerformed
        update(Methods.SPLINES);
    }//GEN-LAST:event_splinesButtonActionPerformed

    private void gaussButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaussButtonActionPerformed
        update(Methods.GAUSS);
    }//GEN-LAST:event_gaussButtonActionPerformed

    private void update(Methods method) {
        Color d = new Color(238, 238, 238);
        gaussButton.setBackground(d);
        halleyButton.setBackground(d);
        hermiteButton.setBackground(d);
        riddersButton.setBackground(d);
        splinesButton.setBackground(d);
        switch (method) {
            case HALLEY:
                halleyButton.setBackground(Color.YELLOW);
                break;
            case RIDDERS:
                riddersButton.setBackground(Color.YELLOW);
                break;
            case HERMITE:
                hermiteButton.setBackground(Color.YELLOW);
                break;
            case SPLINES:
                splinesButton.setBackground(Color.YELLOW);
                break;
            case GAUSS:
                gaussButton.setBackground(Color.YELLOW);
                break;
        }
        contentPanel.removeAll();
        contentPanel.add(new Description(method));
        contentPanel.revalidate();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Intro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Intro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Intro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Intro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Intro().setVisible(true);
        });
    }
    
    public void setVisibleContent(java.awt.Component c)
    {
        contentPanel.removeAll();
        contentPanel.add(c);
        contentPanel.revalidate();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton gaussButton;
    private javax.swing.JButton halleyButton;
    private javax.swing.JButton hermiteButton;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JButton riddersButton;
    private javax.swing.JButton splinesButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
