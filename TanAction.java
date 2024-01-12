package com.shpp.p2p.cs.ikopach.assignment11;

/**
 * The TanAction class implements the {@code IAction} interface
 * and provides a method to calculate the tangent of an angle in degrees.
 */
public class TanAction implements IAction {

    /**
     * Calculates the tangent of the specified angle in degrees.
     *
     * @param number The angle in degrees for which the tangent is calculated.
     * @return The tangent of the given angle.
     */
    @Override
    public double calculate(double number) {
        // Convert the angle from degrees to radians before calculating the tangent
        double radians = Math.toRadians(number);
        return Math.tan(radians);
    }
}
