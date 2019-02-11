package view;

import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Value;
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
    private InputEvent event;

    public InputEventManager(ExpressionInput input) {
        this.drawables = new ArrayList<>();
        this.input = input;
        this.event = new InputEventImpl();
        
        getCoordRect();
    }

    public void invokeEvent() {
        drawables.forEach((d) -> {
            getCoordRect().remove(d);
        });
        drawables.clear();
        if (event != null) {
            event.inputUpdate(input, this);
        }
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
     * Desenha um ponto no formate de cruz.
     * @param x Valor de X
     * @param f f(x)
     */
    public void drawCrossHair(Value x, Function f) {
        Crosshair ch = new Crosshair(x, f);
        draw(ch, true);//False makes it permanent. WTF???
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
}
