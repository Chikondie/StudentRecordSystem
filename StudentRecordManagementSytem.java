import java.util.Scanner;

class Student {
    String name;
    String id; // ID can be alphanumeric
    int age;
    double grade;

    public Student(String name, String id, int age, double grade) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.grade = grade;
    }

    public void displayDetails() {
        System.out.println("Student Name: " + name);
        System.out.println("Student ID: " + id);
        System.out.println("Student Age: " + age);
        System.out.println("Student Grade: " + grade);
    }
}

class StudentManagement {
    static Student[] students = new Student[100];
    static int studentCount = 0;

    public static void addStudent(String name, String id, int age, double grade) {
        students[studentCount] = new Student(name, id, age, grade);
        studentCount++;
        System.out.println("Student added successfully.");
    }

    public static void updateStudent(String id, String newName, int newAge, double newGrade) {
        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].id.equals(id)) {
                students[i].name = newName;
                students[i].age = newAge;
                students[i].grade = newGrade;
                found = true;
                System.out.println("Student details updated successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Student ID not found.");
        }
    }

    public static void viewStudent(String id) {
        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].id.equals(id)) {
                students[i].displayDetails();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student ID not found.");
        }
    }
}

public class StudentRecordSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n=== Student Record Management System ===");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. View Student Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Please enter a valid input.");
                continue;
            }

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    // Validate student ID
                    String id;
                    while (true) {
                        System.out.print("Enter student ID number (e.g., CS1102 or 007383): ");
                        id = scanner.nextLine().trim();
                        if (id.matches("^[A-Za-z]*\\d+$")) { // ID must have at least one number
                            break;
                        } else {
                            System.out.println("Invalid ID format! Please enter a valid student ID.");
                        }
                    }

                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter student grade: ");
                    double grade = scanner.nextDouble();
                    scanner.nextLine(); 
                    StudentManagement.addStudent(name, id, age, grade);
                    break;
                case 2:
                    String updateId;
                    while (true) {
                        System.out.print("Enter student ID to update (e.g., CS1102 or 007383): ");
                        updateId = scanner.nextLine().trim();
                        if (updateId.matches("^[A-Za-z]*\\d+$")) {
                            break;
                        } else {
                            System.out.println("Invalid ID format! Please enter a valid student ID.");
                        }
                    }

                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new age: ");
                    int newAge = scanner.nextInt();
                    System.out.print("Enter new grade: ");
                    double newGrade = scanner.nextDouble();
                    scanner.nextLine(); 
                    StudentManagement.updateStudent(updateId, newName, newAge, newGrade);
                    break;
                case 3:
                    String viewId;
                    while (true) {
                        System.out.print("Enter student ID to view details (e.g., CS1102 or 007383): ");
                        viewId = scanner.nextLine().trim();
                        if (viewId.matches("^[A-Za-z]*\\d+$")) {
                            break;
                        } else {
                            System.out.println("Invalid ID format! Please enter a valid student ID.");
                        }
                    }
                    StudentManagement.viewStudent(viewId);
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}