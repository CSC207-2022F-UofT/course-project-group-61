package userlogin;

/* Used to call the login interactor with details from the view. */
public class UserLoginController {

    private final UserLoginInputBoundary inputBoundary;

    public UserLoginController(UserLoginInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public UserLoginResponseModel login(String username, String password) {
        UserLoginRequestModel request = new UserLoginRequestModel(username, password);
        return inputBoundary.login(request);
    }
}
