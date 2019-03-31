/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.methods;

import data.Constants;
import data.Iteration;
import data.ValueImpl;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.draw.Crosshair;
import view.ExpressionInput;
import view.InputEventManager;
import view.MethodImplementation;


public class Ridders implements MethodImplementation {

    @Override
    public Iteration[] inputUpdate(ExpressionInput input, InputEventManager event) {
        return ridders(input, event, input.getx0(), input.getXr(), input.getTolerance(), input.getIterations());
    }

    @Override
    public void pointClickedEvent(ExpressionInput input, InputEventManager event, double x, double y) {
        // NOP
    }
    
    
    /**
     * Calcula a raiz da funcao usando o metodo de ridders
     * @param input
     * @param event
     * @param xl X da esquerda
     * @param xr X da direita
     * @param tolmin Tolerancia minima
     * @param nitr Numero de iteracoes maxim@
     * @return Um array com cada iteracao
     */
    private Iteration[] ridders(ExpressionInput input, InputEventManager event, double xl, double xr, double tolmin, int nitr) {
        Iteration[] iterations = new Iteration[nitr];
        Function f = input.getFunction(input.getApplication().getVariable());
        Crosshair crossh = null;
        
        double x = 0.0;
        double x_old;
        int itr = 0;
        double err = 1.0;
        
        while(err > tolmin && itr < nitr) {
            x_old = x;
            double fr = new ValueMath(f, new ValueImpl(xr)).getVal();
            double fl = new ValueMath(f, new ValueImpl(xl)).getVal();
            double d0 = Math.abs(fr - fl);
            x = (xr*fl - xl*fr)/(fl - fr);
            double fx = new ValueMath(f, new ValueImpl(x)).getVal();
            
            double a = (fl - fx)/(fx - fr);
            double b = (fl - fx)/(fx - a*fr);
            double beta = b - 1.0;
            double alpha = a - 1.0;
            double lnb = beta - beta*beta/2 + beta*beta*beta/3;
            double lna = alpha - alpha*alpha/2 + alpha*alpha*alpha/3;
            double root = xl + d0*lnb/lna;
            double froot = new ValueMath(f, new ValueImpl(root)).getVal();
            
            if(fl*fx < 0) {
                if(xl < root && root < x) {
                    if(fx*froot < 0) {
                        xl = root;
                        xr = x;
                    } else {
                        xr = root;
                    }
                } else {
                    xr = x;
                }
            } else if(fl*fx > 0) {
                if(x < root && root < xr) {
                    if(fx*froot < 0) {
                        xl = x;
                        xr = root;
                    } else {
                        xl = root;
                        fl = froot;
                    }
                } else {
                    xl = x;
                    fl = fx;
                }
            } else {
                if(fl == 0) {
                    x = xl;
                }
                break;
            }
            err = Math.abs(xr - xl);
            iterations[itr] = new Iteration(x, Math.abs(x-x_old)/x);            
            itr++;        
            crossh = event.drawCrossHair(new ValueImpl(x), f);
        }
        if (crossh != null) {
            crossh.setColor(Constants.RED);
            crossh.setLineWidth(2);
        }
        return iterations;
    }

}
