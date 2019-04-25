package data;

import java.io.*;
import java.util.ArrayList;

public class LobattoComparer {

    /**
     * Invoca um programa externo.
     * @param stdout True para redirecionar o stdout. False para stderr
     * @param args Programa + argumentos
     * @return Array de strings contendo a saída do programa para cada linha.
     * @throws IOException 
     */
    public static String[] callProgram(boolean stdout, String... args) throws IOException {
        Process process = new ProcessBuilder(args).start();
        InputStream is = stdout ? process.getInputStream() : process.getErrorStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

        ArrayList<String> strs = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            strs.add(line);
        }
        return strs.toArray(new String[strs.size()]);
    }

    
    /**
     * Invoca um processo do Sympy para calcular a integral por Lobatto.
     * @param func Equação em função de "x" (não usar 'f(x) = ', apenas coloque a equação)
     * @param n Número de pontos
     * @return String contendo o valor numerico do método (ou um erro assustador).
     * @throws IOException 
     */
    public static String getSymPyLobatto(String func, int n) throws IOException {
        return getSymPyLobatto(func, n, true);
    }

    /**
     * Invoca um processo do Python + Sympy para calcular a integral por Lobatto.
     * @param func Equação em função de "x" (não usar 'f(x) = ', apenas coloque a equação)
     * @param n Número de pontos
     * @param stdout True para redirecionar o stdout. False para stderr (útil em debug).
     * @return String contendo o valor numerico do método (ou um erro assustador).
     * @throws IOException 
     */
    public static String getSymPyLobatto(String func, int n, boolean stdout) throws IOException {
        return callProgram(stdout, "python3", "extern/lobatto.py", func, String.valueOf(n))[0];
    }

    
    /**
     * Invoca um processo do Python + Sympy para calcular a integral simbolica.
     * @param func Equação em função de "x" (não usar 'f(x) = ', apenas coloque a equação)
     * @return String contendo o valor numerico da integral (ou um erro assustador).
     * @throws IOException 
     */
    public static String getSymPySymbolic(String func) throws IOException {
        return getSymPySymbolic(func, true);
    }

    /**
     * Invoca um processo do Python + Sympy para calcular a integral simbolica.
     * @param func Equação em função de "x" (não usar 'f(x) = ', apenas coloque a equação)
     * @return String contendo o valor numerico da integral (ou um erro assustador).
     * @param stdout True para redirecionar o stdout. False para stderr (útil em debug).
     * @throws IOException 
     */
    public static String getSymPySymbolic(String func, boolean stdout) throws IOException {
        return callProgram(stdout, "python3", "extern/symbolic.py", func)[0];
    }
}
