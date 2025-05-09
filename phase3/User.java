package phase3;
import java.util.List;

public class User {
    private String Username;
    private String Password;
    private String email;
    private String phoneNumber;

    public User(String Username, String Password, String email, String phoneNumber) {
        this.Username = Username;
        this.email = email;
        this.Password = Password;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public String getUsername() { return Username; }
    public String getPassword() { return Password; }
    public String getPhoneNumber() { return phoneNumber; }

    public void setEmail(String email) { this.email = email; }
    public void setUsername(String username) { this.Username = username; }
    public void setPassword(String password) { this.Password = password; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    // JSON Conversion Methods
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("username", Username);
        json.put("email", email);
        json.put("phoneNumber", phoneNumber);
        json.put("password", Password);
        return json;
    }

    public static User fromJSON(JSONObject json) {
        return new User(
            json.getString("username"),
            json.getString("password"),
            json.getString("email"),
            json.getString("phoneNumber")
        );
    }
    public static User submitSignUpForm(String username, String email, String phoneNumber, String password) {
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid phone format");
        }

        return new User(username, password, email, phoneNumber);
    }

    public static User submitLoginForm(String email, String password) {
        List<User> users = UserDB.readUsers();

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "Username='" + Username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}