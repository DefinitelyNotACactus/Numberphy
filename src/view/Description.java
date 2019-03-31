/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.MethodsEnum;
import data.Constants;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 *
 * @author david
 */
public class Description extends JScrollPane {
    
    private MethodsEnum method;
    private JButton btApplication;
    private JLabel descriptionLabel;
    private Container container;
    private static final Dimension SIZE = new Dimension(1280, 680);
    
    private Parser parser;
    private HtmlRenderer renderer;
    private InputStreamReader reader;
    private Node document;
    
    public Description(MethodsEnum method) {
        this.method = method;
        parser = Parser.builder().build();
        renderer = HtmlRenderer.builder().build();
        
        initComponents();
    }
    
    private void initComponents() {
        btApplication = new JButton();
        if(method == MethodsEnum.WELCOME) {
            btApplication.setVisible(false);
        }
        btApplication.addActionListener(this::btApplicationActionPerformed);
        btApplication.setText("Aplicação");
        btApplication.setBackground(Constants.WHITE);
        btApplication.setForeground(Constants.BLUE);
        
        descriptionLabel = new JLabel();
        descriptionLabel.setFont(Constants.HELVETICA);
        descriptionLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                try {
                    descriptionLabel.setText(getMethodDescription(method));
                } catch(IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de descrição", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        try {
            descriptionLabel.setText(getMethodDescription(method));
        } catch(IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de descrição", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        descriptionLabel.setOpaque(true);
        descriptionLabel.setBackground(Constants.WHITE);
        
        container = new Container();
        container.add(descriptionLabel);
        container.add(btApplication);
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setBackground(Constants.WHITE);
        container.revalidate();
        
        getViewport().setView(container);
        getViewport().setBackground(Constants.WHITE);
        setMinimumSize(SIZE);
        setPreferredSize(SIZE);
        setBackground(Constants.WHITE);
        setOpaque(true);
    }
    
    private void reloadContainer() {
        if(method == MethodsEnum.HALLEY || method == MethodsEnum.RIDDERS || method == MethodsEnum.HERMITE || method == MethodsEnum.SPLINES) {
            btApplication.setVisible(true);
        } else {
            btApplication.setVisible(false);
        }
        try {
            descriptionLabel.setText(getMethodDescription(method));
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo de descrição", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        container.revalidate();
    }
    
    public void setMethod(MethodsEnum method) {
        this.method = method;
        reloadContainer();
    }
    
    public MethodsEnum getMethod() {
        return method;
    }
    
    private void btApplicationActionPerformed(ActionEvent evt) {
        getParent().add(new Application(method));
        getParent().revalidate();
        getParent().remove(this);
    }
    
    public String getMethodDescription(MethodsEnum method) throws FileNotFoundException, IOException {
        switch(method){
            case HALLEY:
                
                String newtonGraphMathPath = getResource("/resources/newton/newton_graph_math.png");
                
                String halley1Path = getResource("/resources/halley/halley1_.png");
                String halley2Path = getResource("/resources/halley/halley2_.png");
                String halley4Path = getResource("/resources/halley/halley4__.png");
                String halley5Path = getResource("/resources/halley/halley5_.png");
                String halley6Path = getResource("/resources/halley/halley6_.png");
                String halley7Path = getResource("/resources/halley/halley7_.png");
                String halley8Path = getResource("/resources/halley/halley8_.png");
                String halley9Path = getResource("/resources/halley/halley9_.png");
                String halley10Path = getResource("/resources/halley/halley10_.png");
                
                return ("<html><div WIDTH="+getWidth()+"><h4 id=\"introduo-1\">Introdução</h4>\n" +
                "\n" +
                "<p>O método de Halley é usado para busca de raizes de funções reais de uma variável que possuem primeira e segunda derivada contínuas, realizando iterativamente uma sequência de aproximações à raiz, tendo uma taxa de convergência cúbica. Criado pelo fisíco Edmond Halley, o algoritmo consiste em aplicar o método de Newton-Raphson duas vezes. Esse segundo método, desenvolvido por Isaac Newton e Joseph Raphson, estima as raízes de uma função escolhendo-se uma aproximação inicial. Cria-se então um método iterativo, repetindo o processo de cálculo da reta tangente a partir da derivada da função no ponto e a intersecção dela com o eixo das abcissas. Abaixo é possível ver a representação gráfica e matemática do Método de Newton-Raphson.</p>\n" +
                "<p>\n</p>" +
                "<table><tr><p>  <td><img src=" + newtonGraphMathPath +" alt=\"NewtonGraphMath\"></td></p></tr></table>\n" +
                "<p><p>\n</p></p>" +
                "<h4 id=\"desenvolvimento\">Desenvolvimento</h4>\n" +
                "\n" +
                "<table><tr>  <td>Considere a função de iteração:</td>  <img src=" + halley1Path + " alt=\"Halley1\" ></td>  <td>, onde &nbsp&nbsp&nbsp&nbsp&nbsp</td>  <td><img src=" + halley2Path + " alt=\"Halley2\"></td>   <td>&nbsp&nbsp&nbsp&nbsp&nbsp e</td>    <td> Q é um polinômio.</td></tr></table>" +
                "<p>O Método de Halley diz que se Q for uma função linear, então é possível obter uma função de terceira ordem, obedecendo a forma do método de Newton-Raphson. Supondo uma função g tal que:</p>\n"+
                "<table><p><tr><td><img src=" + halley4Path + " alt=\"Halley4\" ></td>   <td> A função f é aquela cuja raiz tal que f(x)=0 queremos encontrar. Agora derivamos a função g, gerando a expressão:</td></p></tr></table>\n" +
                "<table><p><tr><td><img src=" + halley5Path + " alt=\"Halley5\" > </td>   <td> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  Simplificando: </td>  <td><img src=" + halley6Path + " alt=\"Halley6\" ></td> </tr></p></table>\n" +
                "<p>Agora usaremos a função de iteração do método de Newton-Raphson. Contudo, ao invés de utilizarmos a função f e a sua derivada na busca da raiz, usaremos as expressões derivadas de g, ou seja:</p>" +
                "<table><p><tr><td><img src=" + halley7Path + " alt=\"Halley7\" ></td>   <td> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  onde  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp </td>   <td><img src=" + halley9Path + " alt=\"Halley9\"></td>  </tr></p></table>" +
                "<p>Aplicando a equação encontrada anteriormente com a função de iteração obtemos a expressão usada pelo método de Halley:</p>" +
                "<table><p><tr> <td><img src=" + halley10Path + " alt=\"Halley10\" ></td> </tr></p></table>" +
                "</html>");
            case RIDDERS:
                
                String fpGraphPath = getResource("/resources/fp/fp_graph.png");
                
                String ridder1Path = getResource("/resources/fp/ridder1.png");
                String ridder2Path = getResource("/resources/fp/ridder2.png");
                String ridder3Path = getResource("/resources/fp/ridder3.png");
                String ridder4Path = getResource("/resources/fp/ridder4.png");
                String ridder5Path = getResource("/resources/fp/ridder5.png");
                String ridder6Path = getResource("/resources/fp/ridder6.png");
                String ridder7Path = getResource("/resources/fp/ridder7.png");
                String ridder8Path = getResource("/resources/fp/ridder8.png");
                String ridder9Path = getResource("/resources/fp/ridder9.png");
                String ridder10Path = getResource("/resources/fp/ridder10.png");
                String ridder11Path = getResource("/resources/fp/ridder11.png");
                String ridder12Path = getResource("/resources/fp/ridder12.png");
                String ridder13Path = getResource("/resources/fp/ridder13.png");
                String ridder14Path = getResource("/resources/fp/ridder14.png");
                String ridder15Path = getResource("/resources/fp/ridder15.png");
                String ridder16Path = getResource("/resources/fp/ridder16.png");
                String ridder17Path = getResource("/resources/fp/ridder17.png");
                
                return ("<html><div WIDTH="+getWidth()+"><h4 id=\"introduo-2\">Introdução</h4>\n" +
                "\n" +
                "<p>O método de Ridder é um algoritmo de localização de raízes baseado no Método da Falsa Posição e no uso de uma função exponencial para a aproximação da raíz da função contínua f(x). O Método da Falsa Posição é um método numérico usado para resolver equações lineares definidas em um intervalo [a, b], partindo do pressuposto de que haja uma solução em um subintervalo contido em [a, b]. E assim, diminuindo esse subintervalo em partes cada vez menores, a solução estará onde a função tem sinais opostos, segundo o Teorema do Valor Intermediário. Abaixo é possível ver uma representação do uso do Método da Falsa Posição.</p>\n" +
                "<table><p><tr><td><img src=" + fpGraphPath + " alt=\"FPGraph\" ></td>   </tr></p></table>" +
                "\n" +
                "<h4 id=\"desenvolvimento-1\">Desenvolvimento</h4>\n" +
                "\n" +
                "<table><p><tr><td>Como queremos encontrar f(x)=0, vamos tomar</td> <td><img src=" + ridder9Path + " alt=\"FPGraph\" ></td> <td>. Sejam três valores de x delimitando de alguma forma um intervalo que contenha a raiz tal que</td> <td><img src=" + ridder10Path + " alt=\"FPGraph\" ></td> <td>cuja amplitude seja</td> <td><img src=" + ridder11Path + " alt=\"FPGraph\" ></td></tr></p></table>" +
                "<table><p><tr><td>Podemos utilizar o Método da Falsa posição para obter uma aproximação da raiz a partir desses pontos neste intervalo:</td> <td><img src=" + ridder12Path + " alt=\"FPGraph\" ></td> </tr></p></table>\n" +
                "<table><p><tr><td>A proposição feita por Ridder é de realizar uma segunda aproximação a partir da fórmula:</td> <td><img src=" + ridder6Path + " alt=\"FPGraph\" ></td> <td>, onde: </td></tr></p></table>" +
                "<p><img src=" + ridder13Path + " alt=\"FPGraph\" ></p>" +
                "<p><img src=" + ridder14Path + " alt=\"FPGraph\" ></p>" +
                "<p><img src=" + ridder15Path + " alt=\"FPGraph\" ></p>" +
                "<p><img src=" + ridder16Path + " alt=\"FPGraph\" ></p>" +
                "<p><img src=" + ridder17Path + " alt=\"FPGraph\" ></p>" +
                "<p>\n</p>" +
                "<table><p><tr><td>Da série logarítmica temos:</td> <td><img src=" + ridder1Path + " alt=\"FPGraph\" ></td></tr></p></table>" +
                "<p>Uma aproximação satisfatória para o caso pode ser obtida usando-se até o terceiro termo. Desta forma, para x = β - 1, temos:</p>" +
                "<table><p><tr><td><img src=" + ridder2Path + " alt=\"FPGraph\" ></td> <td>Portanto,</td> <td><img src=" + ridder3Path + " alt=\"FPGraph\" ></td></tr></p></table>" +
                "<table><p><tr><td>Para simplificarmos a notação, adotaremos</td> <td><img src=" + ridder7Path + " alt=\"FPGraph\" ></td>  <td>e</td> <td><img src=" + ridder8Path + " alt=\"FPGraph\" ></td> <td>. Desta forma, obtemos facilmente as expressões necessárias para aplicarmos ao método:</td> </tr></p></table>" +
                "<p><img src=" + ridder4Path + " alt=\"FPGraph\" ></p>" + 
                "<p><img src=" + ridder5Path + " alt=\"FPGraph\" ></p>" +
                "</html>");
            case WELCOME:
            default:
                reader = new InputStreamReader(getResourceFile("resources/assets/Welcome.md"));
                document = parser.parseReader(reader);
                return "<html><div WIDTH="+getWidth()+">" + renderer.render(document) + "<br></html>";
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
    
    private static InputStream getResourceFile(String path) throws FileNotFoundException {
	ClassLoader classLoader = Description.class.getClassLoader();
	return classLoader.getResourceAsStream(path);
    }
}
