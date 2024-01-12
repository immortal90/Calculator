package com.shpp.p2p.cs.ikopach.assignment11;

import java.util.HashMap;
import java.util.Map;

/**
 * The MathExpressionEvaluator class is responsible for evaluating mathematical expressions.
 * It uses a MathExpressionParser to parse the expression, handles variable values, and performs
 * the actual evaluation by calling the calculate method of the MathExpression class.
 */
public class MathExpressionEvaluator {
    private final MathExpressionParser parser;
    private final FunctionRegistry functionRegistry;

    /**
     * Constructs a MathExpressionEvaluator with an internal MathExpressionParser.
     */
    public MathExpressionEvaluator() {
        // Create a new MathExpressionParser for parsing mathematical expressions
        this.parser = new MathExpressionParser();
        this.functionRegistry = new FunctionRegistry();
    }

    /**
     * Evaluates the given mathematical expression by parsing it, handling variable values,
     * and calculating the result using a MathExpression object.
     *
     * @param expression The mathematical expression to evaluate.
     * @param values     Variable values for calculation.
     * @return The result of the evaluation.
     */
    public double evaluate(String expression, Map<String, Double> values) {
        // Parse the expression and create a MathExpression object
        MathExpression mathExpression = parser.parse(expression, new HashMap<>());

        // Set the FunctionRegistry for MathExpression
        mathExpression.setFunctionRegistry(functionRegistry);

        // Check for variables without values
        for (String variable : mathExpression.getVariables()) {
            if (!values.containsKey(variable)) {
                System.out.println("Please provide a value for variable: " + variable);
                return (0.0); // or throw an exception if you prefer
            }
        }

        // Set variable values for MathExpression
        mathExpression.setVariableValues(values);

        // Calculate and return the result of the expression
        return mathExpression.calculate();
    }
}

