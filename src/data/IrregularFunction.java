/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import edu.hws.jcm.data.Cases;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Variable;

/**
 *
 * @author vk
 */
public abstract class IrregularFunction implements Function {

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public double getVal(double[] arguments) {
       return getValueWithCases(arguments,null);
    }

    @Override
    public double getValueWithCases(double[] arguments, Cases cases) {
        return eval(arguments[0]);
    }

    @Override
    public Function derivative(int wrt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Function derivative(Variable x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dependsOn(Variable x) {
        return x.getName().equals("x");
    }
    
    /**
     * Function implemented;
     * @param x
     * @return Value of function computed on x.
     */
    public abstract double eval(double x);
}
