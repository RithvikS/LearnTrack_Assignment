package com.learntrack.entity;

/**
 * Trainer class - BONUS feature.
 * Extends Person to demonstrate inheritance with another subclass.
 */
public class Trainer extends Person {

    private String specialization;
    private boolean active;

    public Trainer(int id, String firstName, String lastName, String email, String specialization) {
        super(id, firstName, lastName, email); // 'super' keyword
        this.specialization = specialization;
        this.active = true;
    }

    // Override getDisplayName() - Polymorphism
    @Override
    public String getDisplayName() {
        return "Trainer: " + getFirstName() + " " + getLastName() + " (" + specialization + ")";
    }

    // ---- Getters and Setters ----

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
