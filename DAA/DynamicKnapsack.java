import java.util.Scanner;

public class DynamicKnapsack {

    // Function to solve the 0-1 Knapsack problem using Dynamic Programming
    public static int knapSack(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n + 1][W + 1];  // DP table

        // Building the DP table in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;  // Base case: no items or weight capacity is 0
                } else if (wt[i - 1] <= w) {
                    // If the item can be included in the knapsack
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } else {
                    // If the item cannot be included in the knapsack
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];  // The last cell of the table will contain the maximum value
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking input from the user
        System.out.println("Enter the number of items:");
        int n = sc.nextInt();

        System.out.println("Enter the capacity of the knapsack:");
        int W = sc.nextInt();

        int[] val = new int[n];
        int[] wt = new int[n];

        System.out.println("Enter the values of the items:");
        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
        }

        System.out.println("Enter the weights of the items:");
        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }

        // Function call to calculate the maximum value
        int result = knapSack(W, wt, val, n);

        // Output the result
        System.out.println("Maximum value that can be obtained: " + result);

        sc.close();
    }
}
// output:

/*Enter the number of items:
4
Enter the capacity of the knapsack:
50
Enter the values of the items:
60 100 120 140
Enter the weights of the items:
10 20 30 40
Maximum value that can be obtained: 220*/