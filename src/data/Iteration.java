/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author david
 */
public class Iteration {
    private double x;
    private double err;
    
    public Iteration(double x, double err) {
        this.x = x;
        this.err = err;
    }
    
    public double getX() {
        return x;
    }
    
    public double getRelativeError() {
        return err;
    }
}
