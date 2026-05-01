package com.learntrack.util;

/**
 * Utility class for simple input validations - BONUS feature.
 * All methods are static since they are stateless helpers.
 */
public class ValidationUtil {

    private ValidationUtil() {} // Prevent instantiation

    /**
     * Checks if a string is null or blank.
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Validates a basic email format (must contain '@' and '.').
     */
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    /**
     * Validates that a number is positive (greater than 0).
     */
    public static boolean isPositive(int value) {
        return value > 0;
    }

    /**
     * Prints a formatted error message to the console.
     */
    public static void printError(String message) {
        System.out.println("\n  [!] ERROR: " + message);
    }

    /**
     * Prints a formatted success message to the console.
     */
    public static void printSuccess(String message) {
        System.out.println("\n  [✓] SUCCESS: " + message);
    }

    /**
     * Prints a formatted info message to the console.
     */
    public static void printInfo(String message) {
        System.out.println("  [i] " + message);
    }
}
