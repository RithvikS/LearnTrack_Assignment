package com.learntrack.entity;

/**
 * Student class - extends Person (Inheritance).
 * Adds batch and active status specific to students.
 */
public class Student extends Person {

    private String batch;
    private boolean active;

    // Constructor overloading - Basic constructor (only required fields)
    public Student(int id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email); // using 'super' keyword
        this.batch = "General";
        this.active = true;
    }

    // Constructor overloading - Full constructor
    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email); // using 'super' keyword
        this.batch = batch;
        this.active = active;
    }

    // Override getDisplayName() - Polymorphism
    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " [" + batch + "]";
    }

    // ---- Getters and Setters ----

    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Student{ID=" + getId()
                + ", Name=" + getFirstName() + " " + getLastName()
                + ", Email=" + getEmail()
                + ", Batch=" + batch
                + ", Active=" + active + "}";
    }
}
