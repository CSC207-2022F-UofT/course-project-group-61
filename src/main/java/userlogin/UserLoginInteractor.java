package userlogin;

import database.UserDb;
import entities.User;

public class UserLoginInteractor implements UserLoginInputBoundary {

    private final UserDb userDb;
    public UserLoginInteractor(UserLoginPresenter presenter, UserDb userDb) {
        this.userDb = userDb;
    }
    @Override
    public UserLoginResponseModel login(UserLoginRequestModel request) {
        User user = userDb.getUser(request.getUsername());
        UserLoginResponseModel response;
        if (user == null) {
            response = new UserLoginResponseModel(null, LoginStatus.INVALID_USER);
        } else {
            if (user.getPassword().equals(request.getPassword())) {
                response = new UserLoginResponseModel(user, LoginStatus.SUCCESS);
            } else {
                response = new UserLoginResponseModel(null, LoginStatus.INCORRECT_PASSWORD);
            }
        }
        return response;
    }
}
