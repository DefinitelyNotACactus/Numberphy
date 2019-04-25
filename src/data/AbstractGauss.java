/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import edu.hws.jcm.data.SimpleFunction;
import view.ExpressionInput;
import view.MethodImplementation;

/**
 *
 * @author nullPointerException
 */
public abstract class AbstractGauss implements MethodImplementation {
    private final ExpressionInput input;
    
    public AbstractGauss(ExpressionInput input, String function, double a, double b, int numPoints) {
        this.input = input;
    }
    
    protected ExpressionInput getInput() {
        return input;
    }
    
    public abstract double getArea();
    
    public abstract Iteration[] getIterations();
    
    public abstract SimpleFunction getComputedFunction();
    
    public abstract String getComputedFunctionString();
    
    
}
