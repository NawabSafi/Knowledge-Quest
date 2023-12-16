package com.example.semesterproject.SignUp;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {

    // Hardcoded user credentials (for educational purposes only)
    private static final Map<String, String> userCredentials = new HashMap<>();

    /**
     * Authenticate the user.
     *
     * @param username Entered username
     * @param password Entered password
     * @return True if authentication is successful, false otherwise
     */
    public static boolean authenticate(String username, String password) {
        // For educational purposes, compare the entered credentials with stored values
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    /**
     * Check if a username is already taken.
     *
     * @param username The username to check
     * @return True if the username is already taken, false otherwise
     */
    public static boolean isUsernameTaken(String username) {
        return userCredentials.containsKey(username);
    }

    /**
     * Sign up a new user.
     *
     * @param username The new username
     * @param password The new password
     */
    public static void signup(String username, String password) {
        // For educational purposes, store the new user credentials
        userCredentials.put(username, password);
    }
}
