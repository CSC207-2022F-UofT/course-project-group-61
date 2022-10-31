package entities;

public abstract class User {
    private final String username;

    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("Test Again");
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
