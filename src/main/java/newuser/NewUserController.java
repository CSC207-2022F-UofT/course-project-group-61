package newuser;

import entities.FacilityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class NewUserController {

    private final NewUserInputBoundary inputBoundary;

    public NewUserController(NewUserInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public NewUserResponseModel createStoreUser(String username, String password, UUID facilityID) {
        NewUserRequestModel request = new NewUserRequestModel(username, password, facilityID, FacilityType.STORE);
        return inputBoundary.addNewUser(request);
    }
    public NewUserResponseModel createWarehouseUser(String username, String password, UUID facilityID) {
        NewUserRequestModel request = new NewUserRequestModel(username, password, facilityID,
                FacilityType.WAREHOUSE);
        return inputBoundary.addNewUser(request);
    }

    public ArrayList<HashMap<String, UUID>> getFacilityUUIDLists(){
        return inputBoundary.getFacilityLists();
    }

    public void returnToMenu() {
        inputBoundary.returnToMenu();
    }
}
