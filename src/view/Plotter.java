package view;

import graph.Axis;
import graph.DataSet;
import graph.G2Dint;
import graph.ParseFunction;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Plotter extends JPanel {
    
    private G2Dint graph = new G2Dint();   // Graph class to do the plotting
    private Axis xaxis;
    private Axis yaxis;
    private DataSet data;
 
    private String function;
    private double min;
    private double max;
    private int points;
    
    private View view;
    
    public Plotter(View view) {
        this.view = view;
        
        function = view.functionTextField.getText();
        points = Integer.parseInt(view.nopTextField.getText());
        min = Double.parseDouble(view.initialXTextField.getText());
        max = Double.parseDouble(view.finalXTextField.getText());
        
        initComponents();
    }

    public void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(graph);
        
        xaxis = graph.createXAxis();
        xaxis.setTitleText("X");
        yaxis = graph.createYAxis();

        data = new DataSet();

        xaxis.attachDataSet(data);
        yaxis.attachDataSet(data);
        graph.attachDataSet(data);

        graph.setDataBackground(new Color(255,255,255));
        graph.setBackground(new Color(200,200,200));

        plot();

    }

    private void plot() {
        double x;
        int count = 0;
        boolean error = false;

        ParseFunction f = new ParseFunction(function);

        if(!f.parse()) {
           return;
        }

        double d[] = new double[2 * points];
        
        for(int i = 0; i < points; i++) {
            x = min + i * (max - min) / (points - 1);
            d[count] = x;
            try {
                d[count+1] = f.getResult(x);
                count += 2;
            } catch(Exception e) { 
                error = true; 
            }
        }

        if(count <= 2) {
            JOptionPane.showMessageDialog(getParent(), "Error NO POINTS to PLOT!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(error) {
            JOptionPane.showMessageDialog(getParent(), "Error while calculating points!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        yaxis.setTitleText(function);
        data.deleteData();

        try {
            data.append(d,count/2);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(getParent(), "Error while appending data!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        graph.repaint();
    }
    
    public void update() {
        try {
            function = view.functionTextField.getText();
            points = Integer.parseInt(view.nopTextField.getText());
            min = Double.parseDouble(view.initialXTextField.getText());
            max = Double.parseDouble(view.finalXTextField.getText());
            plot();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(getParent(), "A entrada esta errada", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
