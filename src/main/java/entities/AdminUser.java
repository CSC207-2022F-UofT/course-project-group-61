package entities;

/* Identical to abstract parent class, has type of AdminUser to give it access to the AdminMainMenu. */
public class AdminUser extends User {

    public AdminUser(String username, String password) {
        super(username, password);
    }
}
