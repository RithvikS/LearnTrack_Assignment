package com.learntrack.entity;

/**
 * Course class representing a course offered in the system.
 * Demonstrates encapsulation with private fields and public getters/setters.
 */
public class Course {

    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    // Constructor overloading - Minimal constructor
    public Course(int id, String courseName, int durationInWeeks) {
        this.id = id;
        this.courseName = courseName;
        this.durationInWeeks = durationInWeeks;
        this.description = "No description provided.";
        this.active = true;
    }

    // Constructor overloading - Full constructor
    public Course(int id, String courseName, String description, int durationInWeeks, boolean active) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = active;
    }

    // ---- Getters and Setters ----

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDurationInWeeks() { return durationInWeeks; }
    public void setDurationInWeeks(int durationInWeeks) { this.durationInWeeks = durationInWeeks; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Course{ID=" + id
                + ", Name=" + courseName
                + ", Duration=" + durationInWeeks + " weeks"
                + ", Active=" + active + "}";
    }
}
