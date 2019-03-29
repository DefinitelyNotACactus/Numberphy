/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Pablo Suria
 */
public class Spline {
    
    public double[] calH(double[] X) {
        double[] h = new double[X.length];
        
        for(int i = 1; i < X.length; i++) {
            h[i] = X[i] - X[i-1];
        }
        
        return h;
    }
    
    public double[] CalMu(double[] H) {
        double[] mu = new double[H.length];
        mu[0] = 0; 
        mu[H.length-1] = 1;
        for(int i = 1; i < H.length-1; i++) {
            mu[i] = H[i] / (H[i] + H[i+1]);
        }
        
        return mu;
    }
    
    public double[] calLambda(double[] MU) {
        double[] lambda = new double[MU.length];
        
        lambda[0] = 0;
        for(int i = 1; i < MU.length; i++){
            lambda[i] = 1 - MU[i];
        }
        
        return lambda;
    }
    
    public double[] calDD(double[] X, double[]Y, double d0, double dn) {
        double[] dd = new double[X.length];
        
        dd[0] = ( (Y[1] - Y[0])/(X[1] - X[0]) - d0 )  / X[1] - X[0];
        dd[X.length-1] = (dn -  ( Y[X.length-1] - Y[X.length-2])/(X[X.length - 1] - X[X.length-2]) )/ (X[X.length - 1] - X[X.length-2]);
        
        for( int i = 1; i < X.length - 1; i++) {
            dd[i] =( (Y[i+1] - Y[i])/(X[i+1] - X[i]) - (Y[i] - Y[i-1])/(X[i] - X[i-1]) ) / (X[i+1] - X[i-1]);
        }
        
        return dd;
    }
    
    public double[] calM(double[] mu, double[] lambda, double[] dd) {
        double[] m = new double[mu.length];
        
        m[0] = (dd[0] * 6) /2;
        m[mu.length-1] = (dd[mu.length-1] * 6) /2;
        System.out.println("as"+ m[0] + " " + m[mu.length-1]);
        return m;
    }
    
    
    
}
