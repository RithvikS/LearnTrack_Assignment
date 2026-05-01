package com.learntrack.service;

import com.learntrack.entity.Course;
import com.learntrack.exception.EntityNotFoundException;
import com.learntrack.util.IdGenerator;
import com.learntrack.util.ValidationUtil;

import java.util.ArrayList;

/**
 * CourseService handles all business logic related to Courses.
 * Uses ArrayList to store course data in memory.
 */
public class CourseService {

    // ArrayList to store all courses
    private ArrayList<Course> courses = new ArrayList<>();

    /**
     * Add a new course (basic version - method overloading).
     */
    public void addCourse(String courseName, int durationInWeeks) {
        if (ValidationUtil.isNullOrEmpty(courseName)) {
            ValidationUtil.printError("Course name cannot be empty.");
            return;
        }
        if (!ValidationUtil.isPositive(durationInWeeks)) {
            ValidationUtil.printError("Duration must be a positive number.");
            return;
        }
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, durationInWeeks);
        courses.add(course);
        ValidationUtil.printSuccess("Course added! ID: " + id);
    }

    /**
     * Add a new course with description (method overloading).
     */
    public void addCourse(String courseName, String description, int durationInWeeks) {
        if (ValidationUtil.isNullOrEmpty(courseName)) {
            ValidationUtil.printError("Course name cannot be empty.");
            return;
        }
        if (!ValidationUtil.isPositive(durationInWeeks)) {
            ValidationUtil.printError("Duration must be a positive number.");
            return;
        }
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks, true);
        courses.add(course);
        ValidationUtil.printSuccess("Course added with description! ID: " + id);
    }

    /**
     * List all courses (active and inactive).
     */
    public void listCourses() {
        if (courses.isEmpty()) {
            ValidationUtil.printInfo("No courses available.");
            return;
        }
        System.out.println("\n  ===== COURSE LIST =====");
        for (Course c : courses) {
            System.out.println("  " + c);
        }
        System.out.println("  Total: " + courses.size() + " course(s)");
    }

    /**
     * Activate a course by ID.
     */
    public void activateCourse(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(true);
        ValidationUtil.printSuccess("Course ID " + id + " activated.");
    }

    /**
     * Deactivate a course by ID.
     */
    public void deactivateCourse(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(false);
        ValidationUtil.printSuccess("Course ID " + id + " deactivated.");
    }

    /**
     * Find a course by its ID.
     */
    public Course findCourseById(int id) throws EntityNotFoundException {
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new EntityNotFoundException("Course with ID " + id + " not found.");
    }

    /**
     * Returns raw list (used by EnrollmentService).
     */
    public ArrayList<Course> getAllCourses() {
        return courses;
    }
}
