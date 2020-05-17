/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castletravel;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Curveball
 */
public class CastleTravel {

    public static void main(String[] args) {
        int[] castles = {0, 6, 9, 10, 11, 17, 18, 23, 25, 31, 36, 40, 44, 50};
        int[] others = {0, 5, 10, 15, 20, 25, 30, 35};
        int[] test = {0, 1, 2, 3, 4, 5, 10, 12, 15};
        int travel = 8;
        int[][] lookup = new int[travel + 1][test.length + 1];

        int start;
        int end;
        int[] admitted = new int[travel];

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < lookup.length; i++) {
            Arrays.fill(lookup[i], -1);
        }

        for (int i = 0; i < travel; i++) {
            lookup[i + 1][0] = i + 1;
        }

        for (int i = 0; i < test.length; i++) {
            lookup[0][i + 1] = test[i];
        }

        //System.out.println(Arrays.toString(admitted));

        
        int cnt;
        start = lookup[0][1];
        int k = 0;
        int t;
        int l;

        for (int i = 0; i < test.length; i++) {
            k = i;
            start = lookup[0][k + 1];
            cnt = 0;

            while (true) {
                if (k == 0) {
                    break;
                }
                if ((lookup[0][i + 1] - lookup[0][k]) <= travel) {
                    list.add(lookup[0][k]);
                    //admitted[cnt] = lookup[0][k + 1];
                    //cnt++;
                    k--;
                } else {
                    break;
                }
            }
            if (i != 0) {
                System.out.println(lookup[0][i + 1] + "-->" + list);
                for (int j = 0; j < list.size(); j++) {
                    l = lookup[0][i + 1] - list.get(j);
                    lookup[l][i + 1] = (int) Math.pow(((lookup[0][i + 1] - list.get(j)) - travel), 2);
                }
            }

            
            list.clear();
        }
        
        for (int i = 0; i < lookup.length; i++) {
            System.out.println(Arrays.toString(lookup[i]));
        }

        /*for (int i = 0; i < castles.length; i++) {
            k = i;
            start = lookup[0][k + 1];
            cnt = 0;

            while (true) {
                if (k == castles.length) {
                    break;
                }
                if ((lookup[0][k + 1] - start) <= 7) {
                    list.add(lookup[0][k + 1]);
                    //admitted[cnt] = lookup[0][k + 1];
                    //cnt++;
                    k++;
                } else {
                    break;
                }
            }
            for (int j = 0; j < cnt; j++) {
                // while loop to fill the table 
            }

            System.out.println(list);
            list.clear();
        }*/
    }

}
