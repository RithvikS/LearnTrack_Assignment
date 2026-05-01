package com.learntrack.exception;

/**
 * Custom exception thrown when an entity (Student, Course, Enrollment)
 * is not found by the given ID.
 *
 * Demonstrates custom exception creation in Java.
 */
public class EntityNotFoundException extends Exception {

    // Constructor with a custom message
    public EntityNotFoundException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
