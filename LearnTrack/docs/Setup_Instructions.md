# Setup Instructions – LearnTrack Project

---

## JDK Version Used

This project was developed and tested with **JDK 17 (LTS)**.

> You can use JDK 11 or higher. JDK 17 is recommended.

To check your installed Java version:
```bash
java -version
```

---

## Steps to Compile the Program

### Step 1: Install JDK

Download and install JDK 17 from:
- https://www.oracle.com/java/technologies/downloads/
- Or use OpenJDK: https://adoptium.net/

### Step 2: Verify Installation

Open a terminal/command prompt and run:
```bash
java -version
javac -version
```

You should see something like:
```
java version "17.0.x"
javac 17.0.x
```

### Step 3: Navigate to Project Folder

```bash
cd path/to/LearnTrack
```

### Step 4: Compile All Java Files

```bash
# This finds all .java files and stores them in sources.txt
find src -name "*.java" > sources.txt

# Compile everything into the 'out' folder
javac -d out @sources.txt
```

On Windows:
```cmd
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
```

### Step 5: Run the Program

```bash
java -cp out com.learntrack.ui.Main
```

---

## Hello World Example – Explained

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Line-by-Line Explanation

| Code | Meaning |
|------|---------|
| `public class HelloWorld` | Defines a class named HelloWorld. Every Java program lives inside a class. |
| `public static void main(String[] args)` | The main method — Java starts execution here. `static` means no object needed. `String[] args` accepts command-line arguments. |
| `System.out.println(...)` | Prints a line of text to the console. `System` is a built-in class, `out` is the output stream, `println` prints with a newline. |

### To compile and run Hello World:
```bash
javac HelloWorld.java
java HelloWorld
```

Output:
```
Hello, World!
```
