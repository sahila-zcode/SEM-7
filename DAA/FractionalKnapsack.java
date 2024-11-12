import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    double weight;
    double value;
    double valuePerWeight;

    // Constructor to initialize item with weight, value, and value-per-weight ratio
    public Item(double weight, double value) {
        this.weight = weight;
        this.value = value;
        this.valuePerWeight = value / weight;
    }
}

public class FractionalKnapsack {

    public static double getMaxValue(Item[] items, double capacity) {
        // Sort items by value per weight in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return Double.compare(o2.valuePerWeight, o1.valuePerWeight);
            }
        });

        double totalValue = 0;

        for (Item item : items) {
            if (capacity == 0) break; // Stop if the knapsack is full

            // Take as much as possible from the item
            if (item.weight <= capacity) {
                // If the item can fit entirely, add its whole value
                totalValue += item.value;
                capacity -= item.weight;
            } else {
                // If the item can't fit entirely, take the fraction of it
                totalValue += item.valuePerWeight * capacity;
                capacity = 0; // Knapsack is full now
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];
        System.out.println("Enter weight and value for each item:");

        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " - Weight: ");
            double weight = sc.nextDouble();
            System.out.print("Item " + (i + 1) + " - Value: ");
            double value = sc.nextDouble();
            items[i] = new Item(weight, value);
        }

        System.out.print("Enter the capacity of the knapsack: ");
        double capacity = sc.nextDouble();

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value in the knapsack = " + maxValue);

        sc.close();
    }
}
// output:

/*Enter the number of items: 3
Enter weight and value for each item:
Item 1 - Weight: 10
Item 1 - Value: 60
Item 2 - Weight: 20
Item 2 - Value: 100
Item 3 - Weight: 30
Item 3 - Value: 120
Enter the capacity of the knapsack: 50
Maximum value in the knapsack = 240.0 */
