package at.fhtw.sampleapp.model;
import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private static UserStore instance;
    private Map<String, String> userMap = new HashMap<>(); // Benutzername -> Passwort

    // Singleton-Muster, um nur eine Instanz zu haben
    public static UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
        }
        return instance;
    }

    // Benutzer registrieren
    public void addUser(String username, String password) {
        userMap.put(username, password);
    }

    // Benutzer abrufen
    public String getPassword(String username) {
        return userMap.get(username);
    }

    // Überprüfen, ob der Benutzer existiert
    public boolean userExists(String username) {
        return userMap.containsKey(username);
    }
}
