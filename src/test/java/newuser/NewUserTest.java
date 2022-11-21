package newuser;

import database.UserDbGateway;
import entities.FacilityType;
import entities.FacilityUser;
import entities.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Objects;

import newuser.*;
import adminmainmenu.*;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/* things to test:
    -sucessfully stores new user in database
    - does not create a new user when a user with the same username already exists
    - does not create a new user when the password is less than 5 characters
 */
public class NewUserTest {

    private NewUserController newUserController;

    private UserDbGateway userDB;

    @Before
    public void setup(){
        this.userDB = new UserDbGateway();
        userDB.fileReset();
        this.newUserController = new NewUserController(new NewUserInteractor(new NewUserPresenter(new NewUserViewModel(), new AdminMainMenuViewModel()), userDB));
    }

    @Test
    public void testCreateStoreUser(){
        userDB.fileReset();
        UUID uuid = UUID.randomUUID();
        FacilityUser user = new FacilityUser("Store User", "Password", uuid, FacilityType.STORE);
        newUserController.createStoreUser(user.getUsername(), user.getPassword(), user.getFacilityID());
        Assertions.assertTrue(userDB.getUser("Store User").getUsername().equals(user.getUsername()));
        Assertions.assertTrue(userDB.getUser("Store User").getPassword().equals(user.getPassword()));
        Assertions.assertTrue(((FacilityUser) userDB.getUser("Store User")).getFacilityID().equals(user.getFacilityID()));
        Assertions.assertTrue(((FacilityUser) userDB.getUser("Store User")).getType().equals(FacilityType.STORE));
    }

    @Test
    public void testCreateWarehouseUser(){
        userDB.fileReset();
        UUID uuid = UUID.randomUUID();
        FacilityUser user = new FacilityUser("Warehouse User", "Password", uuid, FacilityType.WAREHOUSE);
        newUserController.createWarehouseUser(user.getUsername(), user.getPassword(), user.getFacilityID());
        Assertions.assertTrue(userDB.getUser("Warehouse User").getUsername().equals(user.getUsername()));
        Assertions.assertTrue(userDB.getUser("Warehouse User").getPassword().equals(user.getPassword()));
        Assertions.assertTrue(((FacilityUser) userDB.getUser("Warehouse User")).getFacilityID().equals(user.getFacilityID()));
        Assertions.assertTrue(((FacilityUser) userDB.getUser("Warehouse User")).getType().equals(FacilityType.WAREHOUSE));
    }

    @Test
    public void testExistingUsername(){
        userDB.fileReset();
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        newUserController.createStoreUser("User", "Password", uuid1);

        // attempt to create a new user with the same username
        newUserController.createWarehouseUser("User", "Password2", uuid2);
        // check that the registered user is the first user, which is a store user (is there a better method?)
        assertTrue(((FacilityUser) userDB.getUser("User")).getType().equals(FacilityType.STORE));
    }

    @Test
    public void testInvalidPassword(){
        userDB.fileReset();
        UUID uuid = UUID.randomUUID();

        // attempt to create a user with a password that has less than 5 characters
        newUserController.createStoreUser("User", "PW", uuid);

        // check that no user under that username is created
        assertTrue(userDB.getUser("User") == null);
    }
}
