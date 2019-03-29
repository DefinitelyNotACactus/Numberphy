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
import java.nio.charset.StandardCharsets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import jdk.internal.module.Resources;
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
        if(method == MethodsEnum.HALLEY || method == MethodsEnum.RIDDERS) {
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
        InputStreamReader reader;
        switch(method){
            case HALLEY:
                
                String newtonGraphPath = getResource("/resources/newton/newton_graph.png");
                String newtonMathPath = getResource("/resources/newton/newton_math.png");
                
                String halley1Path = getResource("/resources/halley/halley1.png");
                String halley2Path = getResource("/resources/halley/halley2.png");
                String halley3Path = getResource("/resources/halley/halley3.png");
                String halley4Path = getResource("/resources/halley/halley4.png");
                String halley5Path = getResource("/resources/halley/halley5.png");
                String halley6Path = getResource("/resources/halley/halley6.png");
                String halley7Path = getResource("/resources/halley/halley7.png");
                String halley8Path = getResource("/resources/halley/halley8.png");
                String halley9Path = getResource("/resources/halley/halley9.png");
                String halley10Path = getResource("/resources/halley/halley10.png");
                
                
                return ("<html><div WIDTH="+getWidth()+"><h4 id=\"introduo-1\">Introdução</h4>\n" +
                "\n" +
                "<p>O método de Halley é usado para busca de raizes de funções reais de uma variável que possuem primeira e segunda derivada contínuas, realizando iterativamente uma sequência de aproximações à raiz, tendo uma taxa de convergência cúbica. Criado pelo fisíco Edmond Halley, o algoritmo consiste em aplicar o método de Newton-Raphson duas vezes. Esse segundo método, desenvolvido por Isaac Newton e Joseph Raphson, estima as raízes de uma função escolhendo-se uma aproximação inicial. Cria-se então um método iterativo, repetindo o processo de cálculo da reta tangente a partir da derivada da função no ponto e a intersecção dela com o eixo das abcissas. Abaixo é possível ver a representação gráfica e matemática do Método de Newton-Raphson.</p>\n" +
                "<p>\n</p>" +
                "<table><tr><p>  <td><img src=" + newtonGraphPath +" alt=\"NewtonGraph\" height=\"250\" width=\"300\"></td>   <td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>   <td><img src=" + newtonMathPath + " alt=\"NewtonMath\" height=\"140\" width=\"240\"></td></p></tr></table>\n" +
                "<p><p>\n</p></p>" +
                "<h4 id=\"desenvolvimento\">Desenvolvimento</h4>\n" +
                "\n" +
                "<table><tr>  <td>Considere a função de iteração:</td>  <img src=" + halley1Path + " alt=\"Halley1\" height=\"85\" width=\"210\"></td>  <td>, onde &nbsp&nbsp&nbsp&nbsp&nbsp</td>  <td><img src=" + halley2Path + " alt=\"Halley2\" height=\"90\" width=\"150\"></td>   <td>&nbsp&nbsp&nbsp&nbsp&nbsp e</td>    <td><img src=" + halley3Path + " alt=\"Halley3\" height=\"30\" width=\"25\"></td> <td>é um polinômio.</td></tr></table>" +
                "<p>O Método de Halley diz que se Q for uma função linear, então é possível obter uma função de terceira ordem, obedecendo a forma do método de Newton-Raphson. Supondo uma função g tal que:</p>\n"+
                "<table><p><tr><td><img src=" + halley4Path + " alt=\"Halley4\" height=\"125\" width=\"210\"></td>   <td> A função f é aquela cuja raiz tal que f(x)=0 queremos encontrar. Agora derivamos a função g, gerando a expressão:</td></p></tr></table>\n" +
                "<table><p><tr><td><img src=" + halley5Path + " alt=\"Halley5\" height=\"130\" width=\"430\"> </td>   <td> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  Simplificando: </td>  <td><img src=" + halley6Path + " alt=\"Halley6\" height=\"140\" width=\"430\"></td> </tr></p></table>\n" +
                "<p>Agora usaremos a função de iteração do método de Newton-Raphson. Contudo, ao invés de utilizarmos a função f e a sua derivada na busca da raiz, usaremos as expressões derivadas de g, ou seja:</p>" +
                "<table><p><tr><td><img src=" + halley7Path + " alt=\"Halley7\" height=\"150\" width=\"270\"></td>   <td> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  onde  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp </td>   <td><img src=" + halley9Path + " alt=\"Halley9\" height=\"150\" width=\"430\"></td>  </tr></p></table>" +
                "<p>Aplicando a equação encontrada anteriormente com a função de iteração obtemos a expressão usada pelo método de Halley:</p>" +
                "<table><p><tr> <td><img src=" + halley10Path + " alt=\"Halley10\" height=\"150\" width=\"440\"></td> </tr></p></table>" +
                "</html>");
            case RIDDERS:
                
                String fpGraphPath = getResource("/resources/fp/fp_graph.png");
                
                return ("<html><div WIDTH="+getWidth()+"><h4 id=\"introduo-2\">Introdução</h4>\n" +
                "\n" +
                "<p>O método de Ridder é um algoritmo de localização de raízes baseado no Método da Falsa Posição e no uso de uma função exponencial para a aproximação da raíz da função contínua f(x). O Método da Falsa Posição é um método numérico usado para resolver equações lineares definidas em um intervalo [a, b], partindo do pressuposto de que haja uma solução em um subintervalo contido em [a, b]. E assim, diminuindo esse subintervalo em partes cada vez menores, a solução estará onde a função tem sinais opostos, segundo o Teorema do Valor Intermediário. Abaixo é possível ver uma representação do uso do Método da Falsa Posição.</p>\n" +
                "<table><p><tr><td><img src=" + fpGraphPath + " alt=\"FPGraph\" height=\"197\" width=\"220\"></td>   </tr></p></table>" +
                "\n" +
                "<h4 id=\"desenvolvimento-1\">Desenvolvimento</h4>\n" +
                "\n" +
                "<p>Como queremos encontrar f(x)=0, vamos tomar f(x)=A+Be<sup>Cx</sup>.  Sejam três valores de x que estejam delimitando de alguma forma um intervalo que contenha a raiz tal que {x<sub>left</sub> , x<sub>predictor</sub> , x<sub>right</sub>} cuja amplitude seja d<sub>0</sub> = | x<sub>left</sub> - x<sub>right</sub> |. Podemos utilizar o Método da Falsa posição para obter uma aproximação da raiz a partir desses pontos neste intervalo:  x<sub>predictor</sub> = FalsaPosicao( x<sub>left</sub> , x<sub>right</sub> ).</p>\n" +
                "<p>A proposição feita por Ridder é de realizar uma segunda aproximação a partir da fórmula: x<sub>corrector</sub> = x<sub>left</sub> - d<sub>0</sub>{ln(β) / ln(α)}, onde: </p>" +
                "<p>α = ( f<sub>left</sub> - f<sub>predictor</sub> ) / ( f<sub>predictor</sub> - f<sub>right</sub> ) </p>" +
                "<p>β = ( f<sub>left</sub> - f<sub>predictor</sub> ) / ( f<sub>predictor</sub> - αf<sub>right</sub> ) </p>" +
                "<p>f<sub>predictor</sub> = f( x<sub>predictor</sub> ) </p>" +
                "<p>f<sub>right</sub> = f( x<sub>right</sub> )</p>" +
                "<p>f<sub>left</sub> = f( x<sub>left</sub> )</p>" +
                "<p>\n</p>" +
                "<p>Da série logarítmica temos: &nbsp&nbsp  ln(x+1)  &nbsp&nbsp =  &nbsp&nbsp ∑<sup>∞</sup><sub>n=1</sub> ( (-1)<sup>n-1</sup>x<sup>n</sup> ) / n  &nbsp&nbsp =  &nbsp&nbsp x - (1/2)x<sup>2</sup> + (1/3)x<sup>3</sup></p>" +
                "<p>Uma aproximação satisfatória para o caso pode ser obtida usando-se até o terceiro termo. Desta forma, para x = β - 1, temos:</p>" +
                "<p>ln(1 + {β-1}) = β - 1 - (1/2)(β - 1)<sup>2</sup> + (1/3)(β - 1)<sup>3</sup> &nbsp&nbsp Portanto, ln(β) = β - 1 - (1/2)(β - 1)<sup>2</sup> + (1/3)(β - 1)<sup>3</sup> </p>" +
                "<p>Para simplificarmos a notação, adotaremos  ϕ<sub>β</sub> = β - 1 &nbsp&nbsp e ϕ<sub>α</sub> = α -1. Desta forma, obtemos facilmente as expressões necessárias para aplicarmos ao método: </p>" +
                "<p>ln(β) = ϕ<sub>β</sub> - (1/2)ϕ<sub>β</sub><sup>2</sup> + (1/3)ϕ<sub>β</sub><sup>3</sup>  </p>" + 
                "<p>ln(α) = ϕ<sub>α</sub> - (1/2)ϕ<sub>α</sub><sup>2</sup> + (1/3)ϕ<sub>α</sub><sup>3</sup>  </p>" +
                "</html>");
            case WELCOME:
            default:
                reader = new InputStreamReader(getResourceFile("resources/assets/README.md"));
                document = parser.parseReader(reader);
                return "<html><div WIDTH="+getWidth()+">" + renderer.render(document) + "</html>";
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
