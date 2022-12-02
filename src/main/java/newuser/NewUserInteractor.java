package newuser;

import database.UserDb;
import database.FacilityDb;
import entities.Facility;
import entities.FacilityType;
import entities.FacilityUser;
import entities.User;

import java.sql.Array;
import java.util.*;


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

        if (userDB.getUser(request.getUsername()) != null){
            response = new NewUserResponseModel(null, NewUserStatus.USERNAME_EXISTS);
            presenter.prepareFailView(response);
            return response;
        }
        else if (request.getPassword().length() < 5){
            response = new NewUserResponseModel(null, NewUserStatus.PASSWORD_TOO_SHORT);
            presenter.prepareFailView(response);
            return response;
        }
        else {
            userDB.updateUser(newUser);
            response = new NewUserResponseModel(newUser, NewUserStatus.SUCCESS);
            presenter.prepareSuccessView(response);
            return response;
        }
    }

    @Override
    // returns two list of UUIDs (one for stores and one for warehouses)
    public ArrayList<ArrayList<UUID>> getFacilityUUIDLists() {
        ArrayList<UUID> storeUUIDList = new ArrayList<>();
        ArrayList<UUID> warehouseUUIDList = new ArrayList<>();
        HashMap<UUID, Facility> test = facilityDb.getAllFacilities();
        for (Map.Entry<UUID, Facility> facilityEntry: facilityDb.getAllFacilities().entrySet()){
            if (facilityEntry.getValue().getFacilityType() == FacilityType.STORE){
                storeUUIDList.add(facilityEntry.getKey());
            } else if (facilityEntry.getValue().getFacilityType() == FacilityType.WAREHOUSE) {
                warehouseUUIDList.add(facilityEntry.getKey());
            }
        }
        ArrayList<ArrayList<UUID>> uuidList = new ArrayList<>();
        uuidList.add(storeUUIDList);
        uuidList.add(warehouseUUIDList);
        return uuidList;
    }
}
