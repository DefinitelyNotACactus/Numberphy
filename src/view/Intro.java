/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.MethodsEnum;
import javax.swing.JFrame;
import data.Constants;
import java.awt.Color;
import java.awt.Component;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;

/**
 *
 * @author david
 */
public class Intro extends JFrame {

    /**
     * Creates new form Intro
     */
    private Description description;
    
    public Intro() {
        initComponents();
        
        description = new Description(MethodsEnum.WELCOME);
        upperPanel.add(description);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btExtra = new javax.swing.JButton();
        upperPanel = new javax.swing.JPanel();
        lowerPanel = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        btHalley = new javax.swing.JButton();
        btRidders = new javax.swing.JButton();
        btHermite = new javax.swing.JButton();
        btSplines = new javax.swing.JButton();
        btGauss = new javax.swing.JButton();

        btExtra.setBackground(Constants.WHITE);
        btExtra.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btExtra.setForeground(Constants.BLUE);
        btExtra.setText("Extras");
        btExtra.setMaximumSize(new java.awt.Dimension(160, 40));
        btExtra.setMinimumSize(new java.awt.Dimension(160, 40));
        btExtra.setPreferredSize(new java.awt.Dimension(160, 40));
        btExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExtraActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(64, 64, 1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));

        upperPanel.setBackground(Constants.WHITE);
        upperPanel.setLayout(new javax.swing.BoxLayout(upperPanel, javax.swing.BoxLayout.LINE_AXIS));

