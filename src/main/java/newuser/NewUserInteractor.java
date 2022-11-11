package newuser;

import database.UserDbGateway;
import entities.FacilityUser;
import entities.User;
import entities.FacilityType;


public class NewUserInteractor{

    UserDbGateway readWriter = new UserDbGateway();

    public NewUserInteractor(){
    }

    public User addNewUser(String username, String password, int facilityID, FacilityType type){

        // FacilityUser has type int facilityID, but Facility has type UUID facilityID, does this matter?
        // Can int and UUID be compared/be checked for the same number sequence?

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
