package userlogin;

public interface UserLoginOutputBoundary {

    UserLoginResponseModel prepareSuccessView();

    UserLoginResponseModel prepareFailView(LoginStatus status);
}
