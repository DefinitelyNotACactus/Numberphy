/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.Constants;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author david
 */
public class TableInput extends JPanel {
    private JTextField xField;
    private JTextField yField;
    
    public static final GridLayout GRID = new GridLayout(0, 2);
    
    public TableInput() {
        initComponents();
    }
    
    private void initComponents() {
        setBackground(Constants.WHITE);
        setLayout(GRID);
        
        xField = new JTextField(10);
        xField.setFont(Constants.HELVETICA);
        xField.setBackground(Constants.WHITE);
        add(xField);
        
        yField = new JTextField(10);
        yField.setFont(Constants.HELVETICA);
        yField.setBackground(Constants.WHITE);
        add(yField);
        
        setVisible(true);
    }
    
    public String getXInput() {
        return xField.getText();
    }
    
    public String getYInput() {
        return yField.getText();
    }
    
    public double parseX() throws NumberFormatException {
        return Double.parseDouble(getXInput());
    }
    
    public double parseY() throws NumberFormatException {
        return Double.parseDouble(getYInput());
    }
}
