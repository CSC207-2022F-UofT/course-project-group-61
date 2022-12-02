package entities;

import java.io.Serializable;

/* User can login to the program and do various things based on their type. */
public abstract class User implements Serializable {
    private final String username;

    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
