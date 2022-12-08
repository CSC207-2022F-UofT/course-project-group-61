package userlogin;

import database.UserDb;
import entities.User;
import entities.UserSession;

/* Use case for logging into the application. */
public class UserLoginInteractor implements UserLoginInputBoundary {

    private final UserDb userDb;
    private final UserLoginOutputBoundary presenter;
    public UserLoginInteractor(UserLoginOutputBoundary presenter, UserDb userDb) {
        this.userDb = userDb;
        this.presenter = presenter;
    }

    /* Login method to check the requested credentials against the database and update the view accordingly. */
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
                UserSession.setUserSession(user);
                presenter.prepareSuccessView(response);
            } else {
                response = new UserLoginResponseModel(null, LoginStatus.INCORRECT_PASSWORD);
                presenter.prepareFailView(response);
            }
        }
        return response;
    }
}
