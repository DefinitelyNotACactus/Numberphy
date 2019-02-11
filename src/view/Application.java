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
//    private Function firstDer; //first derivative
//    private Function secondDer; //seconde derivative
    private Methods method;
    //private DrawString str;
    
    private Graph1D graph; //input graph
//    private Graph1D firstDerGraph; //first derivative graph
//    private Graph1D secondDerGraph; //second derivative graph
    
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
        setVariable(new Variable("x"));
        parser.add(getVariable());
        
        canvas = new DisplayCanvas();
        getCanvas().setUseOffscreenCanvas(false);
        getCanvas().setHandleMouseZooms(true);
        
        if(limitsEnabled){
            limits = new LimitControlPanel();
            limits.addCoords(getCanvas());
        }
        
        input = new ExpressionInput(method, "x^2 - 2", parser, this);
        function = getExpressionInput().getFunction(getVariable());
//        firstDer = function.derivative(1);
//        secondDer = firstDer.derivative(1);


        
        graph = new Graph1D(function);
        graph.setColor(Color.black);
//        firstDerGraph = new Graph1D(firstDer);
//        firstDerGraph.setColor(Color.red);
//        secondDerGraph = new Graph1D(secondDer);
//        secondDerGraph.setColor(Color.green);
        
        xInput = new VariableInput(getVariable().getName(), "1");
        cross = new Crosshair(xInput, function);
        
        //str = new DrawString("f(x) = " + input.getText());
        //str.setColor(Color.black);
        //str.setBackgroundColor(Color.white);
        //str.setFrameWidth(1);
        
        main = new JCMPanel();
        main.add(getCanvas(), BorderLayout.CENTER);
        main.add(getExpressionInput(), BorderLayout.SOUTH);
        if(limitsEnabled) {
            main.add(limits, BorderLayout.EAST);
        }
        main.setInsetGap(5);
        
        setLayout(new BorderLayout());
        add(main, BorderLayout.CENTER);
        setBackground(Color.lightGray);
        
        getCanvas().add(new Axes());
        getCanvas().add(graph);
//        getCanvas().add(firstDerGraph);
//        getCanvas().add(secondDerGraph);
        getCanvas().add(cross);
        //canvas.add(str);
        
        controller = main.getController();
        controller.setErrorReporter(getCanvas());
        
        if(limitsEnabled) {
            limits.setErrorReporter(getCanvas());
        }
        
        main.gatherInputs();
    }
    
    public Function getFunction() {
        return function;
    }   
        
    
    
    /**
     * @return the canvas
     */
    public DisplayCanvas getCanvas() {
        return canvas;
    }

    /**
     * @return the input
     */
    public ExpressionInput getExpressionInput() {
        return input;
    }
    
    
    /**
     * Event called when the user updates the input.
     * @param event Event
     */
    public void setInputEvent(InputEvent event)
    {
        getExpressionInput().setInputEvent(event);
    }

    /**
     * @return the variable
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * @param variable the variable to set
     */
    public void setVariable(Variable variable) {
        this.variable = variable;
    }
}
