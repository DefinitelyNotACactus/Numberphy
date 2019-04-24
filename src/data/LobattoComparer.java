package data;

import java.io.*;
import java.util.ArrayList;

public class LobattoComparer {

    public static String[] callProgram(String... args) throws IOException {
        Process process = new ProcessBuilder(args).start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

        ArrayList<String> strs = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            strs.add(line);
        }
        return strs.toArray(new String[strs.size()]);
    }

    public static double getSymPyLobatto(String func, int n) throws IOException {
        return Double.parseDouble(callProgram("python3", "lobatto.py", func, String.valueOf(n))[0]);
    }

    public static double getSymPySymbolic(String func) throws IOException {
        return Double.parseDouble(callProgram("python3", "symbolic.py", func)[0]);
    }
}
