import java.util.*;
import java.io.*;

class LoginSystem {
    private Map<String, String> userDB = new HashMap<>();
    private Scanner sc = new Scanner(System.in);
    private final int MAX_ATTEMPTS = 3;

    public void register() {
        System.out.print("Enter a username: ");
        String username = sc.nextLine();

        if (userDB.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }

        System.out.print("Enter a password: ");
        String password = sc.nextLine();

        if (!isStrongPassword(password)) {
            System.out.println("Weak password! Use at least 8 characters, with uppercase, lowercase, and numbers.");
            return;
        }

        userDB.put(username, password);
        System.out.println("Registration Successful!");
    }

    public void login() {
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            if (userDB.containsKey(username) && userDB.get(username).equals(password)) {
                System.out.println("Login Successful! Welcome " + username);
                userMenu(username);
                return;
            } else {
                attempts++;
                System.out.println("Invalid credentials! Attempts left: " + (MAX_ATTEMPTS - attempts));
            }
        }

        System.out.println("Too many failed attempts! Try again later.");
    }

    private void userMenu(String username) {
        while (true) {
            System.out.print("\n--- User Menu ---\n1. Logout\n2. Exit\nChoose an option: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Logged out!");
                    return;
                case 2:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private boolean isStrongPassword(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*\\d.*");
    }

    public void run() {
        while (true) {
            System.out.print("\n--- Main Menu ---\n1. Register\n2. Login\n3. Exit\nChoose an option: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: register(); break;
                case 2: login(); break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void main(String[] args) {
        new LoginSystem().run();
    }
}
