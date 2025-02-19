import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int id;
    private String designation;
    private double salary;

    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [ID: " + id + ", Name: " + name + 
               ", Designation: " + designation + ", Salary: " + salary + "]";
    }
}

public class EmployeeManagementSystem {
    private static final String FILE_NAME = "employees.dat";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayAllEmployees();
                    break;
                case 3:
                    System.out.println("Exiting the application...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Employee Management System ===");
        System.out.println("1. Add an Employee");
        System.out.println("2. Display All");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addEmployee() {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter designation: ");
        String designation = scanner.nextLine();
        
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(name, id, designation, salary);
        
        ArrayList<Employee> employees = readExistingEmployees();
        employees.add(employee);

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
            System.out.println("Employee added successfully!");
        } catch (IOException e) {
            System.out.println("Error while saving employee: " + e.getMessage());
        }
    }

    private static void displayAllEmployees() {
        ArrayList<Employee> employees = readExistingEmployees();
        
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        System.out.println("\nEmployee Details:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Employee> readExistingEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return employees;
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_NAME))) {
            employees = (ArrayList<Employee>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing employee records found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading employee records: " + e.getMessage());
        }
        
        return employees;
    }
}
