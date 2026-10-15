import java.io.*;

class Student implements Serializable {
    int id;
   String name;
   transient String password;

   Student(int id,String name,String pass)
   {
    this.id=id;
    this.name=name;
    this.password=pass;
   }
}

public class SaveLoadDemo {
    public static void main(String[] args) {

        Student[] students = {
            new Student(101, "Dhruvi", "abc123"),
            new Student(102, "Riya", "xyz456")
        };

        try {
            ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("students.dat")
            );

            out.writeObject(students);
            out.close();

            System.out.println("Objects saved successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("students.dat")
            );

            Student[] loadedStudents = (Student[]) in.readObject();
            in.close();

            System.out.println("Objects loaded successfully.");

            for (Student s : loadedStudents) {
                System.out.println("Roll No: " + s.id);
                System.out.println("Name: " + s.name);
                System.out.println("Password: " + s.password);
                System.out.println();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}