package com.learntrack.util;

/**
 * Utility class for generating unique IDs.
 *
 * Demonstrates use of 'static' keyword:
 * - Static variables are shared across all instances.
 * - Static methods can be called without creating an object.
 * - Here, we use static counters to track the last assigned ID.
 */
public class IdGenerator {

    // Static counters - shared across the entire application
    private static int studentCounter = 0;
    private static int courseCounter = 0;
    private static int enrollmentCounter = 0;

    // Private constructor - prevents instantiation (utility class pattern)
    private IdGenerator() {}

    /**
     * Returns the next unique Student ID.
     */
    public static int getNextStudentId() {
        return ++studentCounter;
    }

    /**
     * Returns the next unique Course ID.
     */
    public static int getNextCourseId() {
        return ++courseCounter;
    }

    /**
     * Returns the next unique Enrollment ID.
     */
    public static int getNextEnrollmentId() {
        return ++enrollmentCounter;
    }

    /**
     * Returns total students created so far.
     */
    public static int getTotalStudents() {
        return studentCounter;
    }

    /**
     * Returns total courses created so far.
     */
    public static int getTotalCourses() {
        return courseCounter;
    }

    /**
     * Returns total enrollments created so far.
     */
    public static int getTotalEnrollments() {
        return enrollmentCounter;
    }
}
