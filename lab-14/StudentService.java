
import java.util.ArrayList;
import java.util.Scanner;

// Student class
class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student Name: " + name;
    }
}

// Service class
class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (student == null ||
            student.getName() == null ||
            student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student!");
        }

        students.add(student);
        System.out.println("Student added successfully!");
    }

    public int getStudentCount() {
        return students.size();
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }
}

// Main class
public class StudentService {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager service = new StudentManager();

        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Show Student Count");
            System.out.println("3. Display Students");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }

            try {
                if (choice == 1) {
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    service.addStudent(new Student(name));

                } else if (choice == 2) {
                    System.out.println(
                        "Total Students: " + service.getStudentCount()
                    );

                } else if (choice == 3) {
                    service.displayStudents();

                } else if (choice == 4) {
                    System.out.println("Application closed.");
                    break;

                } else {
                    System.out.println("Invalid choice!");
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        sc.close();
    }
}
