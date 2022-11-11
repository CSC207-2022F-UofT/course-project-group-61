package newuser;

import entities.FacilityType;
import entities.User;

public class NewUserController {

    private final NewUserInteractor newUserInteractor = new NewUserInteractor();

    public User createStoreUser(String username, String password, int facilityID){
        return newUserInteractor.addNewUser(username, password, facilityID, FacilityType.STORE);
    }

    public User createWarehouseUser(String username, String password, int facilityID){
        return newUserInteractor.addNewUser(username, password, facilityID, FacilityType.WAREHOUSE);
    }
}
