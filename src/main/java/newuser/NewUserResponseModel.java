package newuser;

import entities.FacilityUser;
import entities.User;

public class NewUserResponseModel {

    private final User user;
    private final NewUserStatus status;

    public NewUserResponseModel(User user, NewUserStatus status){
        this.user = user;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public NewUserStatus getStatus() {
        return status;
    }
}
