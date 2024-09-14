import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Customer extends User {
    private double balance;

    public Customer(String username, String password) {
        super(username, password);
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. Current balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}

class Employee extends User {
    private String employeeId;
    private String position;

    public Employee(String username, String password, String employeeId, String position) {
        super(username, password);
        this.employeeId = employeeId;
        this.position = position;
    }

    public void viewProfile() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + getUsername());
        System.out.println("Position: " + position);
    }
}

class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }
    
    public void viewProfile(User user) {
        if (user instanceof Customer) {
            // Display customer profile
            System.out.println("Customer Profile");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Balance: " + ((Customer) user).getBalance());
        } else if (user instanceof Employee) {
            // Display employee profile
            ((Employee) user).viewProfile();
        }
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, User> users = new HashMap<>();

        // Dummy data
        Customer customer = new Customer("xyz", "1234");
        users.put("xyz", customer);

        Employee employee = new Employee("abc", "6789", "102", "Manager");
        users.put("abc", employee);

        Admin admin = new Admin("np", "456");
        users.put("np", admin);

        System.out.println("Welcome to the Bank Management System!");

        while (true) {
            System.out.println("1. Customer Login");
            System.out.println("2. Employee Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    // Customer login
                    System.out.print("Enter customer username: ");
                    String customerUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String customerPassword = scanner.nextLine();

                    if (users.containsKey(customerUsername) &&
                            users.get(customerUsername) instanceof Customer &&
                            users.get(customerUsername).getPassword().equals(customerPassword)) 
                            {
                        Customer loggedInCustomer = (Customer) users.get(customerUsername);
                        customerOperations(scanner, loggedInCustomer);
                    }
                     else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;

                case 2:
                    // Employee login
                    System.out.print("Enter employee username: ");
                    String employeeUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String employeePassword = scanner.nextLine();

                    if (users.containsKey(employeeUsername) &&
                            users.get(employeeUsername) instanceof Employee &&
                            users.get(employeeUsername).getPassword().equals(employeePassword)) {
                        Employee loggedInEmployee = (Employee) users.get(employeeUsername);
                        employeeOperations(scanner, loggedInEmployee);
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;

                case 3:
                    // Admin login
                    System.out.print("Enter admin username: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String adminPassword = scanner.nextLine();

                    if (users.containsKey(adminUsername) &&
                            users.get(adminUsername) instanceof Admin &&
                            users.get(adminUsername).getPassword().equals(adminPassword)) {
                        Admin loggedInAdmin = (Admin) users.get(adminUsername);
                        adminOperations(scanner, loggedInAdmin, users);
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting Bank Management System. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void customerOperations(Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("Customer Operations:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    // Deposit
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    customer.deposit(depositAmount);
                    break;

                case 2:
                    // Withdraw
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    customer.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.println("Logging out. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void employeeOperations(Scanner scanner, Employee employee) {
        while (true) {
            System.out.println("Employee Operations:");
            System.out.println("1. View Profile");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    // View Profile
                    employee.viewProfile();
                    break;

                case 2:
                    System.out.println("Logging out. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void adminOperations(Scanner scanner, Admin admin, Map<String, User> users) {
        while (true) {
            System.out.println("Admin Operations:");
            System.out.println("1. View Customer Profile");
            System.out.println("2. View Employee Profile");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline
        
        }
    }
}


