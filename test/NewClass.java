/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.Spline;
/**
 *
 * @author Pablo Suria
 */
public class NewClass {
    
    public static void main(String args[]) {
        Spline t = new Spline();
        double[] X = {0,1,2,3};
        double[] Y = {0, 0.5, 2, 1.5};
        double[] H = t.calH(X);
        double[] MU = t.CalMu(H);
        double[] lambda = t.calLambda(MU);
        double[] dd = t.calDD(X, Y, 0.2,-1);
        double[] m = t.calM(MU, lambda, dd);
        
        for(int i = 0; i < X.length; i++) {
            System.out.println("" + X[i]);
            
        }
        System.out.println("");
        for(int i = 0; i < X.length; i++) {
            System.out.println("" + H[i]);
        }
        System.out.println("");
        for(int i = 0; i < X.length; i++) {
            System.out.println("" + MU[i]);
        }
        System.out.println("");
        for(int i = 0; i < X.length; i++) {
            System.out.println("" + lambda[i]);
        }
        System.out.println("");
        for(int i = 0; i < X.length; i++) {
            System.out.println("" + dd[i]);
        }
    }
}
