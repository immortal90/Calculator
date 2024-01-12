package com.shpp.p2p.cs.ikopach.assignment11;

import java.util.*;

/**
 * The `MathExpressionParser` class is responsible for parsing mathematical expressions,
 * converting them to Reverse Polish Notation (RPN), and creating corresponding
 * `MathExpression` objects.
 */
public class MathExpressionParser {
    public MathExpressionParser() {
    }

    /**
     * Parses the given mathematical expression and creates a `MathExpression` object
     * in Reverse Polish Notation (RPN).
     *
     * @param formula   The mathematical expression to parse.
     * @param variables A map of variable names and their corresponding values.
     * @return A `MathExpression` object in RPN form.
     */
    public MathExpression parse(String formula, HashMap<String, Double> variables) {
        // Remove spaces from the formula for uniform processing
        formula = formula.replaceAll("\\s", "");

        // Tokenize the formula and convert it to RPN
        List<String> tokens = tokenize(formula, variables);
        Queue<String> rpn = toRPN(tokens);

        // Return a MathExpression object with the RPN representation
        return new MathExpression(rpn);
    }

    /**
     * Tokenizes the given mathematical expression, handling operators, variables,
     * and literals, and returns a list of tokens.
     *
     * @param expression          The mathematical expression to tokenize.
     * @param variableDefinitions A map of variable names and their corresponding values.
     * @return A list of tokens representing the expression.
     */
    private List<String> tokenize(String expression, HashMap<String, Double> variableDefinitions) {
        // Initialize a list to store tokens and a StringBuilder for the current token
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        // Iterate through each character in the expression
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            c = (c == ',') ? '.' : c;

            // Check if the character is an operator or parentheses
            if (isOperator(c) || c == '(' || c == ')') {
                // Process the current token if not empty
                if (!currentToken.isEmpty()) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }

                // Handle negative numbers at the beginning or after an operator or open parenthesis
                if (c == '-' && (i == 0 || expression.charAt(i - 1) == '(' || isOperator(expression.charAt(i - 1)))) {
                    currentToken.append(c);
                } else {
                    tokens.add(String.valueOf(c));
                }
            } else if (Character.isDigit(c) || c == '.') {
                // Continue building the current token if the character is a digit or a dot
                currentToken.append(c);

                // Process the current token if at the end of the expression
                if (i == expression.length() - 1) {
                    tokens.add(currentToken.toString());
                }
            } else if (Character.isLetter(c)) {
                // Continue building the current token if the character is a letter (part of a variable or function)
                currentToken.append(c);

                // Process the current token if at the end of the expression
                if (i == expression.length() - 1) {
                    String token = currentToken.toString();
                    if (isFunction(token)) {
                        // Handle the function case
                        tokens.add(token);
                    } else {
                        // Handle the variable case
                        tokens.add(currentToken.toString());
                    }
                }
            } else if (c == '"') {
                // Handle quoted expressions
                int closingQuoteIndex = expression.indexOf('"', i + 1);
                if (closingQuoteIndex == -1) {
                    throw new IllegalArgumentException("Unmatched quote in the expression.");
                }

                // Extract the quoted expression and add it to the tokens
                String quotedExpression = expression.substring(i + 1, closingQuoteIndex);
                tokens.add(quotedExpression);
                i = closingQuoteIndex;
            }
        }
        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);

            // Check if the token ends with a dot
            if (token.endsWith(".")) {
                // Add ".0" to the token
                tokens.set(i, token + "0");
            }
            // Check if the token starts with a dot
            if (token.startsWith(".")) {
                // Add "0" before the token
                tokens.set(i, "0" + token);
            }
            // Check if the token starts with "-."
            if (token.startsWith("-.")) {
                // Add "0" before the token and remove the leading "-"
                tokens.set(i, "-0" + token.substring(1));
            }
        }

        return tokens;
    }

    /**
     * Checks if a given string represents a numeric value.
     *
     * @param str The string to check.
     * @return True if the string is numeric, false otherwise.
     */
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a given character is an operator.
     *
     * @param c The character to check.
     * @return True if the character is an operator, false otherwise.
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' ;
    }

    /**
     * Converts a list of tokens to Reverse Polish Notation (RPN) using the
     * Shunting Yard algorithm.
     *
     * @param tokens The list of tokens to convert.
     * @return A queue representing the expression in RPN.
     */
    private Queue<String> toRPN(List<String> tokens) {
        // Output queue to store the final RPN expression
        Queue<String> outputQueue = new LinkedList<>();

        // Operator stack to temporarily store operators and functions during conversion
        Stack<String> operatorStack = new Stack<>();

        // Iterate through each token in the input list
        for (String token : tokens) {
            // Check if the token is a mathematical function
            if (isFunction(token)) {
                operatorStack.push(token);
            }
            // Check if the token is a numeric constant or variable
            else if (isNumeric(token) || isVariable(token)) {
                outputQueue.offer(token);
            }
            // Check if the token is a negative number (e.g., '-3' is treated as '0 - 3')
            else if (token.charAt(0) == '-' && token.length() > 1) {
                outputQueue.offer(token.substring(1));
            }
            // Check if the token is an operator
            else if (isOperator(token.charAt(0))) {
                char currentOperator = token.charAt(0);

                // Pop operators from the stack and add to the output queue
                // based on precedence and associativity rules
                while (!operatorStack.isEmpty() && (isFunction(operatorStack.peek()) ||
                        (isOperator(operatorStack.peek().charAt(0)) &&
                                hasHigherPrecedence(currentOperator, operatorStack.peek().charAt(0))))) {
                    outputQueue.offer(operatorStack.pop());
                }

                // Push the current operator onto the stack
                operatorStack.push(String.valueOf(currentOperator));
            }
            // Check if the token is an opening parenthesis
            else if (token.charAt(0) == '(') {
                operatorStack.push(token);
            }
            // Check if the token is a closing parenthesis
            else if (token.charAt(0) == ')') {
                // Pop operators from the stack and add to the output queue until an opening parenthesis is encountered
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    outputQueue.offer(operatorStack.pop());
                }

                // Pop the opening parenthesis from the stack
                if (!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                }

                // If the next token on the stack is a function, pop and add to the output queue
                if (!operatorStack.isEmpty() && isFunction(operatorStack.peek())) {
                    outputQueue.offer(operatorStack.pop());
                }
            }
        }

        // Pop any remaining operators from the stack and add to the output queue
        while (!operatorStack.isEmpty()) {
            outputQueue.offer(operatorStack.pop());
        }

        // Return the final RPN expression in the form of a queue
        return outputQueue;
    }
    /**
     * Checks if a given string represents a variable (starts with a letter).
     *
     * @param str The string to check.
     * @return True if the string represents a variable, false otherwise.
     */
    private boolean isVariable(String str) {
        return Character.isLetter(str.charAt(0));
    }

    /**
     * Gets the precedence of an operator.
     *
     * @param operator The operator to get precedence for.
     * @return The precedence value.
     */
    private int getPrecedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 4;
            case '*', '/' -> 3;
            case '^' -> 2;
            default -> -1;
        };
    }

    /**
     * Checks if one operator has higher precedence than another.
     *
     * @param op1 The first operator.
     * @param op2 The second operator.
     * @return True if op1 has higher or equal precedence, false otherwise.
     */
    private boolean hasHigherPrecedence(char op1, char op2) {
        int precedence1 = getPrecedence(op1);
        int precedence2 = getPrecedence(op2);

        return precedence1 >= precedence2;
    }

    /**
     * Checks if a given string represents a function.
     *
     * @param str The string to check.
     * @return True if the string represents a function, false otherwise.
     */
    private boolean isFunction(String str) {
        // List of supported functions
        List<String> supportedFunctions = Arrays.asList("sin", "cos", "tan", "atan", "log10", "log2", "sqrt");

        return supportedFunctions.contains(str);
    }
}