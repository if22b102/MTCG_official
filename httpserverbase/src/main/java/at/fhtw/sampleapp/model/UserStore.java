package at.fhtw.sampleapp.model;
import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private static UserStore instance;
    private Map<String, String> userMap = new HashMap<>(); // Benutzername -> Passwort

    public static UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
        }
        return instance;
    }

    public void addUser(String username, String password) {
        userMap.put(username, password);
    }

    public String getPassword(String username) {
        return userMap.get(username);
    }

    public boolean userExists(String username) {
        return userMap.containsKey(username);
    }
}
