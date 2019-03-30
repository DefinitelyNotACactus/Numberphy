/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.Iteration;

/**
 * Interface do evento de atualização da entrada do usuário.
 * @author vk
 */
public interface InputEvent {
    
    /**
     * Occurs every time the user hits the Enter key while inserting an expression.
     * The expected behavior of this implementation is to check if the input is valid and update the GUI.
     * @param input The input control GUI
     * @param event The input manager, with some facilities.
     * @return Points of every iteration.
     */
    public Iteration[] inputUpdate(ExpressionInput input, InputEventManager event);
    
    /**
     * Occurs every time the user clicks on the graph while pressing Alt key.
     * @param input The input control GUI
     * @param event The input manager, with some facilities.
     * @param x X point coordinate
     * @param y Y point coordinate
     */
    public void pointClickedEvent(ExpressionInput input, InputEventManager event, double x, double y);
    
}
