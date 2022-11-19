package userlogin;

public class UserLoginPresenter implements UserLoginOutputBoundary {
    @Override
    public UserLoginResponseModel prepareSuccessView() {
        return null;
    }

    @Override
    public UserLoginResponseModel prepareFailView(LoginStatus status) {
        return null;
    }
}
