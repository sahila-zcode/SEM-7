import java.util.*;

public class QuickSortAnalysis {

    // Function for deterministic quick sort (choosing the first element as pivot)
    public static void quickSortDeterministic(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortDeterministic(arr, low, pi - 1);
            quickSortDeterministic(arr, pi + 1, high);
        }
    }

    // Function for randomized quick sort (choosing a random element as pivot)
    public static void quickSortRandomized(int[] arr, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(arr, low, high);
            quickSortRandomized(arr, low, pi - 1);
            quickSortRandomized(arr, pi + 1, high);
        }
    }

    // Function for partitioning the array (deterministic version)
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // Choosing the first element as the pivot
        int i = low + 1;
        for (int j = low + 1; j <= high; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, low, i - 1);
        return i - 1;
    }

    // Function for partitioning the array (randomized version)
    public static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int pivotIndex = low + rand.nextInt(high - low + 1); // Random pivot selection
        swap(arr, pivotIndex, low);
        return partition(arr, low, high);
    }

    // Function to swap two elements in the array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Function to print the array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Function to calculate time taken for sorting
    public static long timeSort(Runnable sortingMethod) {
        long startTime = System.nanoTime();
        sortingMethod.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking array input from the user
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr1[i] = arr2[i] = sc.nextInt();
        }

        // Running deterministic quicksort
        System.out.println("Performing Deterministic QuickSort...");
        long timeDeterministic = timeSort(() -> quickSortDeterministic(arr1, 0, arr1.length - 1));
        System.out.println("Sorted array (Deterministic):");
        printArray(arr1);
        System.out.println("Time taken (Deterministic): " + timeDeterministic + " nanoseconds");

        // Running randomized quicksort
        System.out.println("Performing Randomized QuickSort...");
        long timeRandomized = timeSort(() -> quickSortRandomized(arr2, 0, arr2.length - 1));
        System.out.println("Sorted array (Randomized):");
        printArray(arr2);
        System.out.println("Time taken (Randomized): " + timeRandomized + " nanoseconds");

        sc.close();
    }
}
/*Enter the number of elements in the array: 5
Enter the elements of the array:
12 7 9 5 11
Performing Deterministic QuickSort...
Sorted array (Deterministic):
5 7 9 11 12
Time taken (Deterministic): 11600 nanoseconds
Performing Randomized QuickSort...
Sorted array (Randomized):
5 7 9 11 12
Time taken (Randomized): 569300 nanoseconds*/