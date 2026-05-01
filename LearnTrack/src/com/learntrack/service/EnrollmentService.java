package com.learntrack.service;

import com.learntrack.entity.Enrollment;
import com.learntrack.entity.EnrollmentStatus;
import com.learntrack.entity.Student;
import com.learntrack.entity.Course;
import com.learntrack.exception.EntityNotFoundException;
import com.learntrack.util.IdGenerator;
import com.learntrack.util.ValidationUtil;

import java.util.ArrayList;

/**
 * EnrollmentService handles all business logic for Enrollments.
 * Links Students to Courses.
 */
public class EnrollmentService {

    // ArrayList to store all enrollments
    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    // References to other services for validation
    private StudentService studentService;
    private CourseService courseService;

    // Constructor injection - services passed in
    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * Enroll a student in a course.
     * Validates both student and course exist before enrolling.
     */
    public void enrollStudent(int studentId, int courseId) throws EntityNotFoundException {
        // Validate student and course exist
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            ValidationUtil.printError("Student is deactivated and cannot be enrolled.");
            return;
        }
        if (!course.isActive()) {
            ValidationUtil.printError("Course is deactivated. Cannot enroll in it.");
            return;
        }

        // Check if already enrolled and active
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId && e.getCourseId() == courseId
                    && e.getStatus() == EnrollmentStatus.ACTIVE) {
                ValidationUtil.printError("Student is already enrolled in this course.");
                return;
            }
        }

        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollments.add(enrollment);
        ValidationUtil.printSuccess("Enrollment successful! Enrollment ID: " + id
                + " | " + student.getDisplayName() + " → " + course.getCourseName());
    }

    /**
     * View all enrollments for a specific student.
     */
    public void viewEnrollmentsByStudent(int studentId) throws EntityNotFoundException {
        // Validate student exists
        Student student = studentService.findStudentById(studentId);

        System.out.println("\n  ===== ENROLLMENTS FOR: " + student.getDisplayName() + " =====");
        boolean found = false;
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                try {
                    Course course = courseService.findCourseById(e.getCourseId());
                    System.out.println("  Enrollment ID: " + e.getId()
                            + " | Course: " + course.getCourseName()
                            + " | Date: " + e.getEnrollmentDate()
                            + " | Status: " + e.getStatus());
                } catch (EntityNotFoundException ex) {
                    System.out.println("  Enrollment ID: " + e.getId()
                            + " | Course ID: " + e.getCourseId() + " (Course removed)"
                            + " | Status: " + e.getStatus());
                }
                found = true;
            }
        }
        if (!found) {
            ValidationUtil.printInfo("No enrollments found for this student.");
        }
    }

    /**
     * Update the status of an enrollment.
     * Method overloading - accepts String or EnrollmentStatus.
     */
    public void updateEnrollmentStatus(int enrollmentId, EnrollmentStatus newStatus)
            throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(newStatus);
        ValidationUtil.printSuccess("Enrollment ID " + enrollmentId + " status updated to: " + newStatus);
    }

    /**
     * Method overloading - update using String status name.
     */
    public void updateEnrollmentStatus(int enrollmentId, String statusStr) throws EntityNotFoundException {
        try {
            EnrollmentStatus status = EnrollmentStatus.valueOf(statusStr.toUpperCase());
            updateEnrollmentStatus(enrollmentId, status);
        } catch (IllegalArgumentException e) {
            ValidationUtil.printError("Invalid status: " + statusStr + ". Use ACTIVE, COMPLETED, or CANCELLED.");
        }
    }

    /**
     * Find enrollment by ID.
     */
    public Enrollment findEnrollmentById(int id) throws EntityNotFoundException {
        for (Enrollment e : enrollments) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new EntityNotFoundException("Enrollment with ID " + id + " not found.");
    }

    /**
     * List all enrollments.
     */
    public void listAllEnrollments() {
        if (enrollments.isEmpty()) {
            ValidationUtil.printInfo("No enrollments found.");
            return;
        }
        System.out.println("\n  ===== ALL ENROLLMENTS =====");
        for (Enrollment e : enrollments) {
            System.out.println("  " + e);
        }
    }
}
