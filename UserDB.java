package phase3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private static final String FILE_PATH = "users.json";

    public static void saveUser(User user) {
        try {
            StringBuilder content = new StringBuilder("[");

            boolean firstEntry = true;
            if (Files.exists(Paths.get(FILE_PATH))) {
                String existing = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
                if (existing.length() > 2) { // If not empty array
                    content.append(existing.substring(1, existing.length() - 1));
                    firstEntry = false;
                }
            }

            if (!firstEntry) {
                content.append(",");
            }

            content.append(userToJsonString(user));
            content.append("]");

            Files.write(Paths.get(FILE_PATH), content.toString().getBytes());
        } catch (Exception e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    private static String userToJsonString(User user) {
        return String.format(
            "{\"username\":\"%s\",\"email\":\"%s\",\"phoneNumber\":\"%s\",\"password\":\"%s\"}",
            user.getUsername(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getPassword()
        );
    }

    public static boolean emailExists(String email) {
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) return false;

            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            return content.contains("\"email\":\"" + email + "\"");
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false;
    }

    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) return users;

            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            String[] userStrings = content.substring(1, content.length() - 1).split("\\},\\{");

            for (String userStr : userStrings) {
                users.add(parseUser("{" + userStr + "}"));
            }
        } catch (Exception e) {
            System.out.println("Error reading users: " + e.getMessage());
        }
        return users;
    }

    private static User parseUser(String jsonStr) {
        String username = extractValue(jsonStr, "username");
        String email = extractValue(jsonStr, "email");
        String phone = extractValue(jsonStr, "phoneNumber");
        String password = extractValue(jsonStr, "password");
        return new User(username, password, email, phone);
    }

    private static String extractValue(String json, String key) {
        int start = json.indexOf("\"" + key + "\":\"") + key.length() + 4;
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
}
