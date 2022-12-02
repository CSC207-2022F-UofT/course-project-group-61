package newuser;

import entities.Facility;

import java.util.ArrayList;
import java.util.UUID;

public interface NewUserInputBoundary {
    NewUserResponseModel addNewUser(NewUserRequestModel request);

    public ArrayList<ArrayList<UUID>> getFacilityUUIDLists();
}
