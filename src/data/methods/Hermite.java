/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.methods;

import data.Iteration;
import view.ExpressionInput;
import view.InputEventManager;
import view.MethodImplementation;

/**
 *
 * @author david
 */
public class Hermite implements MethodImplementation {

    @Override
    public Iteration[] inputUpdate(ExpressionInput input, InputEventManager event) {
        //return hermite(input, event, input.getXArray(), input.getYArray(), input.getN());
        return null;
    }

    @Override
    public void pointClickedEvent(ExpressionInput input, InputEventManager event, double x, double y) {
        //Do nothing...
    }
    /*
    private Iteration[] hermite(ExpressionInput input, InputEventManager event, double[] x, double[] y, int n) {
        double p = 0.0, xi, xj, ljn_num, ljn_den, diff_ljn, hjn, ljn;
        //Lagrange
        for(int i = 0; i < n; i++) {
            xi = x[i];
            ljn_num = 1.0;
            ljn_den = 1.0;
            for(int j = 0; j < n; j++) {
                xj = x[i];
                if(i != j) {
                    ljn_num *= (x - xj);
                    ljn_den *= (xi - xj);
                    
                }
            }
            ljn = ljn_num / ljn_den;
        }
        //Hermite interpolation
        diff_ljn = 0.0;
        for(int i = 0; i < n; i++) {
            xj = x[i];
            if(i )
        }
        return null;
    }
    */
}
