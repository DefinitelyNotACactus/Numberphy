/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.SimpleFunction;
import edu.hws.jcm.data.ValueMath;
import view.InputEventManager;

/**
 *
 * @author (L)uan(A)villa(R)enan
 */
public class Lobatto extends AbstractGauss {
    
    private final int numPoints;
    private final double a, b;
    private final String function;
    private Function s;
    
    private final String computedFunction;
    private double area;
    private double[] computedLimits;
    private final Iteration[] iterations;
    
    public Lobatto(view.ExpressionInput input, String function, double a, double b, int numPoints) {
        super(input, function, a, b, numPoints);
        this.a = a;
        this.b = b;
        
        this.numPoints = numPoints;
        this.function = function;

        computedFunction = changeLimits();
        iterations = getIterations();
    }
    
    public Iteration[] getRootsAndWeights() {
        return iterations;
    }
    
    public String changeLimits() {
        double a1 = (0.5) * (b - a);
        double a0 = (0.5) * (b + a);
        
        String newFunction = function.replaceAll("x", "((" + a1 +")*x + " + a0 + ")");
        newFunction = "(" + newFunction + ") * " + a1;
        
        return newFunction;
    }
    
    public double[] getComputedLimits() {
        return computedLimits;
    }
    
    public double[] roots(int n) {
        //double[] roots = null;
        switch (n) {
            case 2:
                double[] roots2 = {-1.0, 1.0};
                return roots2;
                //break;
            case 3:
                double[] roots3 = {
                    -1.0,
                    0.0,
                    1.0
                };
                return roots3;
                //break;
            case 4:
                double[] roots4 = {
                    (-1.0),
                    (-0.4472),
                    0.4472,
                    1.0
                };
                return roots4;
                //break;
            case 5:
                double[] roots5 = {-1.0,
                -0.6546,
                0.0,
                0.6546,
                1.0};
                return roots5;
                //break;
            default:
                return null;
        }
    }
    
    public double[] weight(int n, double[] roots) {
        String generalFunction = "2 / (n * (n-1) * (P)^2)";
        String newFunction1, newFunction2;
        
        switch (n) {
            case 2:
                double[] weights2 = new double[2];
                newFunction1 = generalFunction.replaceAll("n", "2");
                
                String P1 = "x";
                for (int i = 0; i < n; ++i) {                    
                    String PResult = P1.replaceAll("x", roots[i] + "");
                    newFunction2 = newFunction1.replaceAll("P", PResult);
                    
                    s = getInput().createFunction(newFunction2);
                    ValueImpl v = new ValueImpl(roots[i]);
                    weights2[i] = new ValueMath(s, v).getVal();
                    
                    /*ExpressionInput input = new ExpressionInput(newFunction2, new Parser());
                    weights2[i] = input.getVal();*/
                }
                
                return weights2;
                
            case 3:
                double[] weights3 = new double[3];
                newFunction1 = generalFunction.replaceAll("n", "3");
                
                String P2 = "0.5 * (3 * (x)^2 - 1)";
                for (int i = 0; i < n; ++i) {
                    String PResult = P2.replaceAll("x", roots[i] + "");
                    newFunction2 = newFunction1.replaceAll("P", PResult);
                    
                    s = getInput().createFunction(newFunction2);
                    ValueImpl v = new ValueImpl(roots[i]);
                    weights3[i] = new ValueMath(s, v).getVal();
                    
                    /*ExpressionInput input = new ExpressionInput(newFunction2, new Parser());
                    weights3[i] = input.getVal();*/
                }
                
                return weights3;
                
            case 4:
                double[] weights4 = new double[4];
                newFunction1 = generalFunction.replaceAll("n", "4");
                
                String P3 = "0.5 * (5 * (x)^3 - 3 * (x))";
                for (int i = 0; i < n; ++i) {
                    String PResult = P3.replaceAll("x", roots[i] + "");
                    newFunction2 = newFunction1.replaceAll("P", PResult);
                    
                    s = getInput().createFunction(newFunction2);
                    ValueImpl v = new ValueImpl(roots[i]);
                    weights4[i] = new ValueMath(s, v).getVal();
                    
                    /*ExpressionInput input = new ExpressionInput(newFunction2, new Parser());
                    weights4[i] = input.getVal();*/
                }
                
                return weights4;
                
            case 5:
                double[] weights5 = new double[5];
                newFunction1 = generalFunction.replaceAll("n", "5");
                
                String P4 = "0.125 * (35 * (x)^4 - 30 * (x)^2 + 3)";
                for (int i = 0; i < n; ++i) {
                    String PResult = P4.replaceAll("x", roots[i] + "");
                    newFunction2 = newFunction1.replaceAll("P", PResult);
                    
                    s = getInput().createFunction(newFunction2);
                    ValueImpl v = new ValueImpl(roots[i]);
                    weights5[i] = new ValueMath(s, v).getVal();
                    
                    /*ExpressionInput input = new ExpressionInput(newFunction2, new Parser());
                    weights5[i] = input.getVal();*/
                }
                
                return weights5;
            default:
                return null;
        }
    }
    
    @Override
    public double getArea() {
        return area;
    }

    @Override
    public Iteration[] getIterations() {
        double[] roots = roots(numPoints);
        double[] weights = weight(numPoints, roots);
        ValueImpl v;
        area = 0;
        Iteration rootsAndWeights[] = new Iteration[roots.length];
        
        for(int i = 0; i < roots.length; i++) {
            rootsAndWeights[i] = new Iteration(roots[i], weights[i]);
        }

        for (int i = 0; i < numPoints; i++) {
            s = getInput().createFunction(computedFunction);
            v = new ValueImpl(roots[i]);
            area += (new ValueMath(s, v).getVal()) * weights[i];            
        }
        computedLimits = new double[4];
        computedLimits[0] = -1;
        computedLimits[1] = 1;
        v = new ValueImpl(-1);
        computedLimits[2] = new ValueMath(s, v).getVal();
        v = new ValueImpl(1);
        computedLimits[3] = new ValueMath(s, v).getVal();
        
        return rootsAndWeights;
    }

    @Override
    public SimpleFunction getComputedFunction() {
        return getInput().createFunction(computedFunction);
    }

    @Override
    public Iteration[] inputUpdate(view.ExpressionInput input, InputEventManager event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pointClickedEvent(view.ExpressionInput input, InputEventManager event, double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
