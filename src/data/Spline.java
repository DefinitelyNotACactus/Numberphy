/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static java.lang.Math.pow;

public class Spline {

    private double[] calH(double[] X) {
        double[] h = new double[X.length];

        for (int i = 1; i < X.length; i++) {
            h[i] = X[i] - X[i - 1];
        }

        return h;
    }

    private double[] CalMu(double[] H) {
        double[] mu = new double[H.length];

        mu[H.length - 1] = 1;
        for (int i = 0; i < H.length - 1; i++) {
            mu[i] = H[i] / (H[i] + H[i + 1]);
        }

        return mu;
    }

    private double[] calLambda(double[] MU) {
        double[] lambda = new double[MU.length];

        //lambda[0] = 0;
        for (int i = 0; i < MU.length; i++) {
            lambda[i] = 1 - MU[i];
        }

        return lambda;
    }

    private double[] calDD(double[] X, double[] Y, double d0, double dn) {
        double[] dd = new double[X.length];

        dd[0] = ((Y[1] - Y[0]) / (X[1] - X[0]) - d0) / (X[1] - X[0]);
        dd[X.length - 1] = (dn - (Y[X.length - 1] - Y[X.length - 2]) / (X[X.length - 1] - X[X.length - 2])) / (X[X.length - 1] - X[X.length - 2]);
        for (int i = 1; i < X.length - 1; i++) {
            dd[i] = ((Y[i + 1] - Y[i]) / (X[i + 1] - X[i]) - (Y[i] - Y[i - 1]) / (X[i] - X[i - 1])) / (X[i + 1] - X[i - 1]);
        }

        return dd;
    }

    private double[][] montarSistema(double[] lambda, double[] mu) {
        double[][] sistema = new double[mu.length][mu.length];

        for (int i = 0; i < mu.length; i++) {
            sistema[i][i] = 2;

            if (i + 1 < mu.length) {
                sistema[i][i + 1] = lambda[i];
            }
            if (i - 1 >= 0) {
                sistema[i][i - 1] = mu[i];
            }
        }

        return sistema;
    }

    private double[] calM(double[][] sistema, double[] dd) {
        double[] M = lsolve(sistema, dd);
        return M;
    }

    private Poli[] montarPolinomios(double[] X, double[] Y, double[] H, double[] M) {
        Poli[] Ci = new Poli[X.length];

        for (int i = 1; i < X.length; i++) {
            final double Mi_1 = M[i - 1];
            final double Mi = M[i];
            final double Xi_1 = X[i - 1];
            final double Xi = X[i];
            final double Yi_1 = Y[i - 1];
            final double Yi = Y[i];
            final double Hi = H[i];
            Ci[i] = (double x) -> (Mi_1 * pow((Xi - x), 3)) / (6 * Hi)
                    + (Mi * pow((x - Xi), 3)) / (6 * Hi)
                    + (Yi_1 - (Mi - 1 * Hi * Hi) / 6) * ((Xi - x) / Hi)
                    + (Yi - (Mi * Hi * Hi) / 6) * ((x - Xi_1) / Hi);
        }

        return Ci;
    }

    public Poli[] Intepolate(double[] X, double[] Y, double d0, double dn) {
        System.out.println("X:");
        for (int i = 0; i < X.length; i++) {
            System.out.println("" + X[i]);
        }
        
        double[] H = calH(X);
        System.out.println("H:");
        for (int i = 0; i < H.length; i++) {
            System.out.println("" + H[i]);
        }
        
        double[] MU = CalMu(H);
        System.out.println("MU:");
        for (int i = 0; i < MU.length; i++) {
            System.out.println("" + MU[i]);
        }
        
        double[] LAMBDA = calLambda(MU);
        System.out.println("Lambda:");
        for (int i = 0; i < LAMBDA.length; i++) {
            System.out.println("" + LAMBDA[i]);
        }
        
        double[] DD = calDD(X, Y, d0, dn);
        System.out.println("DD:");
        for (int i = 0; i < DD.length; i++) {
            System.out.println("" + DD[i]);
        }
        
        double[][] sistema = montarSistema(LAMBDA, MU);
        System.out.println("Sistema:");
        for (int i = 0; i < sistema.length; i++) {
            for (int j = 0; j < sistema[0].length; j++) {
                System.out.print("" + sistema[i][j] + " | ");
            }
            System.out.println("");
        }
        
        double[] M = calM(sistema, DD);
        System.out.println("M:");
        for (int i = 0; i < M.length; i++) {
            System.out.println("" + M[i]);
        }
        
        Poli[] Ci = montarPolinomios(X, Y, H, M);
        System.out.println("Resultados");
        System.out.println("c1 x0: " + Ci[1].poli(X[0]));
        System.out.println("c1 x1: " + Ci[1].poli(X[1]));
        System.out.println("c2 x1: " + Ci[2].poli(X[1]));
        System.out.println("c2 x2: " + Ci[2].poli(X[2]));
        System.out.println("c3 x2: " + Ci[3].poli(X[2]));
        System.out.println("c3 x3: " + Ci[3].poli(X[3]));
        
        return Ci;
    }

    /**
     * Método da eliminação gaussiana com pivoteamento parcial.
     *
     * @param A Matriz contendo os coeficientes do sistema.
     * @param b Vetor contendo o resultado das equações.
     * @return Vetor solução contendo os valores de x1, x2, ..., xn.
     */
    public static double[] lsolve(double[][] A, double[] b) {

        int n = b.length;
        double EPSILON = 1e-10;

        for (int p = 0; p < n; p++) {

            // Achando o elemento pivô e o trocando.
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }

            // Trocando as linhas da matriz.
            double[] aux = A[p];
            A[p] = A[max];
            A[max] = aux;

            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            // Verificando se a matriz é singular ou quase singular.
            if (Math.abs(A[p][p]) <= EPSILON) {
                throw new ArithmeticException("Matriz é singular ou quase singular.\n");
            }

            // Pivoteamento com A e b.
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // Substituindo de volta.
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }

        return x;
    }

}
