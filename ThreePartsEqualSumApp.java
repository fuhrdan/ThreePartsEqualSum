import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreePartsEqualSumApp {

    public static void main(String[] args) {
        // Create the JFrame
        JFrame frame = new JFrame("Three Parts Equal Sum Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        // Create components
        JLabel instructions = new JLabel("Enter a comma-separated array of integers:");
        JTextField inputField = new JTextField();
        JButton checkButton = new JButton("Check");
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Add components to the frame
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(instructions, BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(checkButton, BorderLayout.CENTER);
        frame.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Add button action listener
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim();
                try {
                    // Parse the input into an array
                    String[] parts = input.split(",");
                    int[] arr = new int[parts.length];
                    for (int i = 0; i < parts.length; i++) {
                        arr[i] = Integer.parseInt(parts[i].trim());
                    }

                    // Check for three equal sum parts
                    String result = canThreePartsEqualSum(arr);
                    resultArea.setText(result);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Invalid input! Please enter a comma-separated array of integers.");
                }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }

    private static String canThreePartsEqualSum(int[] arr) {
        int totalSum = 0;

        // Calculate the total sum of the array
        for (int num : arr) {
            totalSum += num;
        }

        // If the total sum is not divisible by 3, return false
        if (totalSum % 3 != 0) {
            return "false: Cannot divide the array into three equal parts.";
        }

        int targetSum = totalSum / 3;
        int currentSum = 0;
        int partitions = 0;
        StringBuilder partitionDetails = new StringBuilder();
        StringBuilder currentPartition = new StringBuilder();

        // Iterate through the array to find partitions
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            currentPartition.append(arr[i]).append(" ");

            if (currentSum == targetSum) {
                partitions++;
                partitionDetails.append("Partition ").append(partitions).append(": [").append(currentPartition.toString().trim()).append("]\n");
                currentSum = 0;
                currentPartition = new StringBuilder();
            }

            if (partitions == 2 && i < arr.length - 1) {
                // If two partitions are found and elements remain, the rest is the third partition
                partitionDetails.append("Partition 3: [");
                for (int j = i + 1; j < arr.length; j++) {
                    partitionDetails.append(arr[j]).append(" ");
                }
                partitionDetails.append("]\n");
                return "true:\n" + partitionDetails.toString();
            }
        }

        return "false: Cannot divide the array into three equal parts.";
    }
}
