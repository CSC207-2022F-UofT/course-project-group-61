package entities;

import entities.User;

public class UserSession {

    private static User userSession;

    public static void setUserSession(User user) {
        userSession = user;
    }

    public static User getUserSession() {
        return userSession;
    }
}
