/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trufflepath;

import java.util.Arrays;
import static java.lang.Math.max;

/**
 *
 * @author Nada Boukhari
 */
public class TrufflePath {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] f
                = {
                    {1, 5, 1, 5, 1, 5},
                    {3, 3, 2, 3, 3, 4},
                    {2, 3, 4, 4, 4, 2},
                    {2, 2, 3, 2, 2, 3},
                    {2, 2, 4, 3, 4, 2},
                    {4, 4, 4, 4, 2, 3}};

        int truffleYield;

        truffleYield = TruffleHarvest(f);
        /*for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print("\t" + field[i][j]);
            }
            System.out.print("\n");
        }*/
        System.out.println(truffleYield);
    }

    static int TruffleHarvest(int field[][]) {
        // To find max val in first row 
        int[][] y = new int[field.length][field[0].length];
        int[][] path = new int[field.length][field[0].length];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                y[i][j] = field[i][j];
            }
        }
        int[] temp = new int[field.length];
        int yes = 0;
        int s = 0;
        int truffle = -1;
        int k = 0;
        for (int i = 0; i < y[0].length; i++) {
            if (y[0][i] >= truffle) {
                truffle = y[0][i];
                temp[0] = i;

            }
            path[0][i] = i;
        }
        //System.out.println("[1" + "," + (k+1) + "] - " + truffle);

        for (int i = 1; i < y.length; i++) {
            truffle = -1;
            for (int j = 0; j < y[0].length; j++) {
                // When all paths are possible 
                if (j > 0 && j < y[0].length - 1) {
                    if (y[i - 1][j - 1] > y[i - 1][j + 1]) {
                        path[i][j] = j - 1;
                        yes = field[i - 1][j - 1];
                    } else {
                        if (y[i - 1][j] > y[i - 1][j + 1]) {
                            path[i][j] = j;
                            yes = field[i - 1][j];
                        } else {
                            path[i][j] = j + 1;
                            yes = field[i - 1][j + 1];
                        }
                    }
                    y[i][j] = max(y[i - 1][j], max(y[i - 1][j - 1], y[i - 1][j + 1])) + y[i][j];
                    //path[i][j] = j;
                } // When diagonal right is not possible 
                else if (j > 0) {
                    if (y[i - 1][j] > y[i - 1][j - 1]) {
                        path[i][j] = j;
                        yes = field[i - 1][j];
                    } else {
                        path[i][j] = j - 1;
                        yes = field[i - 1][j - 1];
                    }
                    y[i][j] = max(y[i - 1][j], y[i - 1][j - 1]) + y[i][j];
                    //path[i][j] = j;
                } // When diagonal left is not possible 
                else if (j < y[0].length - 1) {
                    if (y[i - 1][j] > y[i - 1][j + 1]) {
                        path[i][j] = j;
                        yes = field[i - 1][j];
                    } else {
                        path[i][j] = j + 1;
                        yes = field[i - 1][j + 1];
                    }
                    y[i][j] = max(y[i - 1][j], y[i - 1][j + 1]) + y[i][j];
                    //path[i][j] = j;
                }
                if (y[i][j] >= truffle) {
                    truffle = y[i][j];
                    temp[i] = j;
                }
                truffle = max(y[i][j], truffle);
            }

            //System.out.println("[" + (i + 1) + "," + (k + 1) + "] - " + field[i-1][s]);
            //System.out.println(res);
        }
        int var = temp.length;
        int crap = temp[var - 1];
        int crap2 = 0;
        int position = 0;
        int holder = 0;
        int holder2 = 0;

        for (int i = 5; i >0; i--) {
            if (i == 5) {
                System.out.println("[" + (i + 1) + "," + (crap + 1) + "] - ");
            } else {
                holder = path[i+1][crap];
                System.out.println("[" + (i + 1) + "," + (holder+1) + "] - ");
                crap = holder;
            }
        }
        
        System.out.println("[1," + (temp[0]+1) + "] - ");
        
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y[i].length; j++) {
                System.out.print("\t" + y[i][j]);
            }
            System.out.print("\n");
        }

        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print("\t" + field[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print("\t" + path[i][j]);
            }
            System.out.print("\n");
        }

        return truffle;
    }

}
