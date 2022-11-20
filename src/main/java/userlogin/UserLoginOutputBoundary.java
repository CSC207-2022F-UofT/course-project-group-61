package userlogin;

public interface UserLoginOutputBoundary {

    void prepareSuccessView(UserLoginResponseModel model);

    void prepareFailView(UserLoginResponseModel model);
}
