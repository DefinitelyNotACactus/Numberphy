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
public class Teste {
    public static void main(String[] args) {
        double[] limits = {2.3, 3.0};
        System.out.println(Lobatto.changeLimits(limits, "x^3 + x + 5"));
    }
}
