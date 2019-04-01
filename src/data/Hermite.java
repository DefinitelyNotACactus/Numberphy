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

    public static String interpolate(double x, double[] X, double[] Y, double[] dif, int n) {

        if (n <= 0) {
            n = X.length;
        } else {
            n = n + 1;
        }

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
            String hjn_ =" (x - " + xi + ") * (" + ljn + " * " + ljn + ")";
            
            p += hjn + " * " + Y[i] + " + " + hjn_ +" * " +dif[i];
        }
        
        return p;
    }

}
