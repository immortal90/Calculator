package com.shpp.p2p.cs.ikopach.assignment11;

import java.util.Map;

/**
 * This class represents the main program for evaluating mathematical expressions, as
 * part of Assignment 11. It uses a command-line argument to take a mathematical
 * expression and variable values.
 *
 * @author Ihor Kopach
 * @version 2.0
 */
public class Assignment11Part1 {

    /**
     * The main method of the program, responsible for controlling the flow of expression evaluation.
     *
     * @param args - Command-line arguments or arguments passed through IDEA,
     *              containing a mathematical expression for evaluation and variables with values.
     */
    public static void main(String[] args) {
        // Check if a mathematical expression is provided as a command-line argument
        if (args.length < 1) {
            System.out.println("Please provide a mathematical expression");
            return;
        }
        evaluateMathExpression(args[0], args);
    }

    /**
     * Evaluates the given mathematical expression and handles exceptions if any occur.
     * Prints the result or an error message based on the evaluation outcome.
     *
     * @param expression The mathematical expression to evaluate.
     * @param args       Command-line arguments or arguments passed through IDEA, containing variable values.
     */
    static public void evaluateMathExpression(String expression, String[] args) {
        try {
            // Parse command-line arguments and create a map of variable values
            Map<String, Double> values = MathArgumentsParser.parseArguments(args);

            // Evaluate the expression and print the result
            MathExpressionEvaluator evaluator = new MathExpressionEvaluator();
            double result = evaluator.evaluate(expression, values);
            System.out.println("Result: " + result);
        } catch (ArithmeticException | IllegalArgumentException e) {
            // Handle specific exceptions and print relevant error messages
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // Handle general exceptions and print an error message
            System.out.println("Error:Please correct the error in the formula or provide the values for all variables "
                    + e.getMessage());
        }
    }
}
