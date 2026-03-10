import java.util.*;

public class UsernameSystem {

    static HashMap<String, Integer> users = new HashMap<>();
    static HashMap<String, Integer> attempts = new HashMap<>();

    static boolean checkAvailability(String username) {

        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        if (users.containsKey(username))
            return false;

        return true;
    }

    static void suggestAlternatives(String username) {

        for (int i = 1; i <= 3; i++) {
            String suggestion = username + i;

            if (!users.containsKey(suggestion))
                System.out.println(suggestion);
        }
    }

    static void getMostAttempted() {

        String name = "";
        int max = 0;

        for (String key : attempts.keySet()) {

            if (attempts.get(key) > max) {
                max = attempts.get(key);
                name = key;
            }
        }

        System.out.println("Most attempted: " + name + " (" + max + " attempts)");
    }

    public static void main(String[] args) {


        users.put("john_doe", 1);
        users.put("admin", 2);

        System.out.println(checkAvailability("john_doe")); 
        System.out.println(checkAvailability("jane"));     

        System.out.println("Suggestions:");
        suggestAlternatives("john_doe");

        checkAvailability("admin");
        checkAvailability("admin");

        getMostAttempted();
    }
}