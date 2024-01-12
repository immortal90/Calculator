package com.shpp.p2p.cs.ikopach.assignment11;

/**
 * The AtanAction class implements the {@code IAction} interface
 * and provides a method to calculate the arctangent of a given number.
 */
public class AtanAction implements IAction {

    /**
     * Calculates the arctangent of the specified number.
     *
     * @param number The input value for which the arctangent is calculated.
     * @return The arctangent of the given number in radians.
     */
    @Override
    public double calculate(double number) {
        return Math.atan(number);
    }
}
