package com.learntrack.ui;

import com.learntrack.entity.EnrollmentStatus;
import com.learntrack.exception.EntityNotFoundException;
import com.learntrack.service.CourseService;
import com.learntrack.service.EnrollmentService;
import com.learntrack.service.StudentService;
import com.learntrack.util.ValidationUtil;

import java.util.Scanner;

/**
 * Main.java - Entry point for LearnTrack System.
 *
 * This class handles all user interaction (UI layer).
 * Business logic is kept in Service classes (separation of concerns).
 *
 * Demonstrates:
 * - Scanner for user input
 * - while loops for menu navigation
 * - switch statements for menu selection
 * - try-catch for error handling
 */
public class Main {

    // Services - declared at class level for shared access
    static StudentService studentService = new StudentService();
    static CourseService courseService = new CourseService();
    static EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printBanner();
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    courseMenu();
                    break;
                case 3:
                    enrollmentMenu();
                    break;
                case 0:
                    System.out.println("\n  Goodbye! Thank you for using LearnTrack.");
                    running = false;
                    break;
                default:
                    ValidationUtil.printError("Invalid choice. Please select 0-3.");
            }
        }
        scanner.close();
    }

    // =============================================
    //              MAIN MENU
    // =============================================

    static void printBanner() {
        System.out.println("=======================================================");
        System.out.println("         LearnTrack - Student & Course Manager         ");
        System.out.println("          Core Java Console Application v1.0           ");
        System.out.println("=======================================================");
    }

    static void printMainMenu() {
        System.out.println("\n------- MAIN MENU -------");
        System.out.println("  1. Student Management");
        System.out.println("  2. Course Management");
        System.out.println("  3. Enrollment Management");
        System.out.println("  0. Exit");
        System.out.println("-------------------------");
    }

    // =============================================
    //            STUDENT MENU
    // =============================================

    static void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- STUDENT MANAGEMENT ---");
            System.out.println("  1. Add Student");
            System.out.println("  2. View All Students");
            System.out.println("  3. Search Student by ID");
            System.out.println("  4. Update Student");
            System.out.println("  5. Deactivate Student");
            System.out.println("  0. Back to Main Menu");
            System.out.println("--------------------------");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    addStudentFlow();
                    break;
                case 2:
                    studentService.listStudents();
                    break;
                case 3:
                    searchStudentFlow();
                    break;
                case 4:
                    updateStudentFlow();
                    break;
                case 5:
                    deactivateStudentFlow();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    ValidationUtil.printError("Invalid option.");
            }
        }
    }

    static void addStudentFlow() {
        System.out.println("\n  -- Add New Student --");
        String firstName = readString("  First Name: ");
        String lastName  = readString("  Last Name : ");
        String email     = readString("  Email     : ");
        String batch     = readString("  Batch     : ");
        studentService.addStudent(firstName, lastName, email, batch);
    }

    static void searchStudentFlow() {
        int id = readInt("  Enter Student ID: ");
        try {
            System.out.println("  " + studentService.findStudentById(id));
        } catch (EntityNotFoundException e) {
            ValidationUtil.printError(e.getMessage());
        }
    }

    static void updateStudentFlow() {
        int id = readInt("  Enter Student ID to update: ");
        try {
            studentService.findStudentById(id); // check exists
            String firstName = readString("  New First Name: ");
            String lastName  = readString("  New Last Name : ");
            String email     = readString("  New Email     : ");
            String batch     = readString("  New Batch     : ");
            studentService.updateStudent(id, firstName, lastName, email, batch);
        } catch (EntityNotFoundException e) {
            ValidationUtil.printError(e.getMessage());
        }
    }

    static void deactivateStudentFlow() {
        int id = readInt("  Enter Student ID to deactivate: ");
        try {
            studentService.removeStudent(id);
        } catch (EntityNotFoundException e) {
            ValidationUtil.printError(e.getMessage());
        }
    }

    // =============================================
    //             COURSE MENU
    // =============================================

    static void courseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- COURSE MANAGEMENT ---");
            System.out.println("  1. Add Course");
            System.out.println("  2. View All Courses");
            System.out.println("  3. Activate Course");
            System.out.println("  4. Deactivate Course");
            System.out.println("  0. Back to Main Menu");
            System.out.println("-------------------------");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    addCourseFlow();
                    break;
                case 2:
                    courseService.listCourses();
                    break;
                case 3:
                    activateCourseFlow();
                    break;
                case 4:
                    deactivateCourseFlow();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    ValidationUtil.printError("Invalid option.");
            }
        }
    }

    static void addCourseFlow() {
        System.out.println("\n  -- Add New Course --");
        String name        = readString("  Course Name     : ");
        String description = readString("  Description     : ");
        int    weeks       = readInt   ("  Duration (weeks): ");
        courseService.addCourse(name, description, weeks);
    }

    static void activateCourseFlow() {
        int id = readInt("  Enter Course ID to activate: ");
        try {
            courseService.activateCourse(id);
        } catch (EntityNotFoundException e) {
            ValidationUtil.printError(e.getMessage());
        }
    }

    static void deactivateCourseFlow() {
        int id = readInt("  Enter Course ID to deactivate: ");
        try {
            courseService.deactivateCourse(id);
        } catch (EntityNotFoundException e) {
            ValidationUtil.printError(e.getMessage());
        }
    }

    // =============================================
    //           ENROLLMENT MENU
    // =============================================

    static void enrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- ENROLLMENT MANAGEMENT ---");
            System.out.println("  1. Enroll Student in Course");
            System.out.println("  2. View Student Enrollments");
            System.out.println("  3. Update Enrollment Status");
            System.out.println("  4. View All Enrollments");
            System.out.println("  0. Back to Main Menu");
            System.out.println("-----------------------------");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    enrollStudentFlow();
                    break;
                case 2:
                    viewEnrollmentsFlow();
                    break;
                case 3:
                    updateStatusFlow();
                    break;
                case 4:
                    enrollmentService.listAllEnrollments();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    ValidationUtil.printError("Invalid option.");
            }
        }
    }

    static void enrollStudentFlow() {
        int studentId = readInt("  Student ID : ");
        int courseId  = readInt("  Course ID  : ");
        try {
            enrollmentService.enrollStudent(studentId, courseId);
        } catch (EntityNotFoundException e) {
            ValidationUtil.printError(e.getMessage());
        }
    }

    static void viewEnrollmentsFlow() {
        int studentId = readInt("  Enter Student ID: ");
        try {
            enrollmentService.viewEnrollmentsByStudent(studentId);
        } catch (EntityNotFoundException e) {
            ValidationUtil.printError(e.getMessage());
        }
    }

    static void updateStatusFlow() {
        int enrollmentId = readInt("  Enrollment ID : ");
        System.out.println("  Status options: ACTIVE | COMPLETED | CANCELLED");
        String status = readString("  New Status     : ");
        try {
            enrollmentService.updateEnrollmentStatus(enrollmentId, status);
        } catch (EntityNotFoundException e) {
            ValidationUtil.printError(e.getMessage());
        }
    }

    // =============================================
    //           HELPER / INPUT METHODS
    // =============================================

    /**
     * Reads an integer safely, with try-catch for invalid input.
     */
    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                ValidationUtil.printError("Please enter a valid number.");
            }
        }
    }

    /**
     * Reads a non-empty string from the user.
     */
    static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            ValidationUtil.printError("Input cannot be empty.");
        }
    }
}
