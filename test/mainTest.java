/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.Hermite;
import data.Spline;

/**
 *
 * @author Pablo Suria
 */
public class mainTest {

    public static void main(String args[]) {
        double[] X = {-1, 0, 1};
        double[] Y = {2, 1, 2};
        double[] dif = {-8,0,8};
        double[] dif2 = {56, 0 ,56};
        
        Hermite.interpolate(X, Y, dif,dif2);
       
    }
    
}
