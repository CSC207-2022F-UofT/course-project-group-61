package database;

import entities.User;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;

public class UserDbGateway implements UserDb {

    private final DBReadWriter db;

    public UserDbGateway() {
        String USER_FILE_PATH = "data/users.ser";
        this.db = new DBReadWriter(USER_FILE_PATH);
    }

    @Override
    public HashMap<String, User> getAllUsers() {
        try {
            return (HashMap<String, User>) db.read();
        } catch (EOFException eof) {
            HashMap<String, User> tempMap = new HashMap<>();
            try {
                this.db.write(tempMap);
                return (HashMap<String, User>) db.read();
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUser(String username) {
        try {
            HashMap<String, User> users = getAllUsers();
            return users.get(username);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            HashMap<String, User> users = getAllUsers();
            users.put(user.getUsername(), user);
            db.write(users);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //for testing purposes
    public void fileReset() {
        try {
            HashMap<String, User> newHash = new HashMap<>();
            db.write(newHash);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
