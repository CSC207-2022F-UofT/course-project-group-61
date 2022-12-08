package newuser;

import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;

public interface NewUserInputBoundary {
    NewUserResponseModel addNewUser(NewUserRequestModel request);

    ArrayList<HashMap<String, UUID>> getFacilityLists();

    void returnToMenu();
}
