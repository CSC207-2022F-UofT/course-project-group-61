package newuser;

import adminmainmenu.AdminMainMenuViewModel;
import database.UserDbGateway;
import database.FacilityDbGateway;
import entities.FacilityType;
import entities.FacilityUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

import static org.junit.Assert.*;

/* things to test:
    - successfully stores new user in database
    - does not create a new user when a user with the same username already exists
    - does not create a new user when the password is less than 5 characters
 */
public class NewUserTest {

    private NewUserController newUserController;

    private UserDbGateway userDB;

    private FacilityDbGateway facilityDB;

    @Before
    public void setup(){
        this.userDB = new UserDbGateway();
        userDB.fileReset();
        this.newUserController = new NewUserController(new NewUserInteractor(new NewUserPresenter(new NewUserViewModel(), new AdminMainMenuViewModel()), userDB, facilityDB));
    }

    @Test
    public void testCreateStoreUser(){
        userDB.fileReset();
        UUID uuid = UUID.randomUUID();
        FacilityUser user = new FacilityUser("Store User", "Password", uuid, FacilityType.STORE);
        newUserController.createStoreUser(user.getUsername(), user.getPassword(), user.getFacilityID());
        Assertions.assertEquals(userDB.getUser("Store User").getUsername(), user.getUsername());
        Assertions.assertEquals(userDB.getUser("Store User").getPassword(), user.getPassword());
        Assertions.assertEquals(((FacilityUser) userDB.getUser("Store User")).getFacilityID(), user.getFacilityID());
        Assertions.assertEquals(((FacilityUser) userDB.getUser("Store User")).getType(), FacilityType.STORE);
    }

    @Test
    public void testCreateWarehouseUser(){
        userDB.fileReset();
        UUID uuid = UUID.randomUUID();
        FacilityUser user = new FacilityUser("Warehouse User", "Password", uuid, FacilityType.WAREHOUSE);
        newUserController.createWarehouseUser(user.getUsername(), user.getPassword(), user.getFacilityID());
        Assertions.assertEquals(userDB.getUser("Warehouse User").getUsername(), user.getUsername());
        Assertions.assertEquals(userDB.getUser("Warehouse User").getPassword(), user.getPassword());
        Assertions.assertEquals(((FacilityUser) userDB.getUser("Warehouse User")).getFacilityID(), user.getFacilityID());
        Assertions.assertEquals(((FacilityUser) userDB.getUser("Warehouse User")).getType(), FacilityType.WAREHOUSE);
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
        assertEquals(((FacilityUser) userDB.getUser("User")).getType(), FacilityType.STORE);
    }

    @Test
    public void testInvalidPassword(){
        userDB.fileReset();
        UUID uuid = UUID.randomUUID();

        // attempt to create a user with a password that has less than 5 characters
        newUserController.createStoreUser("User", "PW", uuid);

        // check that no user under that username is created
        assertNull(userDB.getUser("User"));
    }
}
