/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.methods.*;
import view.MethodImplementation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lazy person
 */
public enum MethodsEnum {
    
    HALLEY(Halley.class), RIDDERS(Ridders.class), HERMITE, SPLINES, GAUSS, WELCOME, EXTRA;
    
    private final Class method;
    private static final HashMap<MethodsEnum, MethodImplementation> instances = new HashMap<>();
    
    private MethodsEnum() {
        this.method = null;
    }
    
    private MethodsEnum(Class method) {
        this.method = method;
    }
    
    /**
     * Obtém uma instância de MethodImplementation correspondente ao método.
     * Inicializa uma caso nenhuma tenha sido inicilizada ainda.
     * @return Instância
     */
    public MethodImplementation getInstance() {
        if (instances.containsKey(this)) {
            return instances.get(this);
        }
        
        MethodImplementation mi = null;
        
        try {
            if (this.method != null)
                mi = (MethodImplementation)this.method.getConstructor().newInstance();
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |
                NoSuchMethodException | SecurityException | InstantiationException ex) {
            Logger.getLogger(MethodsEnum.class.getName()).log(Level.SEVERE, null, ex);
        }
        instances.put(this, mi);
        return mi;
    }
    
    /**
     * Substitui a instância de getInstance()
     * @param inst Instância
     */
    public void setInstance(MethodImplementation inst) {
        instances.put(this, inst);
    }
}
