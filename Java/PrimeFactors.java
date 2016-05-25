import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author: Andrew Jarombek
 * Date: 5/25/2016
 * Prime Factorization - Have the user enter a number and find all Prime Factors (if there are any) and display them.
 * [CMD] This Program uses the Command Line.
 */
public class PrimeFactors {

    /**
     * Determine all of the prime factors of a number
     * @param num any integer
     * @return all of its prime factors
     */
    public static Object[] primeFactors(int num) {
        ArrayList<Integer> pFactors = new ArrayList<>();
        ArrayList<Integer> results = new ArrayList<>();

        if (num <= 1) return new Integer[0];

        // Get a list of all the prime factor options
        for (int i=2; i <= num/2; i++) {
            if (num % i == 0 && isPrime(i))
                pFactors.add(i);
        }

        // Find how many instances of each prime factor exists
        while (!pFactors.isEmpty() && num > 1) {
            for (Integer pFactor : pFactors) {
                boolean exhausted = false;
                while (!exhausted) {
                    if (num % pFactor == 0) {
                        num /= pFactor;
                        results.add(pFactor);
                    } else {
                        exhausted = true;
                    }
                }
            }
        }
        // return all instances
        return results.toArray();
    }

    /**
     * Helper function to determine if a number is prime
     * @param num any integer
     * @return if it is prime
     */
    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        } else if (num == 2) {
            return true;
        } else {
            for (int i = 2; i <= num / 2; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);
        System.out.println("The Prime Factors of " + number + " are: " + Arrays.toString(primeFactors(number)));
    }
}
