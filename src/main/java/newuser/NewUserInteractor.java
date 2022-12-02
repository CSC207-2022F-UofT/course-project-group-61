package newuser;

import database.FacilityDb;
import database.UserDb;
import entities.Facility;
import entities.FacilityType;
import entities.FacilityUser;
import entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class NewUserInteractor implements NewUserInputBoundary{

    private final UserDb userDB;
    private final FacilityDb facilityDb;
    private final NewUserOutputBoundary presenter;
    public NewUserInteractor(NewUserOutputBoundary presenter, UserDb userDb, FacilityDb facilityDB){
        this.userDB = userDb;
        this.facilityDb = facilityDB;
        this.presenter = presenter;
    }

    @Override
    public NewUserResponseModel addNewUser(NewUserRequestModel request){

        User newUser = new FacilityUser(request.getUsername(), request.getPassword(),
                request.getFacilityID(), request.getType());

        NewUserResponseModel response;

        // Empty username - failure
        if (userDB.getUser(request.getUsername()) != null){
            response = new NewUserResponseModel(null, NewUserStatus.USERNAME_EXISTS);
            presenter.prepareFailView(response);
            return response;
        }
        // Password too short - failure
        else if (request.getPassword().length() < 5){
            response = new NewUserResponseModel(null, NewUserStatus.PASSWORD_TOO_SHORT);
            presenter.prepareFailView(response);
            return response;
        }
        // No registered facilities of the selected facility type - failure
        else if (request.getFacilityID() == null){
            response = new NewUserResponseModel(null, NewUserStatus.NO_FACILITIES);
            presenter.prepareFailView(response);
            return response;
        }
        // Success
        else {
            userDB.updateUser(newUser);
            response = new NewUserResponseModel(newUser, NewUserStatus.SUCCESS);
            presenter.prepareSuccessView(response);
            return response;
        }
    }

    @Override
    // returns an array list of two dictionaries mapping a String (facility name) to the facility UUID.
    public ArrayList<HashMap<String, UUID>> getFacilityLists() {
        // Hashmap mapping a store name to its UUID.
        HashMap<String, UUID> storeMap = new HashMap<>();
        // Hashmap mapping a warehouse name to its UUID.
        HashMap<String, UUID> warehouseMap = new HashMap<>();
        // Filling both hashmaps. The array will include all the facilities in the database.
        for (Map.Entry<UUID, Facility> facilityEntry: facilityDb.getAllFacilities().entrySet()){
            if (facilityEntry.getValue().getFacilityType() == FacilityType.STORE){
                storeMap.put(facilityEntry.getValue().getName(), facilityEntry.getKey());
            } else if (facilityEntry.getValue().getFacilityType() == FacilityType.WAREHOUSE) {
                warehouseMap.put(facilityEntry.getValue().getName(), facilityEntry.getKey());
            }
            // leave open for any enhancements regarding new user/facility types.
        }
        // Create and fill the arraylist.
        ArrayList<HashMap<String, UUID>> facilityList = new ArrayList<>();
        facilityList.add(storeMap);
        facilityList.add(warehouseMap);
        return facilityList;
    }
}
