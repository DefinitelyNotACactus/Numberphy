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
        Spline t = new Spline();
        double[] X = {0, 1, 2, 3};
        double[] Y = {0, 0.5, 2, 1.5};

        System.out.println("X:");
        for (int i = 0; i < X.length; i++) {
            System.out.println("" + X[i]);
        }

        double[] H = t.calH(X);
        System.out.println("H:");
        for (int i = 0; i < X.length; i++) {
            System.out.println("" + H[i]);
        }

        double[] MU = t.CalMu(H);
        System.out.println("MU:");
        for (int i = 0; i < X.length; i++) {
            System.out.println("" + MU[i]);
        }

        double[] lambda = t.calLambda(MU);
        System.out.println("Lambda:");
        for (int i = 0; i < X.length; i++) {
            System.out.println("" + lambda[i]);
        }

        double[] dd = t.calDD(X, Y, 0.2, -1);
        System.out.println("DD:");
        for (int i = 0; i < X.length; i++) {
            System.out.println("" + dd[i]);
        }

        double[][] sistema = t.montarSistema(lambda, MU);
        System.out.println("Sistema:");
        for (int i = 0; i < sistema.length; i++) {
            for (int j = 0; j < sistema[0].length; j++) {
                System.out.print("" + sistema[i][j] + " | ");
            }
            System.out.println("");
        }

        double[] M = t.calM(sistema, dd);
        System.out.println("M:");
        for (int i = 0; i < M.length; i++) {
            System.out.println("" + M[i]);
        }

        Poli[] ci = t.montarPolinomios(X, Y, H, M);
        System.out.println("Resultados");
        System.out.println("c1 x0" + ci[1].poli(X[0]));
        System.out.println("c1 x1" + ci[1].poli(X[1]));
        System.out.println("c2 x1" + ci[2].poli(X[1]));
        System.out.println("c2 x2" + ci[2].poli(X[2]));
        System.out.println("c3 x2" + ci[3].poli(X[2]));
        System.out.println("c3 x3" + ci[3].poli(X[3]));

    }
}
