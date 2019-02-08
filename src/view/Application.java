package view;

import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.draw.Axes;
import edu.hws.jcm.draw.Crosshair;
import edu.hws.jcm.draw.DisplayCanvas;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.draw.LimitControlPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class Application extends JPanel {
    
    private Parser parser;
    private Variable variable;
    private DisplayCanvas canvas;
    private LimitControlPanel limits;
    private boolean limitsEnabled;
    private ExpressionInput input;
    
    private Function function; //input function
    private Function firstDer; //first derivative
    private Function secondDer; //seconde derivative
    private Methods method;
    //private DrawString str;
    
    private Graph1D graph; //input graph
    private Graph1D firstDerGraph; //first derivative graph
    private Graph1D secondDerGraph; //second derivative graph
    
    private Crosshair cross; //a point on the graph
    private VariableInput xInput; //point x value
    
    private JCMPanel main;
    private Controller controller;
    
    public Application(Methods method) {
        this.method = method;
        limitsEnabled = false;
        initComponents();
    }
    
    public Application(Methods method, boolean limits) {
        this.method = method;
        limitsEnabled = limits;
        initComponents();
    }
    
    private void initComponents() {
        parser = new Parser();
        variable = new Variable("x");
        parser.add(variable);
        
        canvas = new DisplayCanvas();
        canvas.setUseOffscreenCanvas(false);
        canvas.setHandleMouseZooms(true);
        
        if(limitsEnabled){
            limits = new LimitControlPanel();
            limits.addCoords(canvas);
        }
        
        input = new ExpressionInput(method, "x^2 - x^3 + x", parser);
        function = input.getFunction(variable);
        firstDer = function.derivative(1);
        secondDer = firstDer.derivative(1);
        
        graph = new Graph1D(function);
        graph.setColor(Color.black);
        firstDerGraph = new Graph1D(firstDer);
        firstDerGraph.setColor(Color.red);
        secondDerGraph = new Graph1D(secondDer);
        secondDerGraph.setColor(Color.green);
        
        xInput = new VariableInput(variable.getName(), "1");
        cross = new Crosshair(xInput, firstDer);
        
        //str = new DrawString("f(x) = " + input.getText());
        //str.setColor(Color.black);
        //str.setBackgroundColor(Color.white);
        //str.setFrameWidth(1);
        
        main = new JCMPanel();
        main.add(canvas, BorderLayout.CENTER);
        main.add(input, BorderLayout.SOUTH);
        if(limitsEnabled) {
            main.add(limits, BorderLayout.EAST);
        }
        main.setInsetGap(5);
        
        setLayout(new BorderLayout());
        add(main, BorderLayout.CENTER);
        setBackground(Color.lightGray);
        
        canvas.add(new Axes());
        canvas.add(graph);
        canvas.add(firstDerGraph);
        canvas.add(secondDerGraph);
        canvas.add(cross);
        //canvas.add(str);
        
        controller = main.getController();
        controller.setErrorReporter(canvas);
        
        if(limitsEnabled) {
            limits.setErrorReporter(canvas);
        }
        
        main.gatherInputs();
    }
}
