# LearnTrack – Student & Course Management System

## Project Description

**LearnTrack** is a console-based Student and Course Management System built using **Core Java only** (no frameworks, no databases). It allows you to manage students, courses, and their enrollments through a simple interactive text menu.

---

## Features

- **Student Management** – Add, update, search, deactivate students
- **Course Management** – Add courses, activate/deactivate them
- **Enrollment Management** – Enroll students in courses, update status
- **Custom Exception Handling** – Graceful error messages, no crashes
- **OOP Principles** – Encapsulation, Inheritance, Polymorphism
- **Enum for Status** – ACTIVE, COMPLETED, CANCELLED
- **Static ID Generator** – Auto-increments IDs for each entity
- **Validation Utility** – Email, empty-check, positive number checks

---

## Folder Structure

```
LearnTrack/
├── src/
│   └── com/
│       └── learntrack/
│           ├── entity/
│           │   ├── Person.java         (abstract base class)
│           │   ├── Student.java        (extends Person)
│           │   ├── Trainer.java        (extends Person - Bonus)
│           │   ├── Course.java
│           │   ├── Enrollment.java
│           │   └── EnrollmentStatus.java (enum)
│           ├── service/
│           │   ├── StudentService.java
│           │   ├── CourseService.java
│           │   └── EnrollmentService.java
│           ├── ui/
│           │   └── Main.java
│           ├── util/
│           │   ├── IdGenerator.java
│           │   └── ValidationUtil.java
│           └── exception/
│               └── EntityNotFoundException.java
└── docs/
    ├── README.md
    ├── Setup_Instructions.md
    ├── JVM_Basics.md
    └── Design_Notes.md
```

---

## How to Compile and Run

### Step 1 – Compile all Java files

Open terminal in the `LearnTrack/` directory and run:

```bash
find src -name "*.java" > sources.txt
javac -d out @sources.txt
```

### Step 2 – Run the program

```bash
java -cp out com.learntrack.ui.Main
```

---

## Class Diagram (Text Description)

```
Person (abstract)
  └── Student          extends Person
  └── Trainer          extends Person (Bonus)

Course                 standalone entity
Enrollment             links Student ↔ Course
EnrollmentStatus       enum (ACTIVE, COMPLETED, CANCELLED)

IdGenerator            static utility
ValidationUtil         static utility

StudentService         manages Student ArrayList
CourseService          manages Course ArrayList
EnrollmentService      manages Enrollment ArrayList
                         depends on StudentService + CourseService

EntityNotFoundException  custom exception

Main (UI)              uses all three services
```

---

## Technologies Used

- Java (JDK 17 recommended)
- Core Java only: ArrayList, Scanner, Exception, Enum, Inheritance
- No external libraries, no frameworks, no databases
