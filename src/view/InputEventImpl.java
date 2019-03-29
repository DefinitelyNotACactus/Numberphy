/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.Constants;
import data.Iteration;
import data.ValueImpl;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.draw.Crosshair;

/**
 *
 * @author david
 */
public class InputEventImpl implements InputEvent {

    @Override
    public Iteration[] inputUpdate(ExpressionInput input, InputEventManager event) {
        event.drawString("f(x) = " + input.getFunctionString());
        Iteration points[] = null;
        switch(input.getMethod()) {
            case HALLEY:
                points = halley(input, event, input.getx0(), input.getTolerance(), input.getIterations());
                break;
            case RIDDERS:
                points = ridders(input, event, input.getx0(), input.getXr(), input.getTolerance(), input.getIterations());
                break;
            default:
                //do nothing
                break;
        }
        return points;
    }
    
    /**
     * Calcula a raiz da funcao usando o metodo de Halley
     * @param input
     * @param event 
     * @param x0 Estimativa do X inicial
     * @param tolmin Tolerancia minima
     * @param nitr Numero de iteracoes maxim@
     * @return Um array com cada iteracao
     */
    public Iteration[] halley(ExpressionInput input, InputEventManager event, double x0, double tolmin, int nitr) {
        Iteration[] iterations = new Iteration[nitr+1];
        double xn = x0;
        double tolmax = tolmin + 1;
        int itr = 0;
        iterations[0] = new Iteration(xn, 1);
        Function f = input.getFunction(input.getApplication().getVariable());
        Function dev1 = f.derivative(1);
        Function dev2 = dev1.derivative(1);
        Crosshair crossh = null;
        
        while (tolmax > tolmin && itr < nitr) {
            x0 = xn;
            Value v = new ValueImpl(xn);
            double fx = new ValueMath(f, v).getVal();
            double d1 = new ValueMath(dev1, v).getVal();
            double d2 = new ValueMath(dev2, v).getVal();
            
            xn = xn - (2 * fx * d1) / (2 * d1 * d1 - fx * d2);
            crossh = event.drawCrossHair(v, f);           
            if (xn != 0) {
                tolmax = Math.abs((xn - x0) / xn);
            }
            itr++;
            iterations[itr] = new Iteration(xn, tolmax);
        }
        if (crossh != null) {
            crossh.setColor(Constants.RED);
            crossh.setLineWidth(2);
        }
        return iterations;
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
    public Iteration[] ridders(ExpressionInput input, InputEventManager event, double xl, double xr, double tolmin, int nitr) {
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