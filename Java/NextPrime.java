import java.util.Scanner;

/**
 * Author: Andrew Jarombek
 * Date: 5/27/2016
 * Next Prime Number - Have the program find prime numbers until the user chooses to stop asking for the next one.
 */
public class NextPrime {

    // Keep track of what the last prime number found/searched for was
    private static int location = 2;

    /**
     * Recursively checks for the next prime number
     * @param num a number to be checked if it is prime
     * @return a prime number
     */
    private static int nextPrime(Integer num) {
        // Update the location to be checked next
        location++;
        if (num == 2) {
            return num;
        } else {
            for (int i = 2; i <= num / 2; i++) {
                if (num % i == 0) {
                    // If this number is not prime, check if number+1 is
                    return nextPrime(location);
                }
            }
            return num;
        }
    }

    /**
     * Program that generates prime numbers in order by the users command
     */
    public static void primeGenerator() {
        System.out.println("Find next Prime? (Y/n): ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        // Check if the user wants another prime (Y) or not (n)
        // Also make sure no illegal commands are entered
        switch (answer) {
            case "Y":
                int nextP = nextPrime(location);
                System.out.println("The Next Prime Is: " + nextP);
                primeGenerator();
                break;
            case "n":
                System.out.println("Goodbye. ");
                break;
            default:
                System.out.println("ERROR: Invalid Command. ");
                primeGenerator();
                break;
        }
    }

    public static void main(String[] args) {
        primeGenerator();
    }
}
