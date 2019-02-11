/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.draw.Crosshair;
import edu.hws.jcm.draw.DisplayCanvas;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Graph1D;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author vk
 */
public class InputEventManager {

    private final ArrayList<Drawable> drawables;
    private final ExpressionInput input;
    private InputEvent event = null;

    InputEventManager(ExpressionInput input) {
        this.drawables = new ArrayList<>();
        this.input = input;
        getCoordRect();
    }

    void invokeEvent() {
        drawables.forEach((d) -> {
            getCoordRect().remove(d);
        });
        drawables.clear();
        if (event != null) {
            event.inputUpdate(input, this);
        }
        
        System.out.println(this.halley(1, 0.001, 10));
    }

    public void setInputEvent(InputEvent event) {
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
        if (permanent) {
            drawables.add(d);
        }
        getCanvas().add(d, 0);
        return d;
    }

    /**
     * Desenha um ponto no formato de cruz.
     *
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param permanent Se o desenho deve permanecer mesmo após o usuário
     * alterar a entrada.
     * @return O Desenho
     */
    public Crosshair drawCrossHair(double x, double y, boolean permanent) {
        Crosshair ch = new Crosshair(newVar(x), newVar(y));
        draw(ch, permanent);
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
        return drawCrossHair(x, y, false);
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
        return drawFunction(f, Color.RED, false);
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

    public double halley(double x0, double tolmin, int nitr) {

        double xn = x0;
        double tolmax = tolmin + 1;
        int itr = 0;

        Function f = input.getApplication().getFunction();
        Function dev1 = f.derivative(1);
        Function dev2 = dev1.derivative(1);
        
        while (tolmax > tolmin && itr < nitr) {
            x0 = xn;        
            double fx = new ValueMath(f, new ValueImpl(xn)).getVal();
            System.out.println(fx);
            double d1 = new ValueMath(dev1, new ValueImpl(xn)).getVal();
            System.out.println(d1);
            double d2 = new ValueMath(dev2, new ValueImpl(xn)).getVal();
            System.out.println(d2);
            
            xn = xn - (2 * fx * d1) / (2 * d1 * d1 - fx * d2);
            itr++;
            
            if (xn != 0) {
                tolmax = Math.abs((xn - x0) / xn);
            }
        }

        return xn;
    }
    
    class ValueImpl implements Value {
        double val;
        
        public ValueImpl(double val) {
            this.val = val;
        }
        
        public double getVal() {
            return val;
        }
    }
}
