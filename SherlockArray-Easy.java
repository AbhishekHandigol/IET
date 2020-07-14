import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the balancedSums function below.
    static String balancedSums(List<Integer> arr) {
        //initialize lsum and rsum to 0. 
        //lsum keeps track of the sum from the left end of the list to index i (excluding i)
        //rsum keeps track of the sum of the list from index i+1 to the end.
        int lsum = 0, rsum = 0;

        //find sum of entire list: O(n)
        for (int j : arr) {
            rsum += j;
        }

        //iterates through all the elements in the list: O(n)
        for (int i = 0; i < arr.size(); i++) {
             //since the right side sum excludes i, important to subtract arr[i] from the sum (this also works since i moves forward from left to right)
             rsum -= arr.get(i); 

             //checks if right subarray is equal to left subarray sum 
             if (lsum == rsum) {
                return "YES";
             } 

             //adds arr[i] to lsum to account for index i increasing by 1
             lsum += arr.get(i);
        }

        //if the for loop is over that means it went through all the elements
        //and lsum was not found to be equal to rsum, so false is returned
        return "NO";
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                String result = balancedSums(arr);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
