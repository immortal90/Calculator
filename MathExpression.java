package com.shpp.p2p.cs.ikopach.assignment11;

import java.util.*;

/**
 * The MathExpression class represents a mathematical expression in Reverse Polish Notation (RPN).
 * It provides a method for calculating the result of the expression using a stack-based algorithm.
 * The class supports basic arithmetic operations and exponentiation.
 */
public class MathExpression {
    private final Queue<String> rpn;
    private FunctionRegistry functionRegistry;
    private Map<String, Double> variableValues;

    /**
     * Constructs a MathExpression object with the given RPN representation.
     *
     * @param rpn The queue containing the RPN representation of the mathematical expression.
     */
    public MathExpression(Queue<String> rpn) {
        this.rpn = rpn;
        this.variableValues = new HashMap<>();
    }

    /**
     * Calculates the result of the mathematical expression using a stack-based algorithm.
     *
     * @return The result of the mathematical expression.
     * @throws ArithmeticException      If division by zero is encountered during the calculation.
     * @throws IllegalArgumentException If an unknown operator is encountered during the calculation.
     */
    public double calculate() {
        // Using a deque to store numbers during the calculation
        Deque<Double> numbers = new ArrayDeque<>();

        // Iterate through each token in the Reverse Polish Notation (RPN)
        while (!rpn.isEmpty()) {
            // Retrieve the next token from the RPN
            String token = rpn.poll();

            // Check if the token is a numeric value
            if (isNumeric(token)) {
                // If numeric, push onto the stack
                numbers.push(Double.parseDouble(token));
            } else if (isOperator(token.charAt(0))) {
                // If an operator, pop two operands, apply the operator, and push the result back onto the stack
                double operand2 = numbers.pop();
                double operand1 = numbers.pop();
                double result = applyOperator(operand1, operand2, token.charAt(0));
                numbers.push(result);
            } else if (isFunction(token)) {
                double argument = numbers.pop();
                double result = applyFunction(token, argument);
                numbers.push(result);
            } else {
                // If it's a variable, push its value onto the stack
                double value = variableValues.getOrDefault(token, 0.0);
                numbers.push(value);
            }
        }

        // The final result is at the top of the stack
        return numbers.pop();
    }

    /**
     * Retrieves the unique set of variables present in the Reverse Polish Notation (RPN) expression.
     *
     * @return A set containing unique variable names found in the RPN expression.
     */
    public Set<String> getVariables() {
        // Set to store unique variable names
        Set<String> variables = new HashSet<>();

        // Iterate through each token in the RPN expression
        for (String token : rpn) {
            // Check if the token is not a numeric constant, operator, or function
            if (!isNumeric(token) && !isOperator(token.charAt(0)) && !isFunction(token)) {
                // Add the token (variable) to the set
                variables.add(token);
            }
        }

        // Return the set of unique variables
        return variables;
    }
    /**
     * Checks if a given string represents a numeric value.
     *
     * @param str The string to check.
     * @return True if the string is numeric, false otherwise.
     */
    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Checks if a given character is a valid arithmetic operator.
     *
     * @param c The character to check.
     * @return True if the character is an operator, false otherwise.
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    /**
     * Applies the given arithmetic operator to two operands and returns the result.
     *
     * @param operand1 The first operand.
     * @param operand2 The second operand.
     * @param operator The arithmetic operator.
     * @return The result of applying the operator to the operands.
     * @throws ArithmeticException      If division by zero is encountered during the calculation.
     * @throws IllegalArgumentException If an unknown operator is encountered during the calculation.
     */
    private double applyOperator(double operand1, double operand2, char operator) {
        if (operator == '/' && operand2 == 0) {
            throw new ArithmeticException("Division by 0 is not allowed");
        }

        double result;
        switch (operator) {
            case '+' -> result = operand1 + operand2;
            case '-' -> result = operand1 - operand2;
            case '*' -> result = operand1 * operand2;
            case '/' -> result = operand1 / operand2;
            case '^' -> result = power(operand1, operand2);
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        }
        // Uncomment as needed (replace '2' with the value you want after the comma)
        // System.out.printf("Performed operation: %.2f %c %.2f = %.2f%n", operand1, operator, operand2, result);
        return result;
    }

    /**
     * Computes the result of raising a base to a given exponent.
     *
     * @param base     The base value.
     * @param exponent The exponent value.
     * @return The result of raising the base to the exponent.
     * @throws IllegalArgumentException If raising a negative number to a fractional power is attempted.
     */
    private double power(double base, double exponent) {
        if (base < 0 && Math.floor(exponent) != exponent) {
            // Raising a negative number to a fractional power is undefined
            throw new IllegalArgumentException("Raising a negative number to a fractional power is undefined");
        }

        return Math.pow(base, exponent);
    }

    /**
     * Sets the function registry to be used for mathematical operations.
     *
     * @param functionRegistry The function registry to be set.
     */
    public void setFunctionRegistry(FunctionRegistry functionRegistry) {
        this.functionRegistry = functionRegistry;
    }

    /**
     * Applies the specified function on the given argument using the registered function in the function registry.
     *
     * @param functionName The name of the function to be applied.
     * @param argument The argument on which the function is applied.
     * @return The result of applying the function on the given argument.
     * @throws IllegalArgumentException If the function registry is not set or does not contain the specified function.
     */
    private double applyFunction(String functionName, double argument) {
        if (functionRegistry != null && functionRegistry.containsFunction(functionName)) {
            double result = functionRegistry.getFunction(functionName).calculate(argument);
            // Uncomment as needed (replace '2' with the value you want after the comma)
            //System.out.printf("Performed operation: %s(%.2f) = %.2f%n", functionName, argument, result);
            return result;
        } else {
            throw new IllegalArgumentException("Unknown function: " + functionName);
        }
    }

    /**
     * Sets the variable values to be used in mathematical operations.
     *
     * @param variableValues A map containing variable names and their corresponding values.
     */
    public void setVariableValues(Map<String, Double> variableValues) {
        this.variableValues = variableValues;
    }

    /**
     * Checks if the given token represents a registered function in the function registry.
     *
     * @param token The token to be checked.
     * @return true if the token represents a registered function, false otherwise.
     */
    private boolean isFunction(String token) {
        return functionRegistry.containsFunction(token);
    }
}
