package com.shpp.p2p.cs.ikopach.assignment11;

/**
 * The IAction interface represents a mathematical action that can be performed
 * on a given number. Classes implementing this interface should provide a method to
 * calculate the result of the specified action.
 */
public interface IAction {

    /**
     * Calculates the result of the mathematical action on the given number.
     *
     * @param number The input number for the mathematical action.
     * @return The result of applying the action to the input number.
     */
    double calculate(double number);
}
