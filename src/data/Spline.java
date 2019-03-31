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

        for (int i = 1; i < X.length; i++) {
            h[i] = X[i] - X[i - 1];
        }

        return h;
    }

    public double[] CalMu(double[] H) {
        double[] mu = new double[H.length];
        mu[0] = 0;
        mu[H.length - 1] = 1;
        for (int i = 1; i < H.length - 1; i++) {
            mu[i] = H[i] / (H[i] + H[i + 1]);
        }

        return mu;
    }

    public double[] calLambda(double[] MU) {
        double[] lambda = new double[MU.length];

        lambda[0] = 0;
        for (int i = 1; i < MU.length; i++) {
            lambda[i] = 1 - MU[i];
        }

        return lambda;
    }

    public double[] calDD(double[] X, double[] Y, double d0, double dn) {
        double[] dd = new double[X.length];

        dd[0] = ((Y[1] - Y[0]) / (X[1] - X[0]) - d0) / (X[1] - X[0]);
        dd[X.length - 1] = (dn - (Y[X.length - 1] - Y[X.length - 2]) / (X[X.length - 1] - X[X.length - 2])) / (X[X.length - 1] - X[X.length - 2]);

        for (int i = 1; i < X.length - 1; i++) {
            dd[i] = ((Y[i + 1] - Y[i]) / (X[i + 1] - X[i]) - (Y[i] - Y[i - 1]) / (X[i] - X[i - 1])) / (X[i + 1] - X[i - 1]);
        }

        return dd;
    }

    public double[] calM(double[] mu, double[] lambda, double[] dd) {
        double[] m = new double[mu.length];

        m[0] = (dd[0] * 6) / 2;
        m[mu.length - 1] = (dd[mu.length - 1] * 6) / 2;
        System.out.println("as" + m[0] + " " + m[mu.length - 1]);
        return m;
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
