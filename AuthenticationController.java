package phase3;

import java.util.Random;
import java.util.Scanner;

public class AuthenticationController {
    private static String currentOTP;

    public AuthenticationController() {}

    public static void signUp() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (isValid(email, phoneNumber)) {
            if (UserDB.emailExists(email)) {
                showError("Email already exists.");
            } else {
                currentOTP = generateOTP();
                System.out.println("\uD83D\uDCF2 Your OTP is: " + currentOTP);
                System.out.print("Enter the OTP: ");
                String otp = scanner.nextLine();
                
                if (verifyOTP(otp)) {
                    try {
                        User user = User.submitSignUpForm(username, email, phoneNumber, password);
                        UserDB.saveUser(user);
                        System.out.println("✅ Account created and saved!");
                    } catch (IllegalArgumentException ex) {
                        showError(ex.getMessage());
                    }
                } else {
                    showError("OTP verification failed.");
                }
            }
        }
    }

    public static void login() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = User.submitLoginForm(email, password);
        if (user != null) {
            System.out.println("✅ Login successful! Welcome, " + user.getUsername());
        } else {
            showError("Invalid email or password.");
        }
    }

    public static boolean isValid(String email, String phoneNumber) {
        return enterCorrectFormat(email) && checkDataFormat(phoneNumber);
    }

    public static boolean enterCorrectFormat(String email) {
        if (email.contains("@") && email.contains(".")) {
            return true;
        } else {
            showError("Invalid email format.");
            return false;
        }
    }

    public static boolean checkDataFormat(String phoneNumber) {
        if (!phoneNumber.matches("\\d+")) {
            showError("Phone number must contain only digits.");
            return false;
        }
        return true;
    }

    public static void showError(String message) {
        System.out.println("❌ ERROR: " + message);
    }

    private static String generateOTP() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }

    private static boolean verifyOTP(String otp) {
        return currentOTP != null && currentOTP.equals(otp);
    }

 
}
