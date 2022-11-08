package database;

import entities.Facility;
import entities.Product;
import entities.User;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class UserDbGateway implements UserDb {

    private final String USER_FILE_PATH = "data/users.ser";
    private final DBReadWriter db;

    public UserDbGateway() {
        this.db = new DBReadWriter(USER_FILE_PATH);
    }

    @Override
    public HashMap<String, User> getAllUsers() {
        try {
            return (HashMap<String, User>) db.read();
        } catch (EOFException eof) {
            HashMap<String, User> tempMap = new HashMap<String, User>();
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
            HashMap<String, User> newHash = new HashMap<String, User>();
            db.write(newHash);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
