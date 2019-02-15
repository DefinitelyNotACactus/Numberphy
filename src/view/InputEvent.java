/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 * Interface do evento de atualização da entrada do usuário.
 * @author vk
 */
public interface InputEvent {
    public double[] inputUpdate(ExpressionInput input, InputEventManager event);
}
