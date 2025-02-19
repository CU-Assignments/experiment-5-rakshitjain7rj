// Student.java
import java.io.Serializable;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student [ID: " + id + ", Name: " + name + ", GPA: " + gpa + "]";
    }
}

// SerializationDemo.java
import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        // Create a student object
        Student student = new Student(1, "John Doe", 3.8);
        String filename = "student.ser";

        // Serialization
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(student);
            objectOut.close();
            fileOut.close();
            System.out.println("Student object serialized and saved to " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error during serialization: " + e.getMessage());
        }

        // Deserialization
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Student deserializedStudent = (Student) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Deserialized Student Details:");
            System.out.println(deserializedStudent);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error during deserialization: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }
}
