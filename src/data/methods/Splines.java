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


public class Splines implements MethodImplementation {

    @Override
    public Iteration[] inputUpdate(ExpressionInput input, InputEventManager event) {
        return new Iteration[0];
    }

    @Override
    public void pointClickedEvent(ExpressionInput input, InputEventManager event, double x, double y) {
        // NOP
    }
    
    

}
