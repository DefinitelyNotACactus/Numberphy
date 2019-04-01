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
public class Hermite {

    /**
     * Returns the polynomial generated by the Hermite interpolation
     * @param X An double array containing the X's
     * @param Y An double array containing the Y's
     * @param dif An double array containing the derivatives
     * @return An string with the polynomial
     */
    public static String interpolate(double[] X, double[] Y, double[] dif) {
        int n = X.length;

        String p = "";
        for (int i = 0; i < n; i++) {
            double xi = X[i];
            String ljn_num = "1";
            double ljn_den = 1;

            for (int j = 0; i < n; j++) {
                double xj = X[j];
                if (i != j) {
                    ljn_num += " * (x - " + xi +")";
                    ljn_den *= (xj - xi);
                }
            }
            String ljn = "(" +ljn_num + "/" + ljn_den +")";

            double diff_ljn = 0;
            for (int j = 0; j < n; j++) {
                double xj = X[j];
                if (i != j) {
                    diff_ljn += 1.0 / (xj - xi);
                }
            }

            String hjn = "(1.0 - 2 * (x - " + xi +") * " + diff_ljn+ ") * (" + ljn + " * " +ljn +")";
            String hjn_ = "(x - " + xi + ") * (" + ljn + " * " + ljn + ")";
            
            p += (hjn + " * " + Y[i] + " + " + hjn_ +" * " +dif[i]);
        }
        
        return p;
    }

}
