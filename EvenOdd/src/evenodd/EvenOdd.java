/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenodd;

import static java.lang.Math.pow;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nada Boukhari
 */
public class EvenOdd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[] arr = {3, 2, 4, 7, 3, 4, 5};
        int[] temp = new int[arr.length];
        int start = 0;
        LinkedList<Integer> list = new LinkedList<Integer>();
        
        evenOdd(arr, start, temp, list);

        int evenSequences = (int) ((pow(2, arr.length) - 1) - list.size());
        System.out.println("\nNumber of sequences that sum to an odd number: " + list.size());
        System.out.println("Number of sequences that sum to an even number: " + evenSequences);

    }

    private static void evenOdd(int[] a, int s, int[] t, LinkedList<Integer> l) {
        if (s == a.length) {
            int sum = 0;
            for (int i = 0; i < t.length; i++) {
                if (t[i] == 1) {
                    sum += a[i];
                }
            }
            if (sum % 2 != 0) {
                l.add(1);
            }
            return;
        }
        t[s] = 1;
        evenOdd(a, s + 1, t, l);
        t[s] = 0;
        evenOdd(a, s + 1, t, l);
    }
}
