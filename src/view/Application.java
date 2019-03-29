package view;

import data.Constants;
import data.MethodsEnum;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.draw.Axes;
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
    private MethodsEnum method; //method used
    
    private Graph1D graph; //input graph
    
    private JCMPanel main;
    private Controller controller;
    
    public Application(MethodsEnum method) {
        this.method = method;
        limitsEnabled = false;
        initComponents();
    }
    
    public Application(MethodsEnum method, boolean limits) {
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
        
        input = new ExpressionInput(method, "exp(cos(x) - sin(x)) - x", parser, this);
        function = getExpressionInput().getFunction(getVariable());
        
        graph = new Graph1D(function);
        graph.setColor(Color.black);

        main = new JCMPanel();
        main.add(getCanvas(), BorderLayout.CENTER);
        main.add(getExpressionInput(), BorderLayout.SOUTH);
        if(limitsEnabled) {
            main.add(limits, BorderLayout.EAST);
        }
        main.setInsetGap(5);
        
        setLayout(new BorderLayout());
        add(main, BorderLayout.CENTER);
        setBackground(Constants.GRAY);
        
        getCanvas().add(new Axes());
        getCanvas().add(graph);
        
        controller = main.getController();
        controller.setErrorReporter(getCanvas());
        
        if(limitsEnabled) {
            limits.setErrorReporter(getCanvas());
        }
        
        main.gatherInputs();
    }
    
    /**
     * @return the function 
     */
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
    public void setInputEvent(InputEvent event) {
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
    
    /**
     * 
     * @return the jcm panel
     */
    public JCMPanel getJCMPanel() {
        return main;
    }
}
