package entities;

import java.util.Date;

public abstract class User {
    private final String username;

    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("TestForcePush");
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
