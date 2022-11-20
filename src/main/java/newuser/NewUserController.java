package newuser;

import entities.FacilityType;
import entities.User;
import java.util.UUID;

public class NewUserController {

    private final NewUserInteractor newUserInteractor = new NewUserInteractor();

    public User createStoreUser(String username, String password, UUID facilityID){
        return newUserInteractor.addNewUser(username, password, facilityID, FacilityType.STORE);
    }

    public User createWarehouseUser(String username, String password, UUID facilityID){
        return newUserInteractor.addNewUser(username, password, facilityID, FacilityType.WAREHOUSE);
    }
}
