/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nullPointerException
 */
public class Description extends JPanel {

    /**
     * Creates new form Description
     */
    private Methods method;
    
    public Description(Methods method) {
        this.method = method;
        initComponents();
        loadContent();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        applicationButton = new javax.swing.JButton();

        setSize(new java.awt.Dimension(613, 451));

        titleLabel.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        titleLabel.setText("Titulo");

        applicationButton.setText("Aplicacao");
        if(method == Methods.WELCOME) {
            applicationButton.setVisible(false);
        }
        applicationButton.addActionListener((ActionEvent evt) -> {
            applicationButtonActionPerformed(evt);
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(applicationButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(applicationButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void applicationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applicationButtonActionPerformed
        getParent().add(new Application(method));
        getParent().revalidate();
        getParent().remove(this);
    }//GEN-LAST:event_applicationButtonActionPerformed

    private void loadContent() {
        Container cont = new Container();
        JLabel label = new JLabel();
        label.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                label.setText(getMethodDescription(method));
            }
        });
        label.setText(getMethodDescription(method));
        switch(method) {
            case HALLEY:
                titleLabel.setText("Metodo de Halley"); 
                break;
            case RIDDERS:
                titleLabel.setText("Metodo de Ridders");
                break;
            default:
                titleLabel.setText("Bem-vind@ ao Numberphy");
                break;
        }
        cont.add(label);
        cont.setLayout(new BoxLayout(cont, BoxLayout.X_AXIS));
        scrollPane.getViewport().setView(cont);
    }

    public String getMethodDescription(Methods method) {
        switch(method){
            case HALLEY:
                return ("<html><div WIDTH="+getWidth()+"><h4 id=\"introduo-1\">Introdução</h4>\n" +
                "\n" +
                "<p>O método de Halley é usado para busca de raizes de funções reais de uma variável que possuem primeira e segunda derivada contínuas, realizando iterativamente uma sequência de aproximações à raiz, tendo uma taxa de convergência cúbica. Inventado pelo fisíco Edmond Halley, o algoritmo consiste em aplicar o método de Newton-Raphson duas vezes. Esse segundo método, desenvolvido por Isaac Newton e Joseph Raphson, estima as raízes de uma função escolhendo-se uma aproximação inicial. Cria-se então um método iterativo, repetindo o processo de cálculo a reta tangente a partir da derivada da função no ponto e a intersecção dela com o eixo das abcissas. Abaixo é possível ver a representação gráfica e matemática do Método de Newton-Raphson.</p>\n" +
                "<p>\n</p>" +
                "<tr><p><td><img src=\"https://upload.wikimedia.org/wikipedia/commons/b/bb/Newton%E2%80%93Raphson_method.png\" alt=\"NewtonGraph\" height=\"250\" width=\"300\"></td>   <td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>   <td><img src=\"https://calcworkshop.com/wp-content/uploads/newtons-method-formula.png\" alt=\"NewtonMath\" height=\"140\" width=\"240\"></td></p></tr>\n" +
                "\n" +
                "<h4 id=\"desenvolvimento\">Desenvolvimento</h4>\n" +
                "\n" +
                "<p>(Inserir desenvolvimento aqui)</p>\n" +
                "\n" +
                "<h4 id=\"algoritmo\">Algoritmo</h4>\n" +
                "\n" +
                "<p>(Inserir pseudocodigo aqui)</p></html>");
            case RIDDERS:
                return ("<html><div WIDTH="+getWidth()+"><h4 id=\"introduo-2\">Introdução</h4>\n" +
                "\n" +
                "<p>(Inserir introdução aqui)</p>\n" +
                "\n" +
                "<h4 id=\"desenvolvimento-1\">Desenvolvimento</h4>\n" +
                "\n" +
                "<p>(Inserir desenvolvimento aqui)</p>\n" +
                "\n" +
                "<h4 id=\"algoritmo-1\">Algoritmo</h4>\n" +
                "\n" +
                "<p>(Inserir pseudocodigo aqui)</p></html>");
            case WELCOME:
            default:
                return ("<html><div WIDTH="+getWidth()+"><h1 id=\"numberphy\">Numberphy</h1>\n" +
                "\n" +
                "<p>Projecto de Cálculo Numérico, Numberphy, essa é uma distribuição do grupo de desenvolvimento Pales.</p>\n" +
                "\n" +
                "<h3 id=\"introduo\">Introdução</h3>\n" +
                "\n" +
                "<p>O projecto Numberphy consiste em um programa desenvolvido usando Java/Swing junto à biblioteca JCM (Java Components for Math Project), usando os conhecimentos adquiridos na aula de Calculo Numérico para apresentar uma interface gráfica para explicação e aplicaçao de alguns métodos.</p>\n" +
                "\n" +
                "<h4 id=\"requerimentos\">Requerimentos</h4>\n" +
                "\n" +
                "<blockquote>\n" +
                "  <ul>\n" +
                "  <li>JDK 1.8 ou superior</li>\n" +
                "  \n" +
                "  <li>JCM (Incluido neste repositório)</li>\n" +
                "  \n" +
                "  <li>Algum conhecimento sobre cálculo </li>\n" +
                "  </ul>\n" +
                "</blockquote></html>");
        }
    }
    
    
    
    /**
     * Percorre list tentando retornar o recurso especificado.
     * Para cada item I de list, retorna se o recurso de nome I existe; senão, continua para o próximo item.
     * Caso nenhum item seja encontrado em list, retorna null.
     * @param list Lista de recursos
     * @return URI do item ou null
     */
    private static String getResource(String... list) {
        return getResourceOrDefault(null, list);
    }
    
    /**
     * Percorre list tentando retornar o recurso especificado, retorna defaultItem em caso de falha.
     * Para cada item I de list, retorna se o recurso de nome I existe; senão, continua para o próximo item.
     * Caso nenhum item seja encontrado em list, retorna defaultItem.
     * @param defaultItem Item padrão
     * @param list Lista de recursos
     * @return URI do item ou defaultItem
     */
    private static String getResourceOrDefault(String defaultItem, String... list) {
        for (String s : list) {
            try {
                return Description.class.getResource(s).toString();
            }
            catch (Exception e) {
                // who cares?
            }
        }
        return defaultItem;
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applicationButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
