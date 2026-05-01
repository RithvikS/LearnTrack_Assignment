package com.learntrack.service;

import com.learntrack.entity.Student;
import com.learntrack.exception.EntityNotFoundException;
import com.learntrack.util.IdGenerator;
import com.learntrack.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentService handles all business logic related to Students.
 *
 * Uses ArrayList to store student data in memory.
 * Demonstrates method overloading with multiple addStudent() versions.
 */
public class StudentService {

    // ArrayList to store students - no fixed size, easy to add/remove
    private ArrayList<Student> students = new ArrayList<>();

    // -------------------------------------------------------
    // Method Overloading: addStudent with different parameters
    // -------------------------------------------------------

    /**
     * Add a student with only required fields.
     */
    public void addStudent(String firstName, String lastName, String email) {
        if (ValidationUtil.isNullOrEmpty(firstName) || ValidationUtil.isNullOrEmpty(email)) {
            ValidationUtil.printError("First name and email are required.");
            return;
        }
        if (!ValidationUtil.isValidEmail(email)) {
            ValidationUtil.printError("Invalid email format.");
            return;
        }
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email);
        students.add(student);
        ValidationUtil.printSuccess("Student added! ID: " + id);
    }

    /**
     * Add a student with all fields including batch.
     * Method Overloading - same name, different parameters.
     */
    public void addStudent(String firstName, String lastName, String email, String batch) {
        if (ValidationUtil.isNullOrEmpty(firstName) || ValidationUtil.isNullOrEmpty(email)) {
            ValidationUtil.printError("First name and email are required.");
            return;
        }
        if (!ValidationUtil.isValidEmail(email)) {
            ValidationUtil.printError("Invalid email format.");
            return;
        }
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch, true);
        students.add(student);
        ValidationUtil.printSuccess("Student added with batch! ID: " + id);
    }

    /**
     * Update an existing student's details.
     */
    public void updateStudent(int id, String firstName, String lastName, String email, String batch)
            throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
        ValidationUtil.printSuccess("Student ID " + id + " updated successfully.");
    }

    /**
     * Deactivate a student (soft delete - mark inactive).
     */
    public void removeStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
        ValidationUtil.printSuccess("Student ID " + id + " deactivated.");
    }

    /**
     * List all active students.
     */
    public void listStudents() {
        List<Student> activeStudents = new ArrayList<>();
        for (Student s : students) {
            if (s.isActive()) {
                activeStudents.add(s);
            }
        }

        if (activeStudents.isEmpty()) {
            ValidationUtil.printInfo("No active students found.");
            return;
        }

        System.out.println("\n  ===== STUDENT LIST =====");
        for (Student s : activeStudents) {
            System.out.println("  " + s);
        }
        System.out.println("  Total: " + activeStudents.size() + " student(s)");
    }

    /**
     * Find and return a student by ID.
     * Throws EntityNotFoundException if not found.
     */
    public Student findStudentById(int id) throws EntityNotFoundException {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new EntityNotFoundException("Student with ID " + id + " not found.");
    }

    /**
     * Returns raw list (used by other services like EnrollmentService).
     */
    public ArrayList<Student> getAllStudents() {
        return students;
    }
}
