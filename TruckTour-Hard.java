import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the truckTour function below.
     */
    static int truckTour(int[][] petrolpumps) {
        //since the algorithm resets the starting station when it has a negative fuel tank, I used
        //current to keep track of the current starting position of the tour.
        //the fuel is the amount of fuel the truck has, which I calculated at each station by subtracting the distance needed to travel from the fuel gained at the station.
        int current = 0, fuel = 0, deficit = 0;

        //I used a for loop rather than a queue since I realized that I do not need to loop around. 
        //The drawback of doing this rather than a queue-based implementation is that I need to find a way to figure out if I can loop around. I solved this problem through a variable called deficit, which keeps track of the amount of fuel loss so far. 
        //Once I visit the last station, if I restarted somewhere in the middle, I can use the deficit to figure out if I am able to finish the tour. This accounts for the case where no solution exists.
        
        //iterating over the stations: O(n) runtime
        for (int i = 0; i < petrolpumps.length; i++) {
            //calculating the fuel needed
            fuel += petrolpumps[i][0] - petrolpumps[i][1];
            //if the fuel is not enough to travel to the next station (when it is negative) I would have to start over. I also keep track of the current fuel as a deficit.
            if (fuel < 0) {
                //resets the fuel tank from starting the tour over again
                fuel = 0;
                //adds the negative fuel as a deficit
                deficit += fuel;
                //starts at the next station from the one that didn't work.
                current = i+1;
            } 
        }

        //checks if a solution exists (if the current fuel is enough to overcome the deficit that was acquired before starting over), if it's not return -1.
        if ((fuel + deficit) < 0) {
            return -1;
        }

        //if it is then return the start.
        return current;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] petrolpumps = new int[n][2];

        for (int petrolpumpsRowItr = 0; petrolpumpsRowItr < n; petrolpumpsRowItr++) {
            String[] petrolpumpsRowItems = scanner.nextLine().split(" ");

            for (int petrolpumpsColumnItr = 0; petrolpumpsColumnItr < 2; petrolpumpsColumnItr++) {
                int petrolpumpsItem = Integer.parseInt(petrolpumpsRowItems[petrolpumpsColumnItr].trim());
                petrolpumps[petrolpumpsRowItr][petrolpumpsColumnItr] = petrolpumpsItem;
            }
        }

        int result = truckTour(petrolpumps);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
