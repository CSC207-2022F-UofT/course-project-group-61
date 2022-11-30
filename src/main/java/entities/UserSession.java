package entities;

import entities.User;

/* Holds information about the user that is currently logged in. */
public class UserSession {

    private static User userSession;

    public static void setUserSession(User user) {
        userSession = user;
    }

    public static User getUserSession() {
        return userSession;
    }
}