        lowerPanel.setBackground(Constants.WHITE);
        lowerPanel.setMinimumSize(new java.awt.Dimension(1280, 40));
        lowerPanel.setPreferredSize(new java.awt.Dimension(1280, 40));
        lowerPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        logoLabel.setFont(new java.awt.Font("Lucida Grande", 0, 26)); // NOI18N
        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/assets/logo.png"))); // NOI18N
        logoLabel.setMaximumSize(new java.awt.Dimension(159, 60));
        logoLabel.setMinimumSize(new java.awt.Dimension(159, 60));
        logoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoLabelMousePressed(evt);
            }
        });
        lowerPanel.add(logoLabel);

        titleLabel.setFont(new java.awt.Font("Lucida Grande", 0, 26)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Numberphy");
        titleLabel.setToolTipText(Constants.VERSION);
        titleLabel.setMaximumSize(new java.awt.Dimension(159, 60));
        titleLabel.setMinimumSize(new java.awt.Dimension(159, 60));
        titleLabel.setPreferredSize(new java.awt.Dimension(159, 40));
        lowerPanel.add(titleLabel);

        btHalley.setBackground(Constants.WHITE);
        btHalley.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btHalley.setForeground(Constants.BLUE);
        btHalley.setText("Halley");
        btHalley.setMaximumSize(new java.awt.Dimension(160, 40));
        btHalley.setMinimumSize(new java.awt.Dimension(160, 40));
        btHalley.setPreferredSize(new java.awt.Dimension(160, 40));
        btHalley.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHalleyActionPerformed(evt);
            }
        });
        lowerPanel.add(btHalley);

        btRidders.setBackground(Constants.WHITE);
        btRidders.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btRidders.setForeground(Constants.BLUE);
        btRidders.setText("Ridders");
        btRidders.setMaximumSize(new java.awt.Dimension(160, 40));
        btRidders.setMinimumSize(new java.awt.Dimension(160, 40));
        btRidders.setPreferredSize(new java.awt.Dimension(160, 40));
        btRidders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRiddersActionPerformed(evt);
            }
        });
        lowerPanel.add(btRidders);

        btHermite.setBackground(Constants.WHITE);
        btHermite.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btHermite.setForeground(Constants.BLUE);
        btHermite.setText("Hermite");
        btHermite.setMaximumSize(new java.awt.Dimension(160, 40));
        btHermite.setMinimumSize(new java.awt.Dimension(160, 40));
        btHermite.setPreferredSize(new java.awt.Dimension(160, 40));
        btHermite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHermiteActionPerformed(evt);
            }
        });
        lowerPanel.add(btHermite);

        btSplines.setBackground(Constants.WHITE);
        btSplines.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btSplines.setForeground(Constants.BLUE);
        btSplines.setText("Splines");
        btSplines.setMaximumSize(new java.awt.Dimension(160, 40));
        btSplines.setMinimumSize(new java.awt.Dimension(160, 40));
        btSplines.setPreferredSize(new java.awt.Dimension(160, 40));
        btSplines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSplinesActionPerformed(evt);
            }
        });
        lowerPanel.add(btSplines);

        btGauss.setBackground(Constants.WHITE);
        btGauss.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btGauss.setForeground(Constants.BLUE);
        btGauss.setText("Gauss");
        btGauss.setMaximumSize(new java.awt.Dimension(160, 40));
        btGauss.setMinimumSize(new java.awt.Dimension(160, 40));
        btGauss.setPreferredSize(new java.awt.Dimension(160, 40));
        btGauss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGaussActionPerformed(evt);
            }
        });
        lowerPanel.add(btGauss);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lowerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(upperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(upperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lowerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btRiddersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRiddersActionPerformed
        update(MethodsEnum.RIDDERS);
    }//GEN-LAST:event_btRiddersActionPerformed

    private void btHermiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHermiteActionPerformed
        update(MethodsEnum.HERMITE);
    }//GEN-LAST:event_btHermiteActionPerformed

    private void btSplinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSplinesActionPerformed
        update(MethodsEnum.SPLINES);
    }//GEN-LAST:event_btSplinesActionPerformed

    private void btGaussActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGaussActionPerformed
        //update(MethodsEnum.GAUSS);
    }//GEN-LAST:event_btGaussActionPerformed

    private void btExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExtraActionPerformed
        update(MethodsEnum.EXTRA);
    }//GEN-LAST:event_btExtraActionPerformed

    private void btHalleyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHalleyActionPerformed
        update(MethodsEnum.HALLEY);
    }//GEN-LAST:event_btHalleyActionPerformed

    private void logoLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoLabelMousePressed
        new Timer().scheduleAtFixedRate(new TimerTask() {
            Random rand = new Random();
            public void run() {
                for(Component c : lowerPanel.getComponents()) {
                    if(c instanceof JButton) {
                        c.setBackground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                        c.setForeground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                    }
                }
            }
        }, 0, 100);
    }//GEN-LAST:event_logoLabelMousePressed

    private void update(MethodsEnum method) {
        for(Component c : lowerPanel.getComponents()) {
            if(c instanceof JButton) {
                c.setBackground(Constants.WHITE);
                c.setForeground(Constants.BLUE);
            }
        }
        switch (method) {
            case HALLEY:
                btHalley.setBackground(Constants.BLUE);
                btHalley.setForeground(Constants.WHITE);
                break;
            case RIDDERS:
                btRidders.setBackground(Constants.BLUE);
                btRidders.setForeground(Constants.WHITE);
                break;
            case HERMITE:
                btHermite.setBackground(Constants.BLUE);
                btHermite.setForeground(Constants.WHITE);
                break;
            case SPLINES:
                btSplines.setBackground(Constants.BLUE);
                btSplines.setForeground(Constants.WHITE);
                break;
            case GAUSS:
                btGauss.setBackground(Constants.BLUE);
                btGauss.setForeground(Constants.WHITE);
                break;
            case EXTRA:
                btExtra.setBackground(Constants.BLUE);
                btExtra.setForeground(Constants.WHITE);
                break;
        }
        description.setMethod(method);
        setVisibleContent(description);
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
    
    public void setVisibleContent(Component c) {
        upperPanel.removeAll();
        upperPanel.add(c);
        upperPanel.revalidate();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExtra;
    private javax.swing.JButton btGauss;
    private javax.swing.JButton btHalley;
    private javax.swing.JButton btHermite;
    private javax.swing.JButton btRidders;
    private javax.swing.JButton btSplines;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel lowerPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel upperPanel;
    // End of variables declaration//GEN-END:variables
}
