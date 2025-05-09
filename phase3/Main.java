package phase3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Sign Up");
        System.out.println("2. Login");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            AuthenticationController.signUp();  
        } else if (choice == 2) {
            AuthenticationController.login();  
        } else {
            AuthenticationController.showError("Invalid choice.");  
        }
    }
}
