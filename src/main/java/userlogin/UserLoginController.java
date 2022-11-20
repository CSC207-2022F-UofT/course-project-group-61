package userlogin;

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
