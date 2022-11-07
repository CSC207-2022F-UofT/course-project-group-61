package database;

import entities.User;

import java.util.HashMap;

public interface UserDb {

    HashMap<String, User> getAllUsers();

    User getUser(String username);

    boolean updateUser(User user);
}
