/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Variable;
import view.Application;
import view.Intro;
import view.Methods;

/**
 *
 * @author vk
 */
public class DerivativesExperiment {
    public static void main(String[] args) {
        setUp((setupArgs) -> {
            Application app = (Application)setupArgs[0];
            
            app.setInputEvent((input, event) -> {
                
                Variable xvar = input.getApplication().getVariable();
                Function f = input.getFunction(xvar);
                for (int i = 0; i < 3; i++)
                {
                    f = f.derivative(1);
                    event.drawFunction(f);
                }
                
            });
            
            app.getExpressionInput().performInputEvent();
        });
    }
    
    
    
    /**
     * Contém instruções de Setup da aplicação.
     * Para o entendimento deste experimento, não é necessário debruçar-se sobre este método.
     */
    private static void setUp(Invokable ready) {
        
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
            Intro intro = new Intro();
            intro.setVisible(true);
            Application app = new Application(Methods.WELCOME);
            intro.setVisibleContent(app);
            ready.run(app);
        });
    }
    
    private static interface Invokable {
        void run(Object... args);
    }
}
