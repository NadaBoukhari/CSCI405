package castletravel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 *
 * @author Nada Boukhari
 */
public class CastleTravel {

    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws FileNotFoundException {

        // Get the file
        File input = null;
        if (args.length == 1) {
            input = new File(args[0]);
        } else {
            System.err.println("Invalid input");
            System.exit(0);
        }

        //convert file data to miles and castles (array of castle positions)
        String[] str;
        Scanner sc = new Scanner(input);
        int m = Integer.parseInt(sc.nextLine());
        str = sc.nextLine().split("\t");
        int[] c = new int[str.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = Integer.parseInt(str[i]);
        }
        //get index of the last castle position, i.e the final destination
        int end = c.length - 1;

        //call Journey method
        Journey(c, m, end);

    }

    public static void Journey(int[] castles, int miles, int end) {
        //variable declarations
        int pos = 0;
        int minPenalty = INF;
        int stopIndex;
        int[] Penalty = new int[castles.length];
        int[] minimizedBy = new int[castles.length];
        Penalty[0] = 0;

        /////////Main Algorithm/////////////
        //loop through the all the castle positions
        for (int i = 1; i < Penalty.length; i++) {
            //for every new castle position, check the penalty between
            //the castle at position 0,1,2... and the current i position until i is reached
            for (int j = 0; j < i; j++) {
                //if the new position has a smaller penalty than the previous one, update the penalty (get the minimim penalty)
                if (minPenalty > Penalty[j] + (int) Math.pow(((castles[i] - castles[j]) - miles), 2)) {
                    minPenalty = Penalty[j] + (int) Math.pow(((castles[i] - castles[j]) - miles), 2);
                    //store the position of the newest castle to yield a small penalty
                    pos = j;
                }
            }
            //store the positions that yield the smallest penalty for each castle
            minimizedBy[i] = pos;
            //store the minimum penalty for each castle
            Penalty[i] = minPenalty;
            //update the minimum penalty to loop again
            minPenalty = INF;
        }

        //create a stack and push the last castle
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(castles[end]);

        //As long as the castle at position 0 isn't reached, 
        //start from the last castle and travel back to the
        //first depending on which stops yield the smallest penalty
        while (end > 0) {
            stopIndex = minimizedBy[end];
            //push castles in which to stop in stack
            stack.push(castles[stopIndex]);
            end = stopIndex;
        }

        System.out.print("Stop at:\t");

        //as long as the stack still has castles, pop them
        //to reveal the path that yields the smallest penalty
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "\t");
        }

        //retrieve the penalty for the overall journey
        System.out.println("\nThe penalty is:\t" + Penalty[castles.length - 1]);
    }

}
