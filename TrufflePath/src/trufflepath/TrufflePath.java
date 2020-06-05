/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trufflepath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import static java.lang.Math.max;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 *
 * @author Nada Boukhari
 */
public class TrufflePath {

    static int INF = Integer.MIN_VALUE;

    public static class TruffleInfo {

        public int row;
        public int column;
        public int truffles;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*int[][] c = new int[7][20];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                c[0][j] = 100;
            }
            //c[i][0] = 0;
        }
        c[c.length - 1][0] = 10;
        c[2][1] = 400;
        c[3][1] = 400;
        c[4][1] = 400;
        c[3][8] = 50000;
        c[c.length - 1][10] = 1000;*/

 /*File input = null;
        if (args.length == 1) {
            input = new File(args[0]);
        } else {
            System.err.println("Invalid input");
            System.exit(0);
        }*/
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\boukh\\Desktop\\field.txt"));
        br.mark(0);
        int rows = 0;
        int columns = 0;
        String[] n;
        while (br.ready()) {
            String line = br.readLine();
            n = line.split("\t");
            rows++;
            columns = n.length;
        }

        BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\boukh\\Desktop\\field.txt"));
        int[][] field = new int[rows][columns];
        int k = 0;
        while (br2.ready()) {
            String line = br2.readLine();
            String[] tokens = line.split("\t");
            // now put each in 2D array
            for (int j = 0; j < tokens.length; j++) {
                field[k][j] = Integer.parseInt(tokens[j]);
            }
            k++;
        }

        /*for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print("\t" + field[i][j]);
            }
            System.out.print("\n");
        }*/
        int truffles = TruffleHarvest(field);
        System.out.println(truffles + " truffles");
    }

    static int TruffleHarvest(int field[][]) {
        int[][] currentYield = new int[field.length][field[0].length];
        int[][] path = new int[field.length][field[0].length];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                currentYield[i][j] = field[i][j];
            }
        }
        //int[] temp = new int[field.length];
        int maxTruffles = INF;
        int lastRowMaxIndex= 0;

        for (int i = 1; i < currentYield.length; i++) {
            maxTruffles = INF;
            for (int j = 0; j < currentYield[0].length; j++) {
                // When all paths are possible 
                if (j > 0 && j < currentYield[0].length - 1) {
                    currentYield[i][j] = max(currentYield[i - 1][j], max(currentYield[i - 1][j - 1], currentYield[i - 1][j + 1])) + currentYield[i][j];
                    if (currentYield[i][j] == currentYield[i - 1][j] + field[i][j]) {
                        path[i][j] = j;
                    } else if (currentYield[i][j] == currentYield[i - 1][j - 1] + field[i][j]) {
                        path[i][j] = j - 1;
                    } else if (currentYield[i][j] == currentYield[i - 1][j + 1] + field[i][j]) {
                        path[i][j] = j + 1;
                    }
                } // When diagonal right is not possible 
                else if (j > 0) {
                    currentYield[i][j] = max(currentYield[i - 1][j], currentYield[i - 1][j - 1]) + currentYield[i][j];
                    if (currentYield[i][j] == currentYield[i - 1][j] + field[i][j]) {
                        path[i][j] = j;
                    } else if (currentYield[i][j] == currentYield[i - 1][j - 1] + field[i][j]) {
                        path[i][j] = j - 1;
                    }
                } // When diagonal left is not possible 
                else if (j < currentYield[0].length - 1) {
                    currentYield[i][j] = max(currentYield[i - 1][j], currentYield[i - 1][j + 1]) + currentYield[i][j];
                    if (currentYield[i][j] == currentYield[i - 1][j] + field[i][j]) {
                        path[i][j] = j;
                    } else if (currentYield[i][j] == currentYield[i - 1][j + 1] + field[i][j]) {
                        path[i][j] = j + 1;
                    }
                }
                if (currentYield[i][j] >= maxTruffles) {
                    maxTruffles = currentYield[i][j];
                    lastRowMaxIndex = j;
                }
                maxTruffles = max(currentYield[i][j], maxTruffles);
            }
        }
        //int var = temp.length;
        //int crap = temp[var - 1];
        int pathOrigin = 0;
        Deque<TruffleInfo> stack = new ArrayDeque<>();

        for (int i = currentYield.length - 1; i >= 0; i--) {
            TruffleInfo harvest = new TruffleInfo();
            if (i == currentYield.length - 1) {
                //harvest.row = i;
                harvest.column = lastRowMaxIndex;
                harvest.truffles = field[i][lastRowMaxIndex];
                //stack.push(harvest);
            } else {
                pathOrigin = path[i + 1][lastRowMaxIndex];
                //harvest.row = i;
                harvest.column = pathOrigin;
                harvest.truffles = field[i][pathOrigin];
                lastRowMaxIndex = pathOrigin;
                //stack.push(harvest);
            }
            harvest.row = i;
            stack.push(harvest);
        }

        TruffleInfo t = new TruffleInfo();
        while (!stack.isEmpty()) {
            t = stack.pop();
            System.out.println("[" + (t.row + 1) + "," + (t.column + 1) + "]-" + t.truffles);
        }

        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < currentYield.length; i++) {
            for (int j = 0; j < currentYield[i].length; j++) {
                System.out.print("\t" + currentYield[i][j]);
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
        return maxTruffles;
    }

}
