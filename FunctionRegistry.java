package com.shpp.p2p.cs.ikopach.assignment11;

import java.util.HashMap;
import java.util.Map;

/**
 * The FunctionRegistry class manages a registry of mathematical functions
 * and provides methods to check if a function is registered, retrieve a function by name,
 * register new functions, and execute a registered function with a given input.
 */
public class FunctionRegistry {
    private final Map<String, IAction> functionMap;

    /**
     * Constructs a new FunctionRegistry and registers default mathematical functions.
     */
    public FunctionRegistry() {
        this.functionMap = new HashMap<>();
        // Function registration
        registerFunction("sin", new SinAction());
        registerFunction("cos", new CosAction());
        registerFunction("tan", new TanAction());
        registerFunction("atan", new AtanAction());
        registerFunction("log10", new Log10Action());
        registerFunction("log2", new Log2Action());
        registerFunction("sqrt", new SqrtAction());
    }

    /**
     * Checks if the registry contains a function with the specified name.
     *
     * @param name The name of the function to check.
     * @return {@code true} if the function is registered, {@code false} otherwise.
     */
    public boolean containsFunction(String name) {
        return functionMap.containsKey(name);
    }

    /**
     * Retrieves a registered function by its name.
     *
     * @param name The name of the function to retrieve.
     * @return The {@code IAction} instance representing the requested function.
     */
    public IAction getFunction(String name) {
        return functionMap.get(name);
    }

    /**
     * Registers a new function with the specified name and associated action.
     *
     * @param functionName The name of the function to register.
     * @param action       The {@code IAction} instance representing the function's action.
     */
    private void registerFunction(String functionName, IAction action) {
        functionMap.put(functionName, action);
    }
}