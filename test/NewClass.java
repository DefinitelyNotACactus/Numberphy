/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.Spline;
import data.Poli;

/**
 *
 * @author Pablo Suria
 */
public class NewClass {

    public static void main(String args[]) {
        double[] X = {0, 1, 2, 3};
        double[] Y = {0, 0.5, 2, 1.5};
        
        Spline spline = new Spline();
        Poli[] ci = spline.Intepolate(X, Y, 0.2, 1.0);
    }
    
}
