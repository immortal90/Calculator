package com.shpp.p2p.cs.ikopach.assignment11;


import java.util.HashMap;
import java.util.Map;

/**
 * The `MathArgumentsParser` class provides methods for parsing command-line arguments
 * and creating a map of variable values.
 */
public class MathArgumentsParser {
    /**
     * Parses command-line arguments and creates a map of variable values.
     *
     * @param args Command-line arguments containing variable values.
     * @return A map of variable names and their corresponding values.
     */
    public static Map<String, Double> parseArguments(String[] args) {
        // Create an empty map to store variable-value pairs
        Map<String, Double> values = new HashMap<>();

        // Iterate over all the command-line arguments passed
        for (String arg : args) {
            // Split each argument into variable name and value
            String[] parts = arg.split("=");

            // Check if the argument is well-formed with an equal sign
            if (parts.length == 2) {
                // Extract variable name and value from the split parts
                String variable = parts[0].trim();
                String value = parts[1].trim();

                // Replace commas with dots in the value to handle numeric formats
                value = value.replace(",", ".");

                // Handle cases where the decimal point is at the beginning or end
                if (value.matches("-?\\.\\d+")) {
                    value = "0" + value;
                } else if (value.matches("-?\\d+\\.")) {
                    value = value + "0";
                }

                // Check if the value is a valid numeric format
                if (value.matches("-?\\d+(\\.\\d+)?")) {
                    // Add the variable and its value to the map
                    values.put(variable, Double.parseDouble(value));
                }
            }
        }

        // Return the obtained map of variables and their values.
        return values;
    }
}