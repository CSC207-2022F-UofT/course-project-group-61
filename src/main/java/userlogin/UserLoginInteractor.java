package userlogin;

import database.UserDb;
import entities.User;

public class UserLoginInteractor implements UserLoginInputBoundary {

    private final UserDb userDb;
    private final UserLoginOutputBoundary presenter;
    public UserLoginInteractor(UserLoginOutputBoundary presenter, UserDb userDb) {
        this.userDb = userDb;
        this.presenter = presenter;
    }
    @Override
    public UserLoginResponseModel login(UserLoginRequestModel request) {
        User user = userDb.getUser(request.getUsername());
        UserLoginResponseModel response;
        if (user == null) {
            response = new UserLoginResponseModel(null, LoginStatus.INVALID_USER);
            presenter.prepareFailView(response);
        } else {
            if (user.getPassword().equals(request.getPassword())) {
                response = new UserLoginResponseModel(user, LoginStatus.SUCCESS);
                presenter.prepareSuccessView(response);
            } else {
                response = new UserLoginResponseModel(null, LoginStatus.INCORRECT_PASSWORD);
                presenter.prepareFailView(response);
            }
        }
        return response;
    }
}
