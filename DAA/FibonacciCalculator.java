import java.util.Scanner;

public class FibonacciCalculator {

    // Non-recursive method to calculate Fibonacci numbers
    public static int fibonacciNonRecursive(int n) {
        if (n <= 1) return n;

        int a = 0, b = 1, c = 1;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    // Recursive method to calculate Fibonacci numbers
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Display Fibonacci series using non-recursive method
    public static void displayFibonacciSeriesNonRecursive(int n) {
        System.out.print("Non-recursive Fibonacci Series up to " + n + ": ");
        if (n >= 0) System.out.print("0");
        if (n >= 1) System.out.print(", 1");

        int a = 0, b = 1, c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            System.out.print(", " + c);
            a = b;
            b = c;
        }
        System.out.println();
    }

    // Display Fibonacci series using recursive method
    public static void displayFibonacciSeriesRecursive(int n) {
        System.out.print("Recursive Fibonacci Series up to " + n + ": ");
        for (int i = 0; i <= n; i++) {
            System.out.print(fibonacciRecursive(i));
            if (i < n) System.out.print(", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number to calculate Fibonacci: ");
        int n = scanner.nextInt();

        // Non-recursive Fibonacci calculation and series
        long startTime = System.nanoTime();
        int resultNonRecursive = fibonacciNonRecursive(n);
        long endTime = System.nanoTime();
        System.out.println("Non-recursive Fibonacci of " + n + " = " + resultNonRecursive);
        System.out.println("Time taken (non-recursive): " + (endTime - startTime) + " ns");
        displayFibonacciSeriesNonRecursive(n);

        // Recursive Fibonacci calculation and series
        startTime = System.nanoTime();
        int resultRecursive = fibonacciRecursive(n);
        endTime = System.nanoTime();
        System.out.println("Recursive Fibonacci of " + n + " = " + resultRecursive);
        System.out.println("Time taken (recursive): " + (endTime - startTime) + " ns");
        displayFibonacciSeriesRecursive(n);

        scanner.close();
    }
}

// output:

/*Enter a number to calculate Fibonacci: 5
Non-recursive Fibonacci of 5 = 5
Time taken (non-recursive): [some time in ns]
Non-recursive Fibonacci Series up to 5: 0, 1, 1, 2, 3, 5
Recursive Fibonacci of 5 = 5
Time taken (recursive): [some time in ns]
Recursive Fibonacci Series up to 5: 0, 1, 1, 2, 3, 5
*/
