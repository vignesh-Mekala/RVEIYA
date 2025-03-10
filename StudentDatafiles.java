import java.io.*;
import java.util.*;

// Custom Exception for Student Not Found
class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}

// Student Record System
class StudentRecordSystem {
    private static final String FILE_NAME = "students.txt";

    // Add Student Record
    public static void addStudent(String id, String name, int age, String grade) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            pw.println(id + "," + name + "," + age + "," + grade);
            System.out.println("Student added successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // View All Students
    public static void viewStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("---- Student Records ----");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("ID: " + data[0] + " | Name: " + data[1] + " | Age: " + data[2] + " | Grade: " + data[3]);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Search Student by ID
    public static void searchStudent(String id) {
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(id)) {
                    System.out.println("Student Found: " + "ID: " + data[0] + ", Name: " + data[1] + ", Age: " + data[2] + ", Grade: " + data[3]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new StudentNotFoundException("Student with ID " + id + " not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete Student by ID
    public static void deleteStudent(String id) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals(id)) {
                    bw.write(line + System.lineSeparator());
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }

        // If student was found, replace the file
        if (found) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                System.out.println("Student with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Error updating records.");
            }
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }
}

// Main Class for User Interaction
public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Student Record System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    StudentRecordSystem.addStudent(id, name, age, grade);
                    break;
                case 2:
                    StudentRecordSystem.viewStudents();
                    break;
                case 3:
                    System.out.print("Enter Student ID to Search: ");
                    String searchId = sc.nextLine();
                    StudentRecordSystem.searchStudent(searchId);
                    break;
                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    String deleteId = sc.nextLine();
                    StudentRecordSystem.deleteStudent(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting Student Record System.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}
