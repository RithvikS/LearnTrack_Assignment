package com.learntrack.entity;

/**
 * Base class representing a Person.
 * Demonstrates inheritance - Student extends this class.
 */
public abstract class Person {

    // Private fields - Encapsulation
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    // Default constructor
    public Person() {}

    // Parameterized constructor
    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Abstract method - must be overridden in subclasses (Polymorphism)
    public abstract String getDisplayName();

    // ---- Getters and Setters ----

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + getDisplayName() + " | Email: " + email;
    }
}
