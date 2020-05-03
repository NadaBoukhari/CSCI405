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

        int[] s = {1,2,2};
        //System.out.println(s[3]);
        int[] temp = new int[s.length];
        int index = 0;
        int cnt = 0;
        int m=0;
        LinkedList<Integer> list = new LinkedList<Integer>();
        solve(s, index, temp, cnt,list);
        
        int evenSequences = (int) ((pow(2,s.length) -1) - list.size());
        System.out.println("\nNumber of sequences that sum to an odd number: "+list.size());
        System.out.println("Number of sequences that sum to an even number: "+evenSequences);
    


    }

    private static void solve(int[] arrInput, int index, int[] temp, int cnt, LinkedList<Integer> l) {
        if (index == arrInput.length) {
            String result = "";
            int r = 0;
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] == 1) {
                    result += arrInput[i] + " ";
                    r += arrInput[i];
                    /*if (r % 2 != 0) {
                        cnt++;
                    }*/
                }
            }
            if(result ==""){
                return;
            }
            if (r % 2 != 0) {
                l.add(1);
                System.out.println(result);
            } else {
                System.out.println(result + " sum = " + r + " even");
            }
            return;
        }
        //set the current index bit and solve it recursively
        temp[index] = 1;
        solve(arrInput, index + 1, temp, cnt,l);
        //unset the current index bit and solve it recursively
        temp[index] = 0;
        solve(arrInput, index + 1, temp, cnt,l);
    }
}
