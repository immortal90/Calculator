package com.shpp.p2p.cs.ikopach.assignment11;

/**
 * The Log10Action class implements the {@code IAction} interface
 * and provides a method to calculate the base-10 logarithm of a given number.
 */
public class Log10Action implements IAction {

    /**
     * Calculates the base-10 logarithm of the specified number.
     *
     * @param number The input value for which the base-10 logarithm is calculated.
     * @return The base-10 logarithm of the given number.
     */
    @Override
    public double calculate(double number) {
        // Calculate the base-10 logarithm of the given number
        return Math.log10(number);
    }
}
