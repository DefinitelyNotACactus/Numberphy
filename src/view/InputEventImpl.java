/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.ValueImpl;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.draw.Crosshair;
import java.awt.Color;

/**
 *
 * @author david
 */
public class InputEventImpl implements InputEvent {

    @Override
    public void inputUpdate(ExpressionInput input, InputEventManager event) {
        switch(input.getMethod()) {
            case HALLEY:
                halley(input, event, input.getx0(), input.getTolerance(), input.getIterations());
                break;
            case RIDDERS:
                ridders(input, event, input.getx0(), input.getXr(), input.getTolerance(), input.getIterations());
                break;
            default:
                //do nothing
                break;
        }
    }
    
    public void halley(ExpressionInput input, InputEventManager event, double x0, double tolmin, int nitr) {
        double xn = x0;
        double tolmax = tolmin + 1;
        int itr = 0;
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

            itr++;
            if (xn != 0) {
                tolmax = Math.abs((xn - x0) / xn);
            }
        }
        if (crossh != null) {
            crossh.setColor(Color.red);
            crossh.setLineWidth(2);
        }
    }
    
    public void ridders(ExpressionInput input, InputEventManager event, double xl, double xr, double tolmin, int nitr) {
        double x; 
        double tolmax = 1;
        int itr = 0;
        
        Function f = input.getFunction(input.getApplication().getVariable());
        Crosshair crossh = null;
        
        while (tolmax > tolmin && itr < nitr) {
            double fl = new ValueImpl(xl).getVal();
            double fr = new ValueImpl(xr).getVal();
            double i = Math.abs(xl - xr);
            x = xr - fr*(xl-xr)/(fl - fr);
            Value vx = new ValueImpl(x);
            double fx = new ValueMath(f, vx).getVal();   
            
            double alfa = (fl - fx)/(fx - fr);
            double beta = (fl - fx)/(fl - alfa*fx);
            double xa = alfa - 1;
            double xb = beta - 1;
            
            double lnxa = alfa - alfa*alfa/2 + alfa*alfa*alfa/3;
            double lnxb = beta - beta*beta/2 + beta*beta*beta/3;
            double root = xl - i*lnxb/lnxa;
            Value vroot = new ValueImpl(root);
            double froot = new ValueMath(f, vroot).getVal();
            
            crossh = event.drawCrossHair(vroot, f);
            
            if(fl * fx < 0) {
                if(xl < root && root < x) {
                    if(fx * froot < 0) {
                        xl = root;
                        xr = x;
                    }
                    
                    else {
                        xr = root;
                    }
                }
                
                else {
                    xr = x;
                }
            }
            
            else if (fl * fx > 0) {
                if(x < root && root < xr) {
                    if(fx * froot < 0) {
                        xl = x;
                        xr = root;
                    }
                    
                    else {
                        xl = root;
                        fl = froot;
                    }
                }
                
                else {
                    xl = x;
                    fl = fx;
                }
            }
            
            else {
                if(fl == 0) {
                    x = xl;
                }
                break;
            }
            
            tolmax = Math.abs(xr - xl);
            itr++;
        }
        
        if (crossh != null) {
            crossh.setColor(Color.red);
            crossh.setLineWidth(2);
        }
    }
}