package com.shpp.p2p.cs.ikopach.assignment11;

/**
 * The Log2Action class implements the {@code IAction} interface
 * and provides a method to calculate the base-2 logarithm of a given number.
 */
public class Log2Action implements IAction {

    /**
     * Calculates the base-2 logarithm of the specified number.
     *
     * @param number The input value for which the base-2 logarithm is calculated.
     * @return The base-2 logarithm of the given number.
     */
    @Override
    public double calculate(double number) {
        // Calculate the logarithm of the given number divided by the logarithm of 2
        return Math.log(number) / Math.log(2);
    }
}
