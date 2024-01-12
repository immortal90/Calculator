package com.shpp.p2p.cs.ikopach.assignment11;

/**
 * The SqrtAction class implements the {@code IAction} interface
 * and provides a method to calculate the square root of a given number.
 */
public class SqrtAction implements IAction {

    /**
     * Calculates the square root of the specified number.
     *
     * @param number The input value for which the square root is calculated.
     * @return The square root of the given number.
     */
    @Override
    public double calculate(double number) {
        return Math.sqrt(number);
    }
}
