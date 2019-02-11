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
                //todo
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
}
