package userlogin;

import entities.User;

/* Data structure for the response of a call to the login interactor. */
public class UserLoginResponseModel {

    private final User user;

    private final LoginStatus status;

    public UserLoginResponseModel(User user, LoginStatus status) {
        this.user = user;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public LoginStatus getStatus() {
        return status;
    }
}
