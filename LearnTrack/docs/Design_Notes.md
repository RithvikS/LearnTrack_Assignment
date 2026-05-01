# Design Notes – LearnTrack Project

---

## 1. Why ArrayList Instead of Arrays?

In this project, students, courses, and enrollments are stored using `ArrayList` instead of regular arrays. Here's why:

### Problem with Regular Arrays

```java
Student[] students = new Student[10]; // Fixed size!
```

- You must declare the **size upfront**
- If you add more than 10 students → error or wasted space
- Deleting an element is complex (manual shifting)

### ArrayList Advantages

```java
ArrayList<Student> students = new ArrayList<>();
```

| Feature | Array | ArrayList |
|---------|-------|-----------|
| Size | Fixed at creation | Grows automatically |
| Add elements | Manual logic | `students.add(s)` |
| Remove elements | Complex shifting | `students.remove(s)` |
| Search/Loop | Manual for-loop | for-each supported |
| Type Safety | Supports primitives | Generics (`<Student>`) |

### Where it's used in LearnTrack:
- `StudentService` → `ArrayList<Student> students`
- `CourseService` → `ArrayList<Course> courses`
- `EnrollmentService` → `ArrayList<Enrollment> enrollments`

This allows us to add any number of students/courses without worrying about size.

---

## 2. Where Static is Used and Why

The `static` keyword in Java means the method or variable **belongs to the class**, not to any specific object. It is shared across all instances.

### IdGenerator – Static Counters

```java
public class IdGenerator {
    private static int studentCounter = 0;

    public static int getNextStudentId() {
        return ++studentCounter;
    }
}
```

**Why static here?**
- We need ONE global counter shared across the whole application
- No matter how many times `StudentService` is used, the ID must always increment globally
- Static ensures there is only **one copy** of `studentCounter` in memory
- You call it without creating an object: `IdGenerator.getNextStudentId()`

### ValidationUtil – Static Helper Methods

```java
public static boolean isValidEmail(String email) {
    return email != null && email.contains("@") && email.contains(".");
}
```

**Why static here?**
- These methods don't need any object state — they just take input and return a result
- Using `static` avoids creating a `ValidationUtil` object just to call a helper
- Cleaner usage: `ValidationUtil.isValidEmail(email)`

### Main.java – Static Service Variables

```java
static StudentService studentService = new StudentService();
```

**Why static here?**
- All menu methods (`studentMenu()`, `courseMenu()`, etc.) are `static`
- Static methods can only directly access static variables
- Keeps service instances shared across all menu methods without passing them around

---

## 3. Where Inheritance is Used and Benefits

### Inheritance Structure in LearnTrack

```
        Person (abstract base class)
           |
    -------+--------
    |               |
 Student          Trainer
```

### Person – The Base Class

```java
public abstract class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public abstract String getDisplayName(); // forces subclasses to implement
}
```

### Student – Extends Person

```java
public class Student extends Person {
    private String batch;
    private boolean active;

    public Student(int id, String fn, String ln, String email, String batch) {
        super(id, fn, ln, email); // reuses Person's constructor
        this.batch = batch;
    }

    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " [" + batch + "]";
    }
}
```

### Benefits of Inheritance Here

| Benefit | Explanation |
|---------|-------------|
| **Code Reuse** | `id`, `firstName`, `lastName`, `email` are written once in `Person`, not repeated in `Student` and `Trainer` |
| **Polymorphism** | `getDisplayName()` behaves differently in `Student` vs `Trainer` — same method name, different output |
| **Extensibility** | Adding a new type (e.g., `Admin`) just requires `extends Person` — no changes to existing code |
| **Cleaner Design** | Common properties in one place — less duplication, easier maintenance |

### super Keyword Usage

```java
super(id, firstName, lastName, email);
```

- Calls the **parent class constructor** from the child class
- Avoids re-initializing common fields manually
- Ensures `Person`'s logic runs before `Student`'s extra logic
