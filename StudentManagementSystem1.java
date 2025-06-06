import java.util.*;

public class StudentManagementSystem1 {
    static String[] existingUserIds = {"admin", "student1", "user2", "guest", "teacher1"};
    static String[] existingPasswords = {"admin123", "pass1", "user2pass", "guest123", "teach@123"};

    static String[] userIds = new String[20];
    static String[] passwords = new String[20];
    static int userCount = 0;

    static String[] studentNames = {"Alice", "Bob", "Charlie", "David", "Eva"};
    static int[] studentMarks = {85, 92, 76, 63, 88};
    static boolean[] feesSubmitted = {true, false, true, false, true};
    static String[][] studentCourses = {
        {"Math", "Science"},
        {"English", "History"},
        {"Biology", "Chemistry"},
        {"Math", "Computer"},
        {"Physics", "Economics"}
    };

    public static void main(String[] args) {
        for (int i = 0; i < existingUserIds.length; i++) {
            userIds[userCount] = existingUserIds[i];
            passwords[userCount] = existingPasswords[i];
            userCount++;
        }

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. New User\n2. Existing User\n0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    registerUser(sc);
                    break;
                case 2:
                    if (loginUser(sc)) {
                        studentMenu(sc);
                    }
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }

    public static void registerUser(Scanner sc) {
        if (userCount >= userIds.length) {
            System.out.println("User registration full.");
            return;
        }

        System.out.print("Enter new User ID: ");
        String newUser = sc.nextLine();

        for (int i = 0; i < userCount; i++) {
            if (userIds[i].equals(newUser)) {
                System.out.println("User ID already exists. Please choose a different ID.");
                return;
            }
        }

        String newPass, confirmPass;
        while (true) {
            System.out.print("Enter new Password: ");
            newPass = sc.nextLine();
            System.out.print("Confirm Password: ");
            confirmPass = sc.nextLine();

            if (newPass.equals(confirmPass)) {
                break;
            } else {
                System.out.println("Passwords do not match. Please try again.\n");
            }
        }

        userIds[userCount] = newUser;
        passwords[userCount] = newPass;
        userCount++;

        System.out.println("User registered successfully!");
    }

    public static boolean loginUser(Scanner sc) {
        System.out.print("Enter User ID: ");
        String user = sc.nextLine().trim();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine().trim();

        for (int i = 0; i < userCount; i++) {
            if (userIds[i].equals(user) && passwords[i].equals(pass)) {
                System.out.println("Login successful!\n");
                return true;
            }
        }

        System.out.println("Invalid credentials.\n");
        return false;
    }

    public static void studentMenu(Scanner sc) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Show Student Names");
            System.out.println("2. Show Student Marks");
            System.out.println("3. Students Who Submitted Fees");
            System.out.println("4. Students Who Have Not Submitted Fees");
            System.out.println("5. Students and Their Courses");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");
            int opt = sc.nextInt();

            switch (opt) {
                case 1:
                    System.out.println("Student Names:");
                    for (String name : studentNames) {
                        System.out.println("- " + name);
                    }
                    break;

                case 2:
                    System.out.println("Student Marks:");
                    for (int i = 0; i < studentNames.length; i++) {
                        System.out.println(studentNames[i] + ": " + studentMarks[i] + " marks");
                    }
                    break;

                case 3:
                    System.out.println("Students Who Submitted Fees:");
                    for (int i = 0; i < studentNames.length; i++) {
                        if (feesSubmitted[i]) {
                            System.out.println("- " + studentNames[i]);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Students Who Have Not Submitted Fees:");
                    for (int i = 0; i < studentNames.length; i++) {
                        if (!feesSubmitted[i]) {
                            System.out.println("- " + studentNames[i]);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Students and Their Courses:");
                    for (int i = 0; i < studentNames.length; i++) {
                        System.out.print(studentNames[i] + ": ");
                        for (String course : studentCourses[i]) {
                            System.out.print(course + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 0:
                    System.out.println("Logging out...\n");
                    loggedIn = false;
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
