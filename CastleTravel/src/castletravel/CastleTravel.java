/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castletravel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Curveball
 */
public class CastleTravel {

    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws FileNotFoundException {
        //int[] castles = {0, 6, 9, 10, 11, 17, 18, 23, 25, 31, 36, 40, 44, 50};
        //int[] others = {0, 5, 10, 15, 20, 25, 30, 35};
        //int[] test = {0, 1, 2, 3, 4, 5, 10, 12, 15};
        //int travel = 4;
        //int[][] lookup = new int[travel + 1][test.length + 1];
        String[] arr;

        File input = null;
        if (args.length == 1) {
            input = new File(args[0]);
        } else {
            System.err.println("Invalid input");
            System.exit(0);
        }

        int pos = 0;
        int temp = INF;

        Scanner sc = new Scanner(input);
        int travel = Integer.parseInt(sc.nextLine());

        arr = sc.nextLine().split("\t");
        int[] castles = new int[arr.length];
        for (int i = 0; i < castles.length; i++) {
            castles[i] = Integer.parseInt(arr[i]);
        }

        int[] minPenalty = new int[castles.length];
        minPenalty[0] = 0;

        for (int i = 1; i < minPenalty.length; i++) {
            for (int k = 0; k < i; k++) {
                if (temp > minPenalty[k] + (int) Math.pow((travel - (castles[i] - castles[k])), 2)) {
                    temp = minPenalty[k] + (int) Math.pow((travel - (castles[i] - castles[k])), 2);
                    pos = k;
                }
            }
            minPenalty[i] = temp;
            System.out.println("got to " + castles[i] + " from " + castles[pos]);
            temp = INF;
        }
        System.out.println("Castles :" + Arrays.toString(castles));
        System.out.println("Penalties :" + Arrays.toString(minPenalty));
        //System.out.println("Minimized :" +Arrays.toString(minimizedBy));

        /*int end;
        int[] admitted = new int[travel];
        
        int start =0;
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
