/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author LAR
 */
public class Lobatto {
    
    public static String changeLimits(double[] limits, String function) {
        double a1 = (0.5) * (limits[1] - limits[0]);
        double a0 = (0.5) * (limits[1] + limits[0]);
        String newFunction = function.replaceAll("x", "((" + a1 +")*t + " + a0 + ")");
        newFunction = "(" + newFunction + ") * " + a1;
        return newFunction;
    }
    
    public static double[] roots(int n) {
        //double[] roots = null;
        switch (n) {
            case 2:
                double[] roots2 = {-1.0, 1.0};
                return roots2;
                //break;
            case 3:
                double[] roots3 = {
                    -1.0,
                    0.0,
                    1.0
                };
                return roots3;
                //break;
            case 4:
                double[] roots4 = {
                    (-1.0),
                    (-0.4472),
                    0.4472,
                    1.0
                };
                return roots4;
                //break;
            case 5:
                double[] roots5 = {-1.0,
                -0.6546,
                0.0,
                0.6546,
                1.0};
                return roots5;
                //break;
        }
        return null;
    }
    
    public static double[] weight(int n, double[] d) {
        switch (n) {
            case 2:
                String funcion = ""; 
        }
        return null;
    }
    /*public static double lobatto(double[] limits, int n, String function) {
        
    }*/
}
