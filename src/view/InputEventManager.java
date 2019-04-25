package view;

import data.Constants;
import data.Iteration;
import data.MethodsEnum;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.draw.Crosshair;
import edu.hws.jcm.draw.DisplayCanvas;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Graph1D;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author vk
 */
public class InputEventManager {

    private final ArrayList<Drawable> temporary_draws;
    private final ExpressionInput input;
    private MethodImplementation event;
    private Iteration[] points;

    public InputEventManager(ExpressionInput input) {
        this.temporary_draws = new ArrayList<>();
        this.input = input;
        this.event = input.getMethod().getInstance();
        this.points = null;
        
        getCoordRect();
    }

    public void invokeInputUpdate() {
        temporary_draws.forEach((d) -> {
            d.setVisible(false);
            getCoordRect().remove(d);
        });
        temporary_draws.clear();
        if (event != null) {
            drawString("f(x) = " + input.getFunctionString());
            points = event.inputUpdate(input, this);
        }
        getCoordRect().setLimits(Constants.LIMITS);
    }
    
    public void invokePointClickedEvent(double x, double y) {
        event.pointClickedEvent(input, this, x, y);
    }

    public void setInputEvent(MethodImplementation event) {
        this.event = event;
    }

    /**
     * Desenha no gráfico.
     *
     * @param d Desenhável
     * @param permanent Se o desenho deve permanecer mesmo após o usuário
     * alterar a entrada.
     * @return O Desenho
     */
    public Drawable draw(Drawable d, boolean permanent) {
        if (!permanent) {
            temporary_draws.add(d);
        }
        getCanvas().add(d, 0);
        return d;
    }
    
    public Drawable removeLastDrawable() {
        Drawable removed = temporary_draws.remove(temporary_draws.size()-1);
        getCoordRect().remove(removed);
        return removed;
    }
    
    /**
     * Desenha um ponto no formato de cruz.
     *
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param c Cor
     * @param permanent Se o desenho deve permanecer mesmo após o usuário
     * alterar a entrada.
     * @return O Desenho
     */
    public Crosshair drawCrossHair(double x, double y, Color c, boolean permanent) {
        Crosshair ch = new Crosshair(newVar(x), newVar(y));
        draw(ch, permanent);
        ch.setColor(c);
        return ch;
    }
    
    /**
     * Desenha um ponto no formato de cruz. Por padrão, o desenho será apagado
     * quando o usuário alterar a entrada.
     *
     * @param x Coordenada X
     * @param y Coordenada Y
     * @return O Desenho
     */
    public Crosshair drawCrossHair(double x, double y) {
        return drawCrossHair(x, y, Color.BLACK);
    }
    
    /**
     * Desenha um ponto no formato de cruz.
     * Por padrão, o desenho será apagado quando o usuário alterar a entrada.
     *
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param c Cor
     * @return O Desenho
     */
    public Crosshair drawCrossHair(double x, double y, Color c) {
        return drawCrossHair(x, y, c, false);
    }
    
    /**
     * Desenha um ponto no formato de cruz na função.
     * @param x Valor de X
     * @param f f(x)
     * @return O Desenho
     */
    public Crosshair drawCrossHair(Value x, Function f) {
        return drawCrossHair(x, f, Color.BLACK);
    }

    /**
     * Desenha um ponto no formato de cruz na função.
     * @param x Valor de X
     * @param f f(x)
     * @param c Cor
     * @return O Desenho
     */
    public Crosshair drawCrossHair(Value x, Function f, Color c) {
        Crosshair ch = new Crosshair(x, f);
        draw(ch, false);
        ch.setColor(c);
        return ch;
    }

    /**
     * Desenha uma função
     *
     * @param f Função
     * @param c Cor
     * @param permanent Se o desenho deve permanecer mesmo após o usuário
     * alterar a entrada.
     * @return O Desenho
     */
    public Graph1D drawFunction(Function f, Color c, boolean permanent) {
        Graph1D graph = new Graph1D(f);
        graph.setColor(c);
        draw(graph, permanent);
        return graph;
    }

    /**
     * Desenha uma função em vermelho. Por padrão, o desenho será apagado quando
     * o usuário alterar a entrada.
     *
     * @param f Função
     * @return O Desenho
     */
    public Graph1D drawFunction(Function f) {
        return drawFunction(f, Constants.RED, false);
    }

    /**
     * Desenha uma caixa contendo uma String
     * @param s A string da caixa
     * @return Uma caixa com a String
     */
    public DrawString drawString(String s) {
        DrawString ds = new DrawString(s);
        ds.setColor(Color.BLACK);
        ds.setFont(Constants.HELVETICA);
        ds.setBackgroundColor(Constants.WHITE);
        ds.setFrameColor(Color.BLACK);
        ds.setFrameWidth(1);
        
        draw(ds, false);
        return ds;
    }
    
    private static VariableInput newVar(double v) {
        return new VariableInput(null, String.valueOf(v));
    }

    private DisplayCanvas getCanvas() {
        return input.getApplication().getCanvas();
    }

    private CoordinateRect getCoordRect() {
        return getCanvas().getCoordinateRect(0);
    }
    
    public void setLimits(double[] limits) {
        getCoordRect().setLimits(limits);
    }
    
    public void drawTable(Iteration[] points) {
        boolean gauss = (input.getMethod() == MethodsEnum.GAUSS);
        Container cont = new Container();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
        JPanel line = new JPanel();
        Color color;
        Dimension dim = new Dimension(500, 20);
        line.setLayout(gauss? TableInput.GRID_2 : TableInput.GRID_3);
        if(!gauss) {
            line.add(ExpressionInput.createLabel("k", Constants.BLUE));
        }
        line.add(ExpressionInput.createLabel(gauss? "Raizes" : "X(k)", Constants.BLUE));
        line.add(ExpressionInput.createLabel(gauss? "Pesos" : "ER", Constants.BLUE));
        line.setMaximumSize(dim);
        cont.add(line);
        int i = 0;
        for(Iteration it : points) {
            if(i%2 == 0) {
                color = Constants.WHITE;
            } else {
                color = Constants.GRAY;
            }
            if(it != null) {
                line = new JPanel();
                line.setLayout(gauss? TableInput.GRID_2 : TableInput.GRID_3);
                if(!gauss) {
                    line.add(ExpressionInput.createLabel("" + i, color));
                }
                line.add(ExpressionInput.createLabel(String.format("%.10f", it.getX()), color));
                line.add(ExpressionInput.createLabel(String.format("%.10f", it.getRelativeError()), color));
                line.setMaximumSize(dim);
                line.setBackground(color);
                cont.add(line);
                i++;
            } else {
                break;
            }
        }
        i *= 20;
        if(i > 260) {
            i = 300;
        }
        cont.revalidate();
        //JOptionPane.showMessageDialog(null, cont, "Pontos Encontrados", JOptionPane.PLAIN_MESSAGE);
        JFrame frame = new JFrame();
        JScrollPane pane = new JScrollPane();
        pane.getViewport().setView(cont);
        pane.getViewport().setBackground(Constants.BLUE);
        frame.add(pane);
        frame.setBounds(900, 100, 500, i + 40);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public Iteration[] getPoints() {
        return points;
    }
}
