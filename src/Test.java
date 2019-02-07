/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.javafx.css.CalculatedValue;
import java.util.Scanner;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

/**
 *
 * @author avilla
 */
public class Test {
    
    static double halley(String function, double x0, double tolmin, int nitr) {
        
        double xn = x0;
        double tolmax = tolmin + 1;
        int itr = 0;
        
        Function f = new Function("f(x) = " + function);
        
        Expression e = new Expression("f(" + xn + ")", f);
        
        Expression e1 = new Expression("der( " + f.getFunctionExpressionString() + ", x, " + xn + ")");
        
        Expression e2 = new Expression("der( der( " + f.getFunctionExpressionString() + ", x, x), x, " + xn + ")");
        
        while(tolmax > tolmin && itr < nitr) {
            
            x0 = xn;
            xn = xn - (2*e.calculate()*e1.calculate())/(2*e1.calculate()*e1.calculate() - e.calculate()*e2.calculate());
            
            itr++;
            
            if(xn != 0) {
                tolmax = Math.abs((xn - x0)/xn);
            }
        }
        
        return xn;
    }
    
    public static void main(String[] args) {
        
        Function f = new Function("f(x) = x^(3)");
        Expression e = new Expression("der( " + f.getFunctionExpressionString() + ", x, 2)");
        System.out.println(e.calculate());
    }
}
