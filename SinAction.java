package com.shpp.p2p.cs.ikopach.assignment11;

/**
 * The SinAction class implements the {@code IAction} interface
 * and provides a method to calculate the sine of an angle in degrees.
 */
public class SinAction implements IAction {

    /**
     * Calculates the sine of the specified angle in degrees.
     *
     * @param number The angle in degrees for which the sine is calculated.
     * @return The sine of the given angle.
     */
    @Override
    public double calculate(double number) {
        // Convert the angle from degrees to radians before calculating the sine
        double radians = Math.toRadians(number);
        return Math.sin(radians);
    }
}
