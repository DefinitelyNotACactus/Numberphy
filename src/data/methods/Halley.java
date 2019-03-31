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
import edu.hws.jcm.data.Value;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.draw.Crosshair;
import view.ExpressionInput;
import view.InputEventManager;
import view.MethodImplementation;


public class Halley implements MethodImplementation {

    @Override
    public Iteration[] inputUpdate(ExpressionInput input, InputEventManager event) {
        return halley(input, event, input.getx0(), input.getTolerance(), input.getIterations());
    }

    @Override
    public void pointClickedEvent(ExpressionInput input, InputEventManager event, double x, double y) {
        // NOP
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
    private Iteration[] halley(ExpressionInput input, InputEventManager event, double x0, double tolmin, int nitr) {
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
    
    
}
