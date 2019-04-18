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
    
    public static final GridLayout GRID_2 = new GridLayout(0, 2);
    public static final GridLayout GRID_3 = new GridLayout(0, 3);
    
    private JTextField xField;
    private JTextField yField;
    private JTextField dxField;
    private final boolean triplet;
    
    public TableInput(boolean triplet) {
        this.triplet = triplet;
        initComponents();
    }
    
    private void initComponents() {
        setBackground(Constants.WHITE);
        setLayout(triplet? GRID_3 : GRID_2);
        
        xField = new JTextField();
        xField.setFont(Constants.HELVETICA);
        xField.setBackground(Constants.WHITE);
        add(xField);
        
        yField = new JTextField();
        yField.setFont(Constants.HELVETICA);
        yField.setBackground(Constants.WHITE);
        add(yField);
        
        if(triplet) {
            dxField = new JTextField();
            dxField.setFont(Constants.HELVETICA);
            dxField.setBackground(Constants.WHITE);
            add(dxField);
        }
        setVisible(true);
    }
    
    public String getXInput() {
        return xField.getText();
    }
    
    public void setXInput(String xInput) {
        xField.setText(xInput);
    }
    
    public String getYInput() {
        return yField.getText();
    }
    
    public void setYInput(String yInput) {
        yField.setText(yInput);
    }
    
    public String getDxInput() {
        return dxField.getText();
    }
    
    public void setDxInput(String dxInput) {
        dxField.setText(dxInput);
    }
    
    public double parseX() throws NumberFormatException {
        return Double.parseDouble(getXInput());
    }
    
    public double parseY() throws NumberFormatException {
        return Double.parseDouble(getYInput());
    }
    
    public double parseDx() throws NumberFormatException {
        return Double.parseDouble(getXInput());
    }
}
