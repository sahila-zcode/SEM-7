import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

// Node class representing each character and its frequency in the Huffman Tree
class HuffmanNode {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = '-';
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}

// Comparator class for the PriorityQueue to sort nodes based on frequency
class HuffmanNodeComparator implements java.util.Comparator<HuffmanNode> {
    public int compare(HuffmanNode node1, HuffmanNode node2) {
        return node1.frequency - node2.frequency;
    }
}

public class HuffmanEncoding {

    // Generates Huffman Codes based on the Huffman Tree
    public static void generateCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) return;

        // Leaf node, add character and its code
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
            return;
        }

        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }

    // Builds the Huffman Tree and returns the root node
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencies) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(new HuffmanNodeComparator());

        // Create leaf nodes for each character and add them to the priority queue
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Build the Huffman Tree by combining nodes
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode combined = new HuffmanNode(left.frequency + right.frequency, left, right);
            priorityQueue.add(combined);
        }

        // Return the root of the Huffman Tree
        return priorityQueue.poll();
    }

    // Main method to execute Huffman Encoding
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to encode using Huffman Encoding: ");
        String text = scanner.nextLine();

        // Calculate the frequency of each character in the input string
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char ch : text.toCharArray()) {
            frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);
        }

        // Build the Huffman Tree
        HuffmanNode root = buildHuffmanTree(frequencies);

        // Generate Huffman Codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        // Display Huffman Codes
        System.out.println("Huffman Codes for each character:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Encode the input string using the Huffman Codes
        StringBuilder encodedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(ch));
        }

        // Display the encoded string
        System.out.println("Encoded text: " + encodedText.toString());

        scanner.close();
    }
}
// output

/* Enter a string to encode using Huffman Encoding: hello
Huffman Codes for each character:
h: 10
e: 110
l: 0
o: 111
Encoded text: 10110001110
 */