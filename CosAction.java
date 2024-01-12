package com.shpp.p2p.cs.ikopach.assignment11;

/**
 * The CosAction class implements the {@code IAction} interface
 * and provides a method to calculate the cosine of a given angle in degrees.
 */
public class CosAction implements IAction {

    /**
     * Calculates the cosine of the specified angle in degrees.
     *
     * @param number The angle in degrees for which the cosine is calculated.
     * @return The cosine of the given angle.
     */
    @Override
    public double calculate(double number) {
        // Convert the angle from degrees to radians before calculating the cosine
        double radians = Math.toRadians(number);
        return Math.cos(radians);
    }
}
