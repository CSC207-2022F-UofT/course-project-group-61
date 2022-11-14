package newuser;

import database.UserDbGateway;
import entities.FacilityUser;
import entities.User;
import entities.FacilityType;

import java.util.UUID;


public class NewUserInteractor{

    UserDbGateway readWriter = new UserDbGateway();

    public NewUserInteractor(){
    }

    public User addNewUser(String username, String password, UUID facilityID, FacilityType type){

        // changed facilityID in all classes to type UUID.
        User newUser = new FacilityUser(username, password, facilityID, type);
        if (readWriter.getUser(username) != null){
            return null;
        }
        boolean result = this.readWriter.updateUser(newUser);
        if (result){
            // return user or username?
            return newUser;
        }
        return null;
    }
}
