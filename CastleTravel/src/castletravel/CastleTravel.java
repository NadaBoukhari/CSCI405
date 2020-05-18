/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castletravel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Curveball
 */
public class CastleTravel {

    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws FileNotFoundException {
        
        File input = null;
        if (args.length == 1) {
            input = new File(args[0]);
        } else {
            System.err.println("Invalid input");
            System.exit(0);
        }

        String[] arr;
        Scanner sc = new Scanner(input);
        int t = Integer.parseInt(sc.nextLine());
        arr = sc.nextLine().split("\t");
        int[] c = new int[arr.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = Integer.parseInt(arr[i]);
        }
        
        int whereTo = c.length -1;
        
        Traveling(c,t,whereTo);

    }

    public static void Traveling(int[] castles, int travel,int whereTo) {
        int pos = 0;
        int temp = INF;
        int[] minPenalty = new int[castles.length];
        int[] minimizedBy = new int[castles.length];
        minPenalty[0] = 0;

        for (int i = 1; i < minPenalty.length; i++) {
            for (int k = 0; k < i; k++) {
                if (temp > minPenalty[k] + (int) Math.pow((travel - (castles[i] - castles[k])), 2)) {
                    temp = minPenalty[k] + (int) Math.pow((travel - (castles[i] - castles[k])), 2);
                    pos = k;
                }
            }
            minimizedBy[i]=pos;
            minPenalty[i] = temp;
            temp = INF;
        }
        //System.out.println("Minimized :"+ Arrays.toString(minimizedBy));
        //System.out.println("Castles :" + Arrays.toString(castles));
        //System.out.println("Penalties :" + Arrays.toString(minPenalty));
        int kk;
        
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(castles[castles.length-1]);
        while(minPenalty[whereTo]>0){
            kk= minimizedBy[whereTo];
            stack.push(castles[kk]);    
            whereTo = kk;
        }       
        System.out.print("You should stop at:\t");
        while(!stack.isEmpty()){
            System.out.print(stack.pop()+"\t");
        }
        System.out.println("\nThe penalty is:\t"+minPenalty[castles.length-1]);
    }

}
